package Coords;

import java.util.Vector;

import Geom.Point3D;

public class Coords implements coords_converter {

	final static int earth_rad = 6371000;
	final static double norm_lan = 0.847091174;
    
	/*
	 * This function computes a new point which is the gps point transformed by a 3D vector (in meters).
	 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {

		gps.change_Geometric_To_Cart();
		Point3D temp = new Point3D(gps);
		temp.add(local_vector_in_meter);
		gps.change_Cart_To_Geometric();
		temp.change_Cart_To_Geometric();
		return temp;

	}

	/**
	 * This function computes the 3D distance (in meters) between the two gps like points.
	 * I used this website to get information for this function:
	 * https://stackoverflow.com/questions/5557706/calculating-distance-using-latitude-longitude-coordinates-in-kilometers-with-jav
	 * @param gps0 represent the first point.
	 * @param gps1 represent the second point.
	 * @return the distance 2D between the two points.
	 */
	
	public double distance2d(Point3D gps0, Point3D gps1){
		
		double lon_norm = Math.cos(gps0.x() * Math.PI / 180);
		double dis_lat = gps1.x() - gps0.x();
		double dis_lon = gps1.y() - gps0.y();
		dis_lat = gps0.d2r(dis_lat);
		dis_lon = gps0.d2r(dis_lon);
		dis_lat = Math.sin(dis_lat) * earth_rad;
		dis_lon = Math.sin(dis_lon) * earth_rad * lon_norm;
		double dis = dis_lat * dis_lat + dis_lon * dis_lon;
		return Math.sqrt(dis);
	}
	
	/**
	 * This function computes the 3D distance (in meters) between the two gps like
	 * points we used the file Excel that was send to use by Boaz.
	 * 
	 */
	
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {

		double theDistance = (Math.sin(Math.toRadians(gps0.x())) *
				Math.sin(Math.toRadians(gps1.x()))) +
				(Math.cos(Math.toRadians(gps0.x())) * Math.cos(Math.toRadians(gps1.x())) *
						Math.cos(Math.toRadians(gps0.y() - gps1.y() )));
		return new Double(((Math.toDegrees(Math.acos(theDistance))) * 69.09)* 1.60934) * 1000;
	}

	/*
	 * This function computes the 3D vector (in meters) between two gps like points.
	 */
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		gps0.change_Geometric_To_Cart();
		gps1.change_Geometric_To_Cart();
		double x = gps1.x() - gps0.x();
		double y = gps1.y() - gps0.y();
		double z = gps1.z() - gps0.z();
		gps0.change_Cart_To_Geometric();
		gps1.change_Cart_To_Geometric();
		Point3D vec = new Point3D(x, y, z);
		return vec;

	}

	/** computes the polar representation of the 3D vector be gps0--gps1.
	 * I used this website for this function:
	 * https://www.omnicalculator.com/other/azimuth#how-to-calculate-the-azimuth-from-latitude-and-longitude.
	 */
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {

		double x = Math.sin(Math.toRadians(gps1.y() - gps0.y()) * Math.cos(Math.toRadians(gps1.x())));
		double y = Math.cos(Math.toRadians(gps0.x()))
				   * Math.sin(Math.toRadians(gps1.x())) - Math.sin(Math.toRadians(gps0.x()))
				   * Math.cos(Math.toRadians(gps1.x())) * Math.cos(Math.toRadians(gps1.y() - gps0.y()));
		double azi = Math.atan2(x, y);
		azi = Math.toDegrees(azi) + 360;
		double dis = distance3d(gps0, gps1);
		double el = Math.toDegrees(Math.asin((gps1.z() - gps0.z()) / dis));
		double[] ans = {azi, el, dis};
		return ans;
	}

	/**
	 * This function returns true if this point is a valid coordinate.
	 * lat : [-180, +180].
	 * lon : [-90, +90].
	 * alt : [-450, +inf].
	 * else - return false.
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {

		if(p.x() >= -180 && p.x() <= 180 && p.y() >= -90 && p.y() <= 90 && p.z() >= -450) {
			return true;
		}
		return false;
	}
	
}
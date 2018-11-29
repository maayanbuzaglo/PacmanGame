package Coords;

import Geom.Point3D;

public class Coords implements coords_converter {
	
	final static int earth_rad = 6371000;

	/*
	 * This function computes a new point which is the gps point transformed by a 3D vector (in meters).
	 */
	@Override
	public Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		double comp = local_vector_in_meter.x() / earth_rad;
		double lat = gps.x() + (comp * 180/Math.PI);
		double comp2 = (local_vector_in_meter.y() + (comp * 180 / Math.PI));
		double lon = gps.y() + (comp2 * 180 / Math.PI);
		double alt = gps.z() + local_vector_in_meter.z();
		return new Point3D(lat, lon, alt);
	}

	/**
	 * This function computes the 3D distance (in meters) between the two gps like points.
	 * I used this website to get information for this function:
	 * https://stackoverflow.com/questions/5557706/calculating-distance-using-latitude-longitude-coordinates-in-kilometers-with-jav
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		// TODO Auto-generated method stub
		double theDistance = (Math.sin(Math.toRadians(gps0.x())) *
				Math.sin(Math.toRadians(gps1.x()))) +
				(Math.cos(Math.toRadians(gps0.x())) * Math.cos(Math.toRadians(gps1.x())) *
				Math.cos(Math.toRadians(gps0.y() - gps1.y() )));

		return new Double((Math.toDegrees(Math.acos(theDistance))) * 69.09)* 1.60934;
	}

	public Point3D vector3D(Point3D gps0, Point3D gps1) {
	    // Common values
	    double b = earth_rad + gps1.x();
	    double c = earth_rad + gps0.x();

	    double b2 = b * b;
	    double c2 = c * c;
	    double bc2 = 2 * b * c;

	    // Longitudinal calculations.
	    double alpha = gps1.y() - gps0.y();
	    // Conversion to radian.
	    alpha = alpha * Math.PI / 180;
	    // Small-angle approximation.
	    double cos = 1 - alpha * alpha / 2; //Math.cos(alpha);
	    // Use the law of cosines / Al Kashi theorem.
	    double x = Math.sqrt(b2 + c2 - bc2 * cos);

	    // Repeat for latitudinal calculations
	    alpha = gps1.x() - gps0.x();
	    alpha = alpha * Math.PI / 180;
	    cos = 1 - alpha * alpha/2; //Math.cos(alpha);
	    double y = Math.sqrt(b2 + c2 - bc2 * cos);

	    // Obtain vertical difference, too
	    double z = gps1.z() - gps0.z();

	    return new Point3D(x, y, z);
	}

	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
//		x = acos( {sin(lat2) - sin(lat1)*cos(d) } / {sin(d)*cos(lat1)} )
//
//				IF sin(lon2-lon1) < 0, phi = x
//
//				IF sin(lon2-lon1) > 0, phi = 2*pi - x
		return null;
	}

	/**
	 * This function returns true if this point is a valid coordinate.
	 * lat : [-180,+180].
	 * lon : [-90,+90].
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
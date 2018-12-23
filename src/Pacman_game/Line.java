package Pacman_game;

import Coords.Coords;
import Geom.Point3D;

/*
 * This class represents a line between 2 points.
 * @author maayan
 * @author nahama
 */
public class Line {

	Point3D point1;
	Point3D point2;
	double distance; //the distance between the 2 points.
	
	/*
	 * An empty constructor.
	 */
	public Line() {
		
		this.point1 = null;
		this.point2 = null;
		this.distance = 0;
	}
	
	/*
	 * Constructor.
	 */
	public Line(Point3D p1, Point3D p2) {
		
		Coords c = new Coords();
		this.point1 = p1;
		this.point2 = p2;
		this.distance = c.distance2d(p1, p2);
	}

	@Override
	public String toString() {
		return "Line [Point1 = " + point1 +
				", Point2 = " + point2 + 
				", Distance = " + distance + "]\n";
	}

	public Point3D getPoint1() {
		return this.point1;
	}

	public void setPoint1(Point3D p1) {
		this.point1 = p1;
	}

	public Point3D getPoint2() {
		return this.point2;
	}

	public void setPoint2(Point3D p2) {
		this.point2 = p2;
	}

	public double getDistance() {
		return this.distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}

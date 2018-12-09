package Packman_Game;

import Geom.Point3D;

public class Line {

	Point3D p1;
	Point3D p2;
	double dis;
	double time;
	
	public Line(Point3D p1, Point3D p2) {
		
		this.p1 = p1;
		this.p2 = p2;
		this.dis = p1.distance3D(p2);
	}

	@Override
	public String toString() {
		return "Line [p1=" + p1 + ", p2=" + p2 + ", dis=" + dis + "]";
	}

	public Point3D getP1() {
		return p1;
	}

	public void setP1(Point3D p1) {
		this.p1 = p1;
	}

	public Point3D getP2() {
		return p2;
	}

	public void setP2(Point3D p2) {
		this.p2 = p2;
	}

	public double getDis() {
		return dis;
	}

	public void setDis(double dis) {
		this.dis = dis;
	}

}

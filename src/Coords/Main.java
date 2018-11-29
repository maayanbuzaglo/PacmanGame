package Coords;

import Geom.Point3D;

public class Main {

	public static void main(String[] args) {
		
		Point3D p1 = new Point3D(32.103315 , 35.209039, 670);
		Point3D p2 = new Point3D(32.106352, 35.205225, 650);
		Coords c = new Coords();
		Point3D p3 = c.vector3D(p1, p2);
		System.out.println(p3.toString());

	}

}

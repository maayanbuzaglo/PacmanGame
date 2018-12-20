package Tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import Coords.Coords;
import Geom.Point3D;

class Coords_Test {

	/*
	 * This class tests the Coords functions.
	 */
	@Test
	void test() {
		
		Point3D p1 = new Point3D(32.103315 , 35.209039, 670);
		Point3D p2 = new Point3D(32.106352, 35.205225, 650);
		Coords c = new Coords();
		
		/**
		 * This test checks the add function.
		 */
		assertEquals((int)c.add(p1, p2).x(), 32);
		assertEquals((int)c.add(p1, p2).y(), 35);
		assertEquals((int)c.add(p1, p2).z(), 1320);
		
		/**
		 * This test checks the distance3d function.
		 */
		assertEquals((int)c.distance3d(p1, p2), 493);
		
		/**
		 * This test checks the vector3d function.
		 */
		assertEquals((int)c.vector3D(p1, p2).ix(), 286);
		assertEquals((int)c.vector3D(p1, p2).iy(), -293);
		assertEquals((int)c.vector3D(p1, p2).iz(), -20);
		
		/**
		 * This test checks the azimuth_elevation_dist function.
		 */
		int[] check = {313, -2, 493};
		assertEquals(check[0], (int)c.azimuth_elevation_dist(p1, p2)[0]);
		assertEquals(check[1], (int)c.azimuth_elevation_dist(p1, p2)[1]);
		assertEquals(check[2], (int)c.azimuth_elevation_dist(p1, p2)[2]);
		
		/**
		 * This test checks the isValid_GPS_Point function.
		 */
		assertEquals(c.isValid_GPS_Point(p1), true);
	}

}

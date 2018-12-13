package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

import Geom.Point3D;
import Packman_Game.Map;
import Packman_Game.Pixel;

class Map_Test {

	@Test
	void test() throws IOException {
		
		Map m = new Map();
		Pixel p;
		Point3D p2 = new Point3D(35.20746107, 32.10248386);
		System.out.println(p2.toString());
		
		p = m.Point2Pixel(p2.x(), p2.y());
		System.out.println(p.toString());
		assertArrayEquals(p2.x(), actual);
	}

}

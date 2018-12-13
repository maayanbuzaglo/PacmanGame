package Tests;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import Geom.Point3D;
import Packman_Game.Map;
import Packman_Game.Pixel;

/*
 * This class tests the Map functions.
 */
class Map_Test {

	@Test
	void test() throws IOException {
		
		Map m = new Map();
		Pixel p;
		Point3D p2 = new Point3D(35.20746107, 32.10248386);
		Pixel test = new Pixel(731, 529);
		p = m.Point2Pixel(p2.x(), p2.y());
		
		Point3D test2 = m.Pixel2Point(p);
		p2.set_x((int)(p2.x() % 35 * 10000));
		p2.set_y((int)(p2.y() % 32 * 10000));
		test2.set_x((int)(test2.x() % 35 * 10000));
		test2.set_y((int)(test2.y() % 32 * 10000));
		System.out.println(p2);
		System.out.println(test2);
		
		/*
		 * This test checks the Point2Pixel function.
		 */
		assertEquals(true, p.equals(test));
		
		/*
		 * This test checks the Pixel2Point function.
		 */
		assertEquals(true, p2.equals(test2));
	}

}

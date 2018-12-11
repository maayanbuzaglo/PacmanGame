package Packman_Game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Geom.Point3D;

public class Map {

	private Image image;
	private static Point3D pStart;
	private static Point3D pEnd;
	static int image_width;
	static int image_height;

	public Map() {

		image = null;
		pStart = new Point3D(35.20232, 32.10571);
		pEnd = new Point3D(35.21239, 32.10180);
		image_height = 642;
		image_width = 1433;
	}

	Map(String imgLocation) throws IOException {

		File f = new File(imgLocation);
		image = ImageIO.read(f);
	}

	public void setImage_width(int image_width) {
		this.image_width = image_width;
	}

	public void setImage_height(int image_height) {
		this.image_height = image_height;
	}

	public static Pixel Point2Pixel(double longitude, double latitude) {
		
		double mapLongitudeStart = pStart.x();
		double mapLatitudeStart = pStart.y();
		double mapLongitude = pEnd.x() - mapLongitudeStart;
		double mapLatitude = mapLatitudeStart - pEnd.y();
		
		double lng = longitude - mapLongitudeStart;
	    double  lat = mapLatitudeStart-latitude;

	    double x =  (image_width * (lng / mapLongitude));
	    double y =  (image_height * (lat / mapLatitude));

	    return new Pixel((int)x, (int)y);
	}

	public static Point3D Pixel2Point(Pixel pixel)  {

		Pixel pix = Pix_Worth_Point(image_width, image_height);
		double x = pixel.getX() * pix.getX();
		double y = pixel.getY() * pix.getY();
		double dx = x + pStart.x();
		double dy = y + pStart.y();
		Point3D ans = new Point3D(dx, dy);
		ans.chang_Cart_To_Geometric();
		pStart.chang_Cart_To_Geometric();
		pEnd.chang_Cart_To_Geometric();
		return ans;
	}

	public static Pixel Pix_Worth_Point(double pixWidth, double pixHeight) {

		pStart.chang_Geometric_To_Cart();
		pEnd.chang_Geometric_To_Cart();
		double x = pEnd.x() - pStart.x();
		double y = pEnd.y() - pStart.y();
		double dx = x / pixWidth;
		double dy = y / pixHeight;
		Pixel ans = new Pixel (dx,dy);
		return ans;
	}

	public void changFrame(Pixel p, ArrayList<Pixel> pList, ArrayList<Pixel> fList) {

		this.setImage_width((int)p.getX());
		this.setImage_height((int)p.getY());

		ArrayList<Point3D> pTemp = new ArrayList<Point3D>();
		ArrayList<Point3D> fTemp = new ArrayList<Point3D>();

		for (int i = 0; i < pList.size(); i++) {
			Pixel tmp = new Pixel (pList.get(i).getX(), pList.get(i).getY());
			pTemp.add(this.Pixel2Point(tmp));
		}
		for (int i = 0; i < fList.size(); i++) {
			Pixel tmp = new Pixel (fList.get(i).getX(), fList.get(i).getY());
			fTemp.add(this.Pixel2Point(tmp));
		}
		for (int i = 0; i < pTemp.size(); i++) {
			Pixel tmp = this.Point2Pixel(pTemp.get(i).x(),pTemp.get(i).y());
			pList.get(i).setX(tmp.getX());
			pList.get(i).setY(tmp.getY());
		}
		for (int i = 0; i < fTemp.size(); i++) {
			Pixel tmp = this.Point2Pixel(fTemp.get(i).x(),fTemp.get(i).y());
			fList.get(i).setX(tmp.getX());
			fList.get(i).setY(tmp.getY());
		}
	}

	public static void main(String[] args) {

		Map m = new Map();
		Pixel p;
		Point3D p2 = new Point3D(35.20746107, 32.10248386);
		System.out.println(p2.toString());
		
		p = m.Point2Pixel(p2.x(), p2.y());
		System.out.println(p.toString());
	}
	
}
package Pacman_game;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import Geom.Point3D;

/*
 * This class represents a map that contains a map image file,
 * and all the necessary parameters of its alignment to a global coordinate system.
 * @author maayan
 * @author nahama
 */
public class Map {

	private Image image;
	private static Point3D pStart;
	private static Point3D pEnd;
	static int image_weight;
	static int image_height;

	/*
	 * A default constructor.
	 */
	public Map() throws IOException {

		image = ImageIO.read(new File("C:\\Users\\מעיין\\eclipse-workspace\\OopNavigtion\\pictures\\Ariel1.png")); //gets the backgrounds image.
		pStart = new Point3D(35.20232, 32.10571);
		pEnd = new Point3D(35.21239, 32.10180);
		image_height = 642;
		image_weight = 1433;
	}
	
	/*
	 * Constructor.
	 */
	public Map(int width, int height) throws IOException {

		image = ImageIO.read(new File("C:\\Users\\מעיין\\eclipse-workspace\\OopNavigtion\\pictures\\Ariel1.png")); //gets the backgrounds image.
		pStart = new Point3D(35.20232, 32.10571);
		pEnd = new Point3D(35.21239, 32.10180);
		image_height = height;
		image_weight = width;
	}

	/*
	 * Constructor that gets a string of the map image location.
	 */
	public Map(String imgLocation) throws IOException {

		File f = new File(imgLocation);
		image = ImageIO.read(f);
	}

	/*
	 * This function converts from point to pixel.
	 */
	public Pixel Point2Pixel(double longitude, double latitude) {
		
		Pixel pix = Pixel2Point2(image_weight, image_height);
		Point3D gps = new Point3D (longitude,latitude);
		gps.change_Geometric_To_Cart();
		double x = gps.x() - pStart.x();
		double y = gps.y() - pStart.y();
		double dx = x / pix.getX();
		double dy = y / pix.getY();
		Pixel ans = new Pixel (dx, dy);
		gps.change_Cart_To_Geometric();
		pStart.change_Cart_To_Geometric();
		pEnd.change_Cart_To_Geometric();
		return ans;
	}

	/*
	 * This function converts from pixel to point.
	 */
	public Point3D Pixel2Point(Pixel pixel)  {

		Pixel pix = Pixel2Point2(image_weight, image_height);
		double x = pixel.getX() * pix.getX();
		double y = pixel.getY() * pix.getY();
		double dx = x + pStart.x();
		double dy = y + pStart.y();
		Point3D ans = new Point3D(dx, dy);
		ans.change_Cart_To_Geometric();
		pStart.change_Cart_To_Geometric();
		pEnd.change_Cart_To_Geometric();
		return ans;
	}

	/*
	 * This function helps the Pixel2Point function.
	 */
	public Pixel Pixel2Point2(double pixWeight, double pixHeight) {

		pStart.change_Geometric_To_Cart();
		pEnd.change_Geometric_To_Cart();
		double x = pEnd.x() - pStart.x();
		double y = pEnd.y() - pStart.y();
		double dx = x / pixWeight;
		double dy = y / pixHeight;
		Pixel ans = new Pixel (dx,dy);
		return ans;
	}

	/*
	 * This function updates the frame.
	 */
	public void changeFrame(Pixel p, ArrayList<Pixel> pList, ArrayList<Pixel> fList , ArrayList<Pixel> lList) {

		ArrayList<Point3D> pTemp = new ArrayList<Point3D>();
		ArrayList<Point3D> fTemp = new ArrayList<Point3D>();
		ArrayList<Point3D> lTemp = new ArrayList<Point3D>();

		for (int i = 0; i < pList.size(); i++) {
			Pixel tmp = new Pixel(pList.get(i).getX(), pList.get(i).getY());
			pTemp.add(this.Pixel2Point(tmp));
		}
		
		for (int i = 0; i < fList.size(); i++) {
			Pixel tmp = new Pixel(fList.get(i).getX(), fList.get(i).getY());
			fTemp.add(this.Pixel2Point(tmp));
		}
		
		for (int i = 0; i < lList.size(); i++) {
			Pixel tmp = new Pixel(lList.get(i).getX(), lList.get(i).getY());
			lTemp.add(this.Pixel2Point(tmp));
		}
		
		this.setImage_weight((int)p.getX());
		this.setImage_height((int)p.getY());

		for (int i = 0; i < pTemp.size(); i++) {
			Pixel tmp = this.Point2Pixel(pTemp.get(i).x(), pTemp.get(i).y());
			pList.get(i).setX(tmp.getX());
			pList.get(i).setY(tmp.getY());
		}
		
		for (int i = 0; i < fTemp.size(); i++) {
			Pixel tmp = this.Point2Pixel(fTemp.get(i).x(), fTemp.get(i).y());
			fList.get(i).setX(tmp.getX());
			fList.get(i).setY(tmp.getY());
		}
		
		for (int i = 0; i < lTemp.size(); i++) {
			Pixel tmp = this.Point2Pixel(lTemp.get(i).x(), lTemp.get(i).y());
			lList.get(i).setX(tmp.getX());
			lList.get(i).setY(tmp.getY());
		}
	}
	
	public Image getImage() {
		return this.image;
	}

	public void setImage(Image image) {
		this.image = image;
	}
	
	public Point3D getPstart() {
		return this.pStart;
	}

	public void setPstart(Point3D p) {
		this.pStart = new Point3D(p);
	}
	
	public Point3D getPend() {
		return this.pEnd;
	}

	public void setPend(Point3D p) {
		this.pEnd = new Point3D(p);
	}
	
	public int getImage_weight() {
		return this.image_weight;
	}

	public void setImage_weight(int image_weight) {
		this.image_weight = image_weight;
	}

	public int getImage_height() {
		return this.image_height;
	}
	
	public void setImage_height(int image_height) {
		this.image_height = image_height;
	}

	/*
	 * Example.
	 */
//	public static void main(String[] args) throws IOException {
//
//		Map m = new Map();
//		Pixel p;
//		Point3D p2 = new Point3D(35.20746107, 32.10248386);
//		System.out.println(p2.toString());
//		
//		p = m.Point2Pixel(p2.x(), p2.y());
//		System.out.println(p.toString());
//	}
	
}
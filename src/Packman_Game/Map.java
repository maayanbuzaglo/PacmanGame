package Packman_Game;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.junit.experimental.theories.Theories;

import Geom.Point3D;

public class Map {

	private Image image;
	private Point3D pStart;
	private Point3D pEnd;
	int image_width;
	int image_height;

	public Map() {

		image = null;
		pStart= new Point3D (35.20232,32.10571);
		pEnd= new Point3D (35.21239,32.10180);
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

	public Pixel Point2Pixel(Point3D gps) {

		Pixel pix = Pix_Worth_Point(image_width, image_height);
		gps.chang_Geometric_To_Cart();
		double x = gps.x() - pStart.x();
		double y = gps.y() - pStart.y();
		double dx = x / pix.getX();
		double dy = y / pix.getY();
		Pixel ans = new Pixel (dx,dy);
		gps.chang_Cart_To_Geometric();
		pStart.chang_Cart_To_Geometric();
		pEnd.chang_Cart_To_Geometric();
		return ans;

	}

	public Point3D Pixel2Point(Pixel pixel)  {

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

	public Pixel Pix_Worth_Point(double pixWidth, double pixHeight) {

		pStart.chang_Geometric_To_Cart();
		pEnd.chang_Geometric_To_Cart();
		double x = pEnd.x() - pStart.x();
		double y = pEnd.y() - pStart.y();
		double dx = x / pixWidth;
		double dy = y / pixHeight;
		Pixel ans = new Pixel (dx,dy);
		return ans;
	}

	public void changFrame(Pixel p, ArrayList<Pixel> pList) {

		this.setImage_width((int)p.getX());
		this.setImage_height((int)p.getY());
		
		ArrayList<Point3D> pTemp = new ArrayList<Point3D>();
		for (int i = 0; i < pList.size(); i++) {
			Pixel tmp = new Pixel (pList.get(i).getX(), pList.get(i).getY());
			pTemp.add(this.Pixel2Point(tmp));
		}
		
		
		for (int i = 0; i < pTemp.size(); i++) {
			Pixel tmp = this.Point2Pixel(pTemp.get(i));
			pList.get(i).setX(tmp.getX());
			pList.get(i).setY(tmp.getY());
		}
		
	}










	public static void main(String[] args) {


		//		Pixel p = new Pixel(705, 541);
		//		Point3D p2 = new Point3D(Pixel2Point(p));
		//		p2.toString();

	}
}
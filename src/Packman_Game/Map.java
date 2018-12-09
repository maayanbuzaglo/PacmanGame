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

import Geom.Point3D;

public class Map {

	private Image image;
	private Point3D pStart;
	private Point3D pEnd;
	int image_width = 1433;
	int image_height = 642;

	Map() {
		image = null;
		pStart= new Point3D (32.10571,35.20232);
		pEnd= new Point3D (32.10180,35.21239);
		image_height = 642;
		image_width = 1433;

	}
	
	Map(String imgLocation) throws IOException {
		File f = new File(imgLocation);
		image = ImageIO.read(f);
	}
	
	public Pixel Point2Pixel(Point3D gps)
	{
		gps.chang_Geometric_To_Cart();
		pStart.chang_Geometric_To_Cart();
		double x = gps.x() - pStart.x();
		double y = gps.y() - pStart.y();
		double dx = x / image_width;
		double dy = y / image_height;
		Pixel ans = new Pixel (dx,dy);
		gps.chang_Cart_To_Geometric();
		return ans;

	}
	
	public Point3D Pixel2Point(Pixel pixel) 
	{
		Pixel pix = Pix_Worth_Point(image_width, image_height);
		double x = pixel.getX() * pix.getX();
		double y = pixel.getY() * pix.getY();
		double dx = x + pStart.x();
		double dy = y + pStart.y();
		Point3D ans = new Point3D(dx, dy);
		ans.chang_Cart_To_Geometric();
		return ans;
	}
	
	public Pixel Pix_Worth_Point(double pixWidth, double pixHeight)
	{
		pStart.chang_Geometric_To_Cart();
		pEnd.chang_Geometric_To_Cart();
		double x = pEnd.x() - pStart.x();
		double y = pEnd.y() - pStart.y();
		double dx = x / pixWidth;
		double dy = y / pixHeight;
		Pixel ans = new Pixel (dx,dy);
		return ans;
	}
}

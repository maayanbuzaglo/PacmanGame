package Packman_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import Geom.Point3D;

public class Pacman {

	private Point3D location;
	private long ID;
	private double speed;
	private double radius;

	public Pacman() 
	{
		location = new Point3D(0, 0, 0);
		ID = 0;
		speed = 1;
		radius = 1;
	}

	Pacman(Point3D point, long ID, double speed, double radius) 
	{
		this.location = point;
		this.ID = ID;
		this.speed = speed;
		this.radius = radius;
	}

	public Point3D getLocation() {
		return location;
	}

	public long getID() {
		return ID;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	/////////////////////////// My Functions//////////////////////////////////
	
	/*
	 * This function gets a csv file (String)
	 * split the elements by ",".
	 * create for every line a Row_Locate,
	 * and returns an array list of Row_Locate.
	 */
	public ArrayList<Pacman> ReadCsvFile(String file) {
		ArrayList<Pacman> Csv = new ArrayList<Pacman>();
		Scanner sc = null;
		File fi = new File(file); //gets the file.
		try { //reads the file.
			sc = new Scanner(fi);
		}
		catch (FileNotFoundException exc) { //if file not found - Exception.
			exc.printStackTrace();
		}
		String in = sc.nextLine();
		while(sc.hasNext()) { //continues until there are no more lines in the file.
			in = sc.nextLine(); //moves to the next line.
			String[] arr = in.split(","); //splits the elements by ",";
			if(arr[0].equals("P")) {
			int id = Integer.parseInt(arr[1]); //changes the id from String to int.
			double lat = Double.parseDouble(arr[2]); //changes the latitude from String to double.
			double lon = Double.parseDouble(arr[3]); //changes the longitude from String to double.
			double alt = Double.parseDouble(arr[4]); //changes the altitude from String to double.
			Point3D point = new Point3D(lat, lon, alt);
			double speed = Double.parseDouble(arr[5]); //changes the speed from String to double.
			double rad = Double.parseDouble(arr[6]); //changes the radius from String to double.
			Pacman row = new Pacman(point, id, speed, radius);
			Csv.add(row); //adds the line.
			}
		}
		sc.close(); //closes the scanner.
		return Csv;
	}

	@Override
	public String toString() {
		return "Pacman [location = " + location + 
				", ID = " + ID + 
				", speed = " + speed +
				", radius = " + radius + "]";
	}

//	public void move(double x_move, double y_move, double z_move) 
//	{
//		this.location.set_x(location.x() + x_move);
//		this.location.set_y(location.x() + y_move);
//		this.location.set_z(location.x() + z_move);
//	}
//
//	public Fruit closestFruit(ArrayList<Fruit> fruit_list) 
//	{
//		double distance = this.location.distance3D(fruit_list.get(0).getLocation());
//		long ID = 0;
//		int price = 0;
//		Point3D ans_point = fruit_list.get(0).getLocation();
//		while (fruit_list.iterator().hasNext()) {
//			Fruit runner = fruit_list.iterator().next();
//			if (this.location.distance3D(runner.getLocation()) < distance) {
//				ans_point = runner.getLocation();
//				ID = runner.getID();
//				price = runner.getPrice();
//			}
//		}
//		Fruit ans = new Fruit(ans_point, ID, price);
//		return ans;
//	}
//
//	public ArrayList<Fruit> MyTrack(ArrayList<Fruit> list) {
//		java.util.Iterator<Fruit> iter = list.iterator();
//		ArrayList<Fruit> ans = new ArrayList<Fruit>();
//		while (iter.hasNext()) {
//			Fruit runner = iter.next();
//			if (this.location.x() == runner.getLocation().x()
//					&& this.location.y() == runner.getLocation().y()) {
//				ans.add(runner);
//			}
//		}
//		return ans;
//	}
//
//	public int level(ArrayList<Fruit> list) {
//		ArrayList<Fruit> ans = MyTrack(list);
//		return ans.size();
//	}

}

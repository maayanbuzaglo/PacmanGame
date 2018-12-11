package Packman_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import Geom.Point3D;

public class Fruit {

	private Point3D location;
	private long ID;
	private int price;

	public Fruit()
	{
		location = new Point3D(0,0,0);
		ID = 0;
		this.price = 1;
	}

	public Fruit(Point3D point, long ID,int price)
	{
		this.location = new Point3D(point);
		this.ID = ID;
		this.price = price;

	}
	
	/*
	 * This function gets a csv file (String)
	 * split the elements by ",".
	 * create for every line a Row_Locate,
	 * and returns an array list of Row_Locate.
	 */
	public ArrayList<Fruit> ReadCsvFile(String file) {
		ArrayList<Fruit> Csv = new ArrayList<Fruit>();
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
			if(arr[0].equals("F")) {
			int id = Integer.parseInt(arr[1]); //changes the id from String to int.
			double lat = Double.parseDouble(arr[2]); //changes the latitude from String to double.
			double lon = Double.parseDouble(arr[3]); //changes the longitude from String to double.
			double alt = Double.parseDouble(arr[4]); //changes the altitude from String to double.
			Point3D point = new Point3D(lat, lon, alt);
			int weight = Integer.parseInt(arr[5]); //changes the speed from String to double.
			Fruit row = new Fruit(point, id, weight);
			Csv.add(row); //adds the line.
			}
		}
		sc.close(); //closes the scanner.
		return Csv;
	}

	@Override
	public String toString() {
		return "Fruit [location = " + location +
				", ID = " + ID +
				", price = " + price + "]";
	}

	public long getID() {
		return ID;
	}
	
	public void setID(long iD) {
		ID = iD;
	}
	
	
	public Point3D getLocation() {
		return location;
	}
	
	public void setLocation(Point3D p) {
		this.location = p;
	}
	
	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

}

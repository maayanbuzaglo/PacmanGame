package Pacman_game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import Geom.Point3D;

/*
 * This class represents a fruit.
 * @author maayan
 * @author nahama
 */
public class Fruit {

	private Point3D location;
	private long id;
	private int weight;
	double endTime  = 0 ; 
	/*
	 * An empty constructor.
	 */
	public Fruit() {
		
		location = new Point3D(0, 0, 0);
		id = 0;
		this.weight = 1;
	}

	/*
	 * Constructor.
	 */
	public Fruit(Fruit f) {
		
		this.location = new Point3D(f.getLocation());
		this.id = f.getID();
		this.weight = f.getWeight();
	}
	
	/*
	 * Constructor.
	 */
	public Fruit(Point3D point, long id, int weight) {
		
		this.location = new Point3D(point);
		this.id = id;
		this.weight = weight;
	}
	
	/*
	 * This function gets a csv file (String),
	 * split the elements by ",".
	 * create for every line a fruit,
	 * and returns an array list of fruits.
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
			double lon = Double.parseDouble(arr[2]); //changes the longitude from String to double.
			double lat = Double.parseDouble(arr[3]); //changes the latitude from String to double.
			double alt = Double.parseDouble(arr[4]); //changes the altitude from String to double.
			Point3D point = new Point3D(lat, lon, alt);
			int weight = Integer.parseInt(arr[5]); //changes the weight from String to int.
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
				", ID = " + id +
				", Weight = " + weight + "]\n";
	}
	
	public Point3D getLocation() {
		return location;
	}
	
	public void setLocation(Point3D point) {
		this.location = point;
	}

	public long getID() {
		return id;
	}
	
	public void setID(long id) {
		this.id = id;
	}
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int price) {
		this.weight = price;
	}

	public double getEndTime() {
		return this.endTime;
	}

	public void setEndTime(double endTime) {
		this.endTime = endTime;
	}

}

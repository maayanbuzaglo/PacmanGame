package Packman_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import Geom.Point3D;

/*
 * This class represents a pacman.
 */
public class Pacman {

	Point3D location;
	long id;
	double speed;
	double radius;
	public double time;
	public Path path;

	/*
	 * An empty constructor.
	 */
	public Pacman() {

		this.location = new Point3D(0, 0, 0);
		this.id = 0;
		this.speed = 1;
		this.radius = 1;
		this.time = 0;
		this.path = new Path();
	}

	/*
	 * Constructor.
	 */
	public Pacman(Pacman p) {

		this.location = new Point3D(p.getLocation().x(), p.getLocation().y(), p.getLocation().z());;
		this.id = p.getID();
		this.speed = p.getSpeed();
		this.radius = p.getRadius();
		this.path = p.getPath();
	}
	
	/*
	 * Constructor.
	 */
	public Pacman(Point3D point, long id, double speed, double radius) {

		this.location = new Point3D(point.x(), point.y(), point.z());;
		this.id = id;
		this.speed = speed;
		this.radius = radius;
		this.path = new Path();
	}

	/*
	 * This function gets a csv file (String)
	 * split the elements by ",".
	 * create for every line a pacman,
	 * and returns an array list of pacmans.
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
				double lat = Double.parseDouble(arr[3]); //changes the longitude from String to double.
				double lon = Double.parseDouble(arr[2]); //changes the latitude from String to double.
				double alt = Double.parseDouble(arr[4]); //changes the altitude from String to double.
				Point3D point = new Point3D(lat, lon, alt);
				double speed = Double.parseDouble(arr[5]); //changes the speed from String to double.
				double radius = Double.parseDouble(arr[6]); //changes the radius from String to double.
				Pacman row = new Pacman(point, id, speed, radius);
				Csv.add(row); //adds the line.
			}
		}
		sc.close(); //closes the scanner.
		return Csv;
	}

	public Point3D getLocation() {
		return this.location;
	}

	public void setLocation(Point3D point) {
		this.location = new Point3D(point.x(), point.y(), point.z());
	}

	public long getID() {
		return this.id;
	}

	public void setID(int id) {
		this.id = id;	
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getRadius() {
		return this.radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getTime() {
		return this.time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public Path getPath() {
		return this.path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	@Override
	public String toString() {

		return "Pacman [location = " + location + 
				", ID = " + id + 
				", Speed = " + speed +
				", Radius = " + radius +
				", Time = " + time + 
				", Path = " + path.toString() + "]\n";

	}

	/*
	 * This function makes the pacman move to a new point.
	 */
	public void move(double Xmove, double Ymove, double Zmove) {

		this.location.set_x(location.x() + Xmove);
		this.location.set_y(location.y() + Ymove);
		this.location.set_z(location.z() + Zmove);
	}

	/*
	 * This function gets a list of eaten fruits.
	 */
	public ArrayList<Fruit> eatenFruits(ArrayList<Fruit> list) {
		
		ArrayList<Fruit> eatenFruits = new ArrayList<Fruit>();
		for(Fruit it: list) {
			if (this.location.x() == it.getLocation().x()
			 && this.location.y() == it.getLocation().y()) {
				eatenFruits.add(it);
			}
		}
		return eatenFruits;
	}

	/*
	 * This function returns the number of eaten fruits.
	 */
	public int numEatenfruits(ArrayList<Fruit> list) {

		return eatenFruits(list).size();
	}
	
	public static void main(String[] args) {
		
		Game g = new Game();
		g.readCsv("C:\\Users\\מעיין\\eclipse-workspace\\OopNavigtion\\data\\game_1543693822377.csv");
//		System.out.println(g.Pacman_list.get(0).closestFruit(g.Fruit_list));
	}

}

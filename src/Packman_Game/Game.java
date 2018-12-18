package Packman_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

/*
 * This class represents a game that mades of pacmans and fruits.
 */
public class Game {

	public ArrayList<Pacman> Pacman_list; //Pacman list.
	public ArrayList<Fruit> Fruit_list; //Fruit list.
	public ArrayList<Line> Line_list; //Line list.

	/*
	 * An empty constructor.
	 */
	public Game() {

		this.Pacman_list = null;
		this.Fruit_list = null;
		this.Line_list = null;
	}

	/*
	 * Constructor.
	 */
	public Game(ArrayList<Pacman> pacman_list, ArrayList<Fruit> fruit_list, ArrayList<Line> line_list) {

		this.Pacman_list = pacman_list;
		this.Fruit_list = fruit_list;
		this.Line_list = line_list;
	}

	/*
	 * This function read a csv game file.
	 */
	public void readCsv(String file) {

		Pacman p  = new Pacman();
		this.Pacman_list = p.ReadCsvFile(file); //reads the fruits on the csv file.
		Fruit f = new Fruit();
		this.Fruit_list = f.ReadCsvFile(file); //reads the pacmans on the csv file.
	}

	/*
	 * This function creates a csv game file.
	 */
	public void createCSV(Game g) {

		String file = "Type,id,Lat,Lon,Alt,Speed/Weight,Radius," + g.Pacman_list.size() + "," + g.Fruit_list.size() + "\n";
		for(Pacman it: g.Pacman_list) {
			file += "P," + it.getID() + ","
					+ it.getLocation().y() + ","
					+ it.getLocation().x() + "," 
					+ it.getLocation().z() + ","
					+ it.getSpeed() + ","
					+ it.getRadius() + ",,\n";
		}
		for(Fruit it: g.Fruit_list) {
			file += "F," + it.getID() + ","
					+ it.getLocation().y() + ","
					+ it.getLocation().x() + "," 
					+ it.getLocation().z() + ","
					+ it.getWeight() + ",,,\n";
		}
		createCSV2(file);
	}

	/**
	 * This function helps to create a csv file.
	 */
	public static void createCSV2(String f) {

		try {
			File file = new File("Game.csv"); //csv name file.
			if(!file.exists()) //checks if there is a file with the same name.
				file.createNewFile();

			PrintWriter print = new PrintWriter(file);
			print.print(f);
			print.close();
			System.out.println("Finished.");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This function creates a kml file.
	 */
	public static void createKML(Game g, String f) {

		Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		
		for (Pacman it: g.Pacman_list) { //The iterator runs on a csv file.
			Placemark p = doc.createAndAddPlacemark();
			p.withDescription("Mac: " + it.getID() + "\nType: pacman")
			.withOpen(Boolean.TRUE).createAndSetPoint().
			addToCoordinates(it.getLocation().x(),it.getLocation().y());
		}

		for (Fruit it: g.Fruit_list) { //The iterator runs on a csv file.
			Placemark p = doc.createAndAddPlacemark();
			p.withDescription("Mac: " + it.getID() + "\nType: fruit")
			.withOpen(Boolean.TRUE).createAndSetPoint().
			addToCoordinates(it.getLocation().x(),it.getLocation().y());
		}
		try {
			kml.marshal(new File(f));
			/**
			 * write to kml file.
			 */
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Pacman> getPacman_list() {
		return Pacman_list;
	}

	public void setPacman_list(ArrayList<Pacman> pacman_list) {
		Pacman_list = pacman_list;
	}

	public ArrayList<Fruit> getFruit_list() {
		return Fruit_list;
	}

	public void setFruit_list(ArrayList<Fruit> fruit_list) {
		Fruit_list = fruit_list;
	}

}

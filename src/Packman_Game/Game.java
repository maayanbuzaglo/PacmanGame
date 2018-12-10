package Packman_Game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import File_format.Row_Locate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

public class Game {
	
	public ArrayList<Pacman> Pacman_list;
	public ArrayList<Fruit> Fruit_list;
	
	public Game(ArrayList<Pacman> pacman_list, ArrayList<Fruit> fruit_list) {
		
		this.Pacman_list = pacman_list;
		this.Fruit_list = fruit_list;
	}
	
	public Game() {
		
		this.Pacman_list = null;
		this.Fruit_list = null;
	}
	
	public Game(String file) {
		
		Pacman p  = new Pacman();
		this.Pacman_list = p.ReadCsvFile(file);
		Fruit f = new Fruit();
		this.Fruit_list = f.ReadCsvFile(file);
	}
	
	public void readCsv(Game g) {
		
		String file = "Type,id,Lat,Lon,Alt,Speed/Weight,Radius," + g.Pacman_list.size() + "," + g.Fruit_list.size() + "\n";
		for(Pacman it: g.Pacman_list) {
			file += "P," + it.getID() + ","
		                 + it.getLocation().x() + ","
		                 + it.getLocation().y() + "," 
		                 + it.getLocation().z() + ","
		                 + it.getSpeed() + ","
		                 + it.getRadius() + ",,\n";
		}
		for(Fruit it: g.Fruit_list) {
			file += "P," + it.getID() + ","
		                 + it.getLocation().x() + ","
		                 + it.getLocation().y() + "," 
		                 + it.getLocation().z() + ","
		                 + it.getPrice() + ",,,\n";
		}
		createCSV(file);
	}
	
	/**
	 * This function creates a csv file.
	 */
	public static void createCSV(String f) {
		
		try {
			File file = new File("try.csv");
			if(!file.exists())
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
		Placemark p = doc.createAndAddPlacemark();
		for (Pacman it: g.Pacman_list) { //The iterator runs on a csv file (List of Row_Locate).
			p.withDescription("Mac: " + it.getID())
			.withOpen(Boolean.TRUE).createAndSetPoint().
			addToCoordinates(it.getLocation().x(),it.getLocation().y());
		}
		
		for (Fruit it: g.Fruit_list) { //The iterator runs on a csv file (List of Row_Locate).
			p.withDescription("Mac: " + it.getID())
			.withOpen(Boolean.TRUE).createAndSetPoint().
			addToCoordinates(it.getLocation().x(),it.getLocation().y());
		}
		try {
			kml.marshal(new File(f));  //"final.kml"
			/**
			 * write to kml file
			 */
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
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

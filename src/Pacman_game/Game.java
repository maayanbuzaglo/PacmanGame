package Pacman_game;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.TimeSpan;

/*
 * This class represents a game that mades of pacmans and fruits.
 * @author maayan
 * @author nahama
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
	 * @param f represent the file.
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
	 * @throws ParseException an exception while parsing.
	 * @param g represent the game.
	 * @param f represent the file.
	 */
	public static void createKML(Game g, String f) throws ParseException {

		ShortestPathAlgo algo = new ShortestPathAlgo();
		ArrayList<Pacman> kml_List = new ArrayList<Pacman>();
		ArrayList<Pacman> kml_List2 = new ArrayList<Pacman>();
		try {
			kml_List = algo.closestFruit(g);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();

		Pacman pac = new Pacman(kml_List.get(kml_List.size()-1));
		boolean flag = true;
		int arr[] = new int[g.getPacman_list().size()];
		int ind = 0;
		kml_List2.add(pac);
		for (int i = kml_List.size()-1; i >= 0; i--) {
			for (int j = 0; j < kml_List2.size(); j++) {
				if(kml_List.get(i).getID() == kml_List2.get(j).getID()) {
					flag = false;
					break;	
				}
			}
			if(flag == true) {
				kml_List2.add(kml_List.get(i));
				arr[ind] = i;
				ind++;
			}
			flag = true;
		}
		for (int i = 0; i < arr.length; i++) {
			kml_List.remove(arr[i]);
		}

		for(Pacman it: kml_List2) {
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
//			long millis = Date2Millis(date);
			Placemark p = doc.createAndAddPlacemark();
//			TimeSpan s = p.createAndSetTimeSpan();
//			String str = Millis2Date(millis+(long)(it.getTime())*1000);
//			String[] strA = str.split(" ");
//			str = strA[0] + "T" + strA[1]+"Z"; 
//			s.setBegin(str);
			p.withDescription("Mac: " + it.getID() + "\nType: pacman")
			.withOpen(Boolean.TRUE).createAndSetPoint().
			addToCoordinates(it.getLocation().x(),it.getLocation().y());
		}

		for (Pacman it: kml_List) { //The iterator runs on a csv file.
			Placemark p = doc.createAndAddPlacemark();
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			long millis = Date2Millis(date);
			millis += it.getTime()*100;
			date = Millis2Date(millis);
			String date1 = Millis2Date(Date2Millis(date)+1000);
			String[] date2 = date.split(" ");
			date = date2[0]+'T'+date2[1]+'Z';
			String[] date3 = date1.split(" ");
			date1 = date3[0]+'T'+date3[1]+'Z';
			TimeSpan s = p.createAndSetTimeSpan();
			s.setBegin(date);
			s.setEnd(date1);
			p.withDescription("Mac: " + it.getID() + "\nType: pacman")
			.withOpen(Boolean.TRUE).createAndSetPoint().
			addToCoordinates(it.getLocation().x(),it.getLocation().y());
		}

		for(Fruit it: g.Fruit_list) {
			String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
			long millis = Date2Millis(date);
			Placemark p = doc.createAndAddPlacemark();
			TimeSpan s = p.createAndSetTimeSpan();
			String str = Millis2Date(millis+(long)(it.endTime)*1000);
			String[] strA = str.split(" ");
			str = strA[0] + "T" + strA[1]+"Z"; 
			s.setEnd(str);
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

	/**
	 * This function converse a String date to milliseconds.
	 * @throws ParseException an exception while parsing.
	 * @param date represent the date.
	 * @return the date in milliseconds.
	 */
	public static long Date2Millis (String date) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		Date time = format.parse(date.toString());
		long millis = time.getTime();
		return millis;
	}

	/**
	 * This function converse milliseconds to a String date.
	 * @param millis represent the date in millisecond.
	 * @return the date.
	 */
	public static String Millis2Date(long millis) {
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return date.format(new Date(millis));
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
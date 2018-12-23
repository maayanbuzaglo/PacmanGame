package File_format;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

/**
 * This class reads a csv file and create a kml file.
 * I used this GitHub to get information for this class:
 * https://github.com/igilfu/OOP_PROJECT
 * @author maayan
 * @author nahama
 */
public class Csv2kml {

	/*
	 * This function gets a csv file (String)
	 * split the elements by ",".
	 * create for every line a Row_Locate,
	 * and returns an array list of Row_Locate.
	 */
	public ArrayList<Row_Locate> ReadCsvFile(String file){
		
		ArrayList<Row_Locate> Csv = new ArrayList<Row_Locate>();
		Scanner sc = null;
		File fi = new File(file); //gets the file.
		try { //reads the file.
			sc = new Scanner(fi);
		}
		catch (FileNotFoundException exc) { //if file not found - Exception.
			exc.printStackTrace();
		}
		sc.nextLine(); //the start.
		String in = sc.nextLine();
		in = sc.nextLine();
		while(sc.hasNext()) { //continues until there are no more lines in the file.
			in = sc.nextLine(); //moves to the next line.
			String[] arr = in.split(","); //splits the elements by ",";
			double lat = Double.parseDouble(arr[6]); //changes the latitude from String to double.
			double lon = Double.parseDouble(arr[7]); //changes the longitude from String to double.
			double alt = Double.parseDouble(arr[8]); //changes the altitude from String to double.
			Row_Locate row = new Row_Locate(arr[0],arr[1],arr[2],arr[3],lat,lon,alt);
			Csv.add(row); //adds the line.
		}
		sc.close(); //closes the scanner.
		return Csv;
	}
	
	/**
	 * This function creates a kml file.
	 * @param list represent the list of row.
	 * @param f represent the file name. 
	 */
	public static void createKML(ArrayList<Row_Locate> list, String f) {
		
		Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		for (Row_Locate it: list) { //The iterator runs on a csv file (List of Row_Locate).
			Placemark p = doc.createAndAddPlacemark();
			p.createAndSetTimeStamp().withWhen(it.getFirstSeen().replace(' ','T'));
			p.withDescription("Mac: " + it.getMAC() + "\nCapabilities:   " + it.getAuthMode())
			.withName(it.getSSID()).withOpen(Boolean.TRUE).createAndSetPoint().
			addToCoordinates(it.getCurrentLongitude(),it.getCurrentLatitude());
		}
		try {
			kml.marshal(new File(f));  //"final.kml"
			/**
			 * write to kml file
			 */
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
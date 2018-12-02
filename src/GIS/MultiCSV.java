package GIS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/*
 * This class represents a recursive function which reads files of csv and create a kml file.
 */
public class MultiCSV {

	final static File folder = new File("C:\\Users\\מעיין\\Desktop\\Ex2\\data");
	private static BufferedReader br;
	static Project p = new Project();
	static Set<GIS_element> e = new HashSet<>();
	static Set<GIS_layer> l = new HashSet<>();
	static String s ;

	/*
	 * This recursive function reads just csv files from the the folders you gave it,
	 * and return for all the folders in that folder. 
	 */
	public static Project listFilesForFolder(final File folder, Set<GIS_element> el) throws IOException {
	
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry,el);
			}
			else {
				System.out.println(fileEntry.getName());
				if(fileEntry.getName().contains(".csv") && (fileEntry.getName().charAt(fileEntry.getName().length()-1) == 'v')) { //reads just if thats a csv file.
					br = new BufferedReader(new FileReader(fileEntry));
					br.readLine(); //passes two rows with no data.
					br.readLine();
					while((s = br.readLine()) != null) { //stops when the file end.
					Element element = new Element(s);
					el.add(element);
					}
					br.close();
				}
			}
			l.add(new Layer(el));
		}
		return new Project(l);
	}

	/*
	 * Examples.
	 */
	public static void main(String[] args) throws IOException {
		
		 Layer l = new Layer() ;
		 Project p = new Project();
		 Set<GIS_layer> l2 = listFilesForFolder(folder,e);
		 p = listFilesForFolder(folder,e);
//		 Element e = new Element("09:97:58:32:69:c6,Volvbbbo247,[WPA2-PSK-CCMP][WPS][ESS],2017-12-01 10:49:14,4,-81,32.172209259560766,34.814448298318844,16.376435938028056,8,WIFI");
//		
		 System.out.println(p.get_Meta_data().toString());
		 p.createKML(l2, "C:\\Users\\מעיין\\Desktop\\Ex2\\data\\kaki.kml");
	}
	
}

package GIS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MultiCSV {

	final static File folder = new File("C:\\Users\\מעיין\\Desktop\\Ex2");
	private static BufferedReader br;
	static Project p = new Project();

	public static Layer listFilesForFolder(final File folder , Layer L ) throws IOException {
	
		for (final File fileEntry : folder.listFiles()) {
			if (fileEntry.isDirectory()) {
				listFilesForFolder(fileEntry,L);
			} else {
				
				System.out.println(fileEntry.getName());
				if(fileEntry.getName().contains(".csv")) {
					br = new BufferedReader(new FileReader(fileEntry));
					br.readLine();
					br.readLine();
					
					String s = br.readLine();
					Element element = new Element(s);
					L.add(element);
				}				
			}
			//p.add(layer);
		}
		return L;
	}

	public static void main(String[] args) throws IOException {
		Layer L = new Layer() ;
		 L = listFilesForFolder(folder,L);
	}
}

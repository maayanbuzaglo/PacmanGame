package File_format;

import java.util.ArrayList;

public class Example {
	public static void main(String[] args) {

		Csv2kml r = new Csv2kml();
		ArrayList<Row_Locate> Csv = r.ReadCsvFile("C:\\Users\\מעיין\\Desktop\\Ex2\\data\\WigleWifi_20171201110209.csv");
		for (Row_Locate it: Csv) {
			System.out.println(it.toString());
		}
		r.createKML(Csv,"C:\\Users\\מעיין\\Desktop\\Ex2\\data\\f.kml");
	}
}

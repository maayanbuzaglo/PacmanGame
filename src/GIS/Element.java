package GIS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import Coords.Coords;
import Geom.Geom_element;
import Geom.Point3D;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

/*
 * This class represents an object of csv file.
 */
public class Element implements GIS_element{

	String MAC;
	String SSID;
	String AuthMode;	
	String FirstSeen;	
	double CurrentLatitude;	
	double CurrentLongitude;	
	double AltitudeMeters;
	mDATA data;
	mDATA_2 data2;

	/*
	 * Default contractor.
	 */
	public Element() {

	}

	/*
	 * Constructor that makes a csv file.
	 */
	public Element(String nAME, String mAC, String sSID, String authMode, String firstSeen,
			double currentLatitude, double currentLongitude, double altitudeMeters) {
		data = new mDATA();
		data2 = new mDATA_2();
		data.MAC = mAC;
		data.SSID = sSID;
		data.AuthMode = authMode;
		data.FirstSeen = firstSeen;
		this.CurrentLatitude = currentLatitude;
		this.CurrentLongitude = currentLongitude;
		this.AltitudeMeters = altitudeMeters;
	}

	/*
	 * Constructor that gets string of a row of csv,
	 * split it by ",",
	 * and gets the information it needs.
	 */
	public Element(String s) {
		data = new mDATA();
		data2 = new mDATA_2();
		String[] sss = s.split(",");
		data.MAC = sss[0];
		data.SSID = sss[1];
		data.AuthMode = sss[2];
		data.FirstSeen = sss[3];
		this.CurrentLatitude = Double.parseDouble(sss[6]);
		this.CurrentLongitude = Double.parseDouble(sss[7]);
		this.AltitudeMeters = Double.parseDouble(sss[8]);
	}

	/*
	 * This function prints the csv file.
	 */
	public String toString() {
		return "[MAC = " + MAC + 
				", SSID = " + SSID + 
				", AuthMode = " + AuthMode + 
				", FirstSeen = " + FirstSeen +  
				", CurrentLatitude = " + CurrentLatitude + 
				", CurrentLongitude = " + CurrentLongitude + ".]\n";
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}

	public String getSSID() {
		return SSID;
	}

	public void setSSID(String sSID) {
		SSID = sSID;
	}

	public String getAuthMode() {
		return AuthMode;
	}

	public void setAuthMode(String authMode) {
		AuthMode = authMode;
	}

	public String getFirstSeen() {
		return FirstSeen;
	}

	public void setFirstSeen(String firstSeen) {
		FirstSeen = firstSeen;
	}

	public double getCurrentLatitude() {
		return CurrentLatitude;
	}

	public void setCurrentLatitude(double currentLatitude) {
		CurrentLatitude = currentLatitude;
	}

	public double getCurrentLongitude() {
		return CurrentLongitude;
	}

	public void setCurrentLongitude(double currentLongitude) {
		CurrentLongitude = currentLongitude;
	}

	public double getAltitudeMeters() {
		return AltitudeMeters;
	}

	public void setAltitudeMeters(double altitudeMeters) {
		AltitudeMeters = altitudeMeters;
	}

	/*
	 * This function returns the point of the element (alt, lon, lat).
	 */
	@Override
	public Geom_element getGeom() {

		return new Point3D(this.getCurrentLatitude(), this.getAltitudeMeters(), this.getCurrentLongitude());
	}

	/*
	 * This function returns the data of the element.
	 */
	@Override
	public Meta_data getData() {	

		return this.data;
	}

	/*
	 * This function changes your location by a vector.
	 */
	@Override
	public void translate(Point3D vec) {

		Coords c = new Coords();
		Point3D np  = new Point3D(c.add((Point3D) this.getGeom(), vec));
		this.setCurrentLatitude(np.x());
		this.setCurrentLongitude(np.y());
		this.setAltitudeMeters(np.z());
	}

//	public Element ReadCsvFile(String file){
//
//		//		Set<GIS_element> Csv = new HashSet<>();
//		Scanner sc = null;
//		File fi = new File(file); //gets the file.
//		try { //reads the file.
//			sc = new Scanner(fi);
//		}
//		catch (FileNotFoundException exc) { //if file not found - Exception.
//			exc.printStackTrace();
//		}
//		sc.nextLine(); //the start.
//		String in = sc.nextLine();
//		in = sc.nextLine();
//		Element row = new Element(in);
//		//		while(sc.hasNext()) { //continues until there are no more lines in the file.
//		//			in = sc.nextLine(); //moves to the next line.
//		//			Element row = new Element(in);
//		//			Csv.add(row); //adds the line.
//		//		}
//		sc.close(); //closes the scanner.
//		return row;
//	}

	public static void createKML(Element e, String f) {

		Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		Placemark p = doc.createAndAddPlacemark();
		p.createAndSetTimeStamp().withWhen((e).getFirstSeen().replace(' ','T'));
		p.withDescription("Mac: "+(e).getMAC()+"\nCapabilities: "+(e).getAuthMode())
		.withName((e).getSSID()).withOpen(Boolean.TRUE).createAndSetPoint().
		addToCoordinates((e).getCurrentLongitude(),(e).getCurrentLatitude());
		try {
			kml.marshal(new File(f));  //create a kml file.
		} catch (FileNotFoundException ex) {

			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		Element a = new Element("40:65:a3:35:4c:c4,Efrat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],2017-12-01 10:49:08,1,-75,32.17218268216534,34.81446401702757,13.65040888895076,6,WIFI");
		a.createKML(a, "C:\\Users\\מעיין\\Desktop\\Ex2\\data\\element.kml");
	}

}
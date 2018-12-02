package GIS;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
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
		this.MAC = mAC;
		this.SSID = sSID;
		this.AuthMode = authMode;
		this.FirstSeen = firstSeen;
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
		String[] sss = s.split(",");
		data.MAC = sss[0];
		data.SSID = sss[1];
		data.AuthMode = sss[2];
		data.FirstSeen = sss[3];
		this.MAC = sss[0];
		this.SSID = sss[1];
		this.AuthMode = sss[2];
		this.FirstSeen = sss[3];
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
	
	
	
	//************************methods************************\\
	
	
	
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

	/*
	 * This function creates a kml file by an element,
	 * and puts it in a folder you give it (by a String).
	 */
	public static void createKML(Element e, String f) {

		Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		Placemark p = doc.createAndAddPlacemark();
		p.createAndSetTimeStamp().withWhen((e).getFirstSeen().replace(' ','T'));
		p.withDescription("Mac: " +(e).getMAC() + "\nCapabilities: " + (e).getAuthMode())
		.withName((e).getSSID()).withOpen(Boolean.TRUE).createAndSetPoint().
		addToCoordinates((e).getCurrentLongitude(),(e).getCurrentLatitude());
		try {
			kml.marshal(new File(f)); //create a kml file.
		}
		catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws ParseException {
		
//		Element a = new Element("40:65:a3:35:4c:c4,Efrat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],2017-12-01 10:49:08,1,-75,32.17218268216534,34.81446401702757,13.65040888895076,6,WIFI");
//		a.createKML(a, "C:\\Users\\מעיין\\Desktop\\Ex2\\data\\element.kml");
//		System.out.println(a.getData().getUTC());
	}

}
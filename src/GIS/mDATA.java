package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import Geom.Point3D;

/*
 * This class represents the Meta_data functions of an element.
 */
public class mDATA implements Meta_data{

	long getUTC;
	String toString;
	String MAC;
	String SSID;
	String AuthMode;	
	String FirstSeen;
	
	/*
	 * A default constructor.
	 */
	public mDATA() {
		
		this.getUTC = 0;
		this.toString = null;
	}
	
	/*
	 * A constructor that gets the data.
	 */
	public mDATA(long getUTC, String toString, Point3D get_Orientation) {

		this.getUTC = getUTC;
		this.toString = toString;
	}

	/*
	 * This function returns the Universal Time Clock associated with this data.
	 */
	@Override
	public long getUTC() throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = format.parse(this.FirstSeen);
		long millis = date.getTime();
		return millis;
	}

	/*
	 * This function returns a String representing this data.
	 */
	@Override
	public String toString() {

		return "[MAC = " + MAC + 
			    ", SSID = " + SSID + 
				", AuthMode = " + AuthMode + 
				", FirstSeen = " + FirstSeen + "]\n";
	}

	@Override
	public Point3D get_Orientation() {

		return null;
	}

	public long getGetUTC() {
		return getUTC;
	}

	public void setGetUTC(long getUTC) {
		this.getUTC = getUTC;
	}

	public String getToString() {
		return toString;
	}

	public void setToString(String toString) {
		this.toString = toString;
	}

	public Point3D getGet_Orientation() {
		return null;
	}

	public void setGet_Orientation(Point3D get_Orientation) {
		
	}

	/*
	 * Examples.
	 */
	public static void main(String[] args) throws ParseException {

//		Element el = new Element("40:65:a3:35:4c:c4,Efrat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],2017-12-01 10:49:08,1,-75,32.17218268216534,34.81446401702757,13.65040888895076,6,WIFI");
//	    System.out.println(el.getData().getUTC());
	}

}

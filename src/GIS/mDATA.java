package GIS;

import Geom.Point3D;

public class mDATA implements Meta_data{

	long getUTC;
	String toString;
	String MAC;
	String SSID;
	String AuthMode;	
	String FirstSeen;
	
	public mDATA(long getUTC, String toString, Point3D get_Orientation) {
//		super();
		this.getUTC = getUTC;
		this.toString = toString;

	}

	public mDATA() {
		
		this.getUTC = 0;
		this.toString = null;
	}

	/*
	 * This function returns the Universal Time Clock associated with this data.
	 */
	@Override
	public long getUTC() {

		//String[] arr = this.FirstSeen.split("-");
		//return Long.parseLong(arr[0]);
		return (Long) null;
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

	/*
	 * This function shows the orientation: yaw, pitch and roll associated with this data.
	 */
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

	public static void main(String[] args) {

		Element el = new Element("40:65:a3:35:4c:c4,Efrat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],2017-12-01 10:49:08,1,-75,32.17218268216534,34.81446401702757,13.65040888895076,6,WIFI");
	    System.out.println(el.getData().toString());
	}

}

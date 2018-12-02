package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import Geom.Point3D;

/*
 * This class represents the Meta_data functions of a layer.
 */
public class mDATA_2 implements Meta_data {

	long getUTC;
	String toString;
	ArrayList<mDATA> data = new ArrayList<>();
	
	/*
	 * A default constructor.
	 */
	public mDATA_2() {

		this.getUTC = 0;
		this.toString = null;
	}

	/*
	 * A constructor that gets an array of mData (Meta_data of elements). 
	 */
	public mDATA_2(ArrayList<mDATA> d) {
		
		for(mDATA it: d) {
			this.data.add(it);
		}
	}

	/*
	 * This function returns a String representing this data.
	 */
	@Override
	public long getUTC() throws ParseException {

		String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.US);
		format.setTimeZone(TimeZone.getTimeZone("UTC"));
		Date date = format.parse(timeStamp);
		long millis = date.getTime();
		return millis;
	}

	@Override
	public Point3D get_Orientation() {
		
		return null;
	}
	
	/*
	 * This function gets the data of a layer.
	 */
	@Override
	public String toString() {

		return this.data.toString();
	}

}

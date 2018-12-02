package GIS;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.TimeZone;
import Geom.Point3D;

/*
 * This class represents the Meta_data functions of a project.
 */
public class mDATA_3 implements Meta_data {

	long getUTC = 0;
	String toString;
	Set<GIS_layer> Project;
	ArrayList<mDATA_2> data = new ArrayList<>();
	
	/*
	 * A default constructor.
	 */
	public mDATA_3() {

		this.getUTC = 0;
		this.toString = null;
		this.Project = null;
	}
	
	/*
	 * A constructor that gets an array of mData_2 (Meta_data of layers). 
	 */
	public mDATA_3(ArrayList<mDATA_2> d) {
		
		for(mDATA_2 it: d) {
			this.data.add(it);
		}
	}

	/*
	 * This functions gets the time stamp in millis.
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
	 * This function returns a String representing this data.
	 */
	@Override
	public String toString() {

		return this.data.toString();
	}

}
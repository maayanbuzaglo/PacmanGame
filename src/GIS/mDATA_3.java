package GIS;

import java.util.ArrayList;
import java.util.Set;
import Geom.Point3D;

public class mDATA_3 implements Meta_data {

	long getUTC;
	String toString;
	Set<GIS_layer> Project;
	ArrayList<mDATA_2> data = new ArrayList<>();
	
	public mDATA_3() {

		this.getUTC = 0;
		this.toString = null;
		this.Project = null;
	}
	
	public mDATA_3(ArrayList<mDATA_2> d) {
		
		for(mDATA_2 it: d) {
			this.data.add(it);
		}
	}

	@Override
	public long getUTC() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Point3D get_Orientation() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String toString() {

		return this.data.toString();
	}

}
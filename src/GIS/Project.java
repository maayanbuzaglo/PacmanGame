package GIS;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;

public class Project implements GIS_project {
	
	Element el;
	mDATA_3 data3 = new mDATA_3();
	ArrayList<mDATA_2> data = new ArrayList<>();
	Set<GIS_layer> element = new HashSet<>();
	
	public Project(Set<GIS_layer> elementList) {

		for(GIS_layer it: elementList) {
			this.element.add(it);
		}
		for(GIS_layer it: elementList) {
			this.data.add((mDATA_2) it.get_Meta_data());
		}	
	}
	
	public Project() {
		
	}

	/*
	 * This function adds an GIS_layer to the Set.
	 */
	@Override
	public boolean add(GIS_layer e) {
		
		return element.add(e);
	}

	/*
	 * This function adds collection of GIS_layer to the Set.
	 */
	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {

		return element.addAll(c);
	}

	/*
	 * This function clears the Set.
	 */
	@Override
	public void clear() {
		
		element.clear();
	}

	/*
	 * This function checks if the Set contains a GIS_layer.
	 */
	@Override
	public boolean contains(Object o) {
		
		return element.contains(o);
	}

	/*
	 * This function checks if the Set contains GIS_layers.
	 */
	@Override
	public boolean containsAll(Collection<?> c) {

		return element.containsAll(c);
	}

	/*
	 * This function checks if the Set is empty.
	 */
	@Override
	public boolean isEmpty() {

		return element.isEmpty();
	}

	/*
	 * This function returns an iterator.
	 */
	@Override
	public Iterator<GIS_layer> iterator() {

		return element.iterator();
	}

	/*
	 * This function removes a Layer from the Set.
	 */
	@Override
	public boolean remove(Object o) {

		return element.remove(o);
	}

	/*
	 * This function removes Layers from the Set.
	 */
	@Override
	public boolean removeAll(Collection<?> c) {

		return element.removeAll(c);
	}

	/*
	 * This function retains the Set.
	 */
	@Override
	public boolean retainAll(Collection<?> c) {

		return element.retainAll(c);
	}

	/*
	 * This function gets the size of the Set.
	 */
	@Override
	public int size() {

		return element.size();
	}

	@Override
	public Object[] toArray() {

		return element.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {

		return element.toArray(a);
	}

	@Override
	public Meta_data get_Meta_data() {

		mDATA_3 d = new mDATA_3(this.data);
		return d;
	}
	
	public Set<GIS_layer> getElement() {
		
		return element;
	}

	public void setElement(Set<GIS_layer> element) {
		
		this.element = element;
	}
	
	public Set<GIS_layer> ReadCsvFile(String file){

		Set<GIS_layer> Csv = new HashSet<>();
		Set<GIS_element> eList = new HashSet<>();
		Scanner sc = null;
		File fi = new File(file); //gets the file.
		try { //reads the file.
			sc = new Scanner(fi);
		}
		catch (FileNotFoundException exc) { //if file not found - Exception.
			exc.printStackTrace();
		}
		sc.nextLine(); //the start.
		String in = sc.nextLine();
		in = sc.nextLine();
		while(sc.hasNext()) { //continues until there are no more lines in the file.
			in = sc.nextLine(); //moves to the next line.
			for(GIS_element it: eList) {
				
			}
			Layer rows = new Layer(eList);
			Csv.add(rows); //adds the line.
		}
		sc.close(); //closes the scanner.
		return Csv;
	}

	public static void createKML(Set<GIS_element> list, String f) {

		Kml kml = new Kml();
		Document doc = kml.createAndSetDocument();
		for (GIS_element it: list) { //The iterator runs on a csv file (List of Row_Locate).
			Placemark p = doc.createAndAddPlacemark();
			p.createAndSetTimeStamp().withWhen(((Element) it).getFirstSeen().replace(' ','T'));
			p.withDescription("Mac: "+((Element) it).getMAC()+"\nCapabilities: "+((Element) it).getAuthMode())
			.withName(((Element) it).getSSID()).withOpen(Boolean.TRUE).createAndSetPoint().
			addToCoordinates(((Element) it).getCurrentLongitude(),((Element) it).getCurrentLatitude());
		}
		try {
			kml.marshal(new File(f));  //create a kml file.
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
	}
	
	/*
	 * Examples.
	 */
	public static void main(String[] args) {
		
		Element a = new Element("40:65:a3:35:4c:c4,Efrat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],2017-12-01 10:49:08,1,-75,32.17218268216534,34.81446401702757,13.65040888895076,6,WIFI");
		Element b = new Element("08:97:58:32:69:c6,Volvo247,[WPA2-PSK-CCMP][WPS][ESS],2017-12-01 10:49:14,4,-81,32.172209259560766,34.814448298318844,15.376435938028056,8,WIFI");
		Element c = new Element("09:97:58:32:69:c6,Volvbbbo247,[WPA2-PSK-CCMP][WPS][ESS],2017-12-01 10:49:14,4,-81,32.172209259560766,34.814448298318844,16.376435938028056,8,WIFI");

		System.out.println(a.getData().toString());

		Set<GIS_element> element = new HashSet<>();
		element.add(a);
		element.add(b);

		Layer l = new Layer(element);
		System.out.println(l.get_Meta_data().toString());
		Set<GIS_element> element2 = new HashSet<>();
		element2.add(c);
		Layer l2 = new Layer(element2);

		
		Set<GIS_layer> layer = new HashSet<>();
		layer.add(l);
		layer.add(l2);
		
		Project p = new Project(layer);
		System.out.println(p.get_Meta_data().toString());
	}

}

package GIS;

import GIS.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import java.util.HashSet;

/*
 * This class represents an object of Element's array (csv file).
 */
public class Layer implements GIS_layer{

	mDATA_2 data2 = new mDATA_2();
	Set<GIS_element> element = new HashSet<>();
	ArrayList<mDATA> data = new ArrayList<>();

	/*
	 * A default constructor.
	 */
	public Layer() {

	}

	/*
	 * A constructor that gets a set of elements.
	 */
	public Layer(Set<GIS_element> elementList) {

		for(GIS_element it: elementList) {
			this.element.add(it);
		}
		for(GIS_element it: elementList) {
			this.data.add((mDATA) it.getData());
		}
	}

	/*
	 * This function adds an GIS_element(row of csv) to the Set.
	 */
	@Override
	public boolean add(GIS_element arg0) {

		return element.add(arg0);
	}

	/*
	 * This function adds collection of GIS_element(couple of rows of csv) to the Set.
	 */
	@Override
	public boolean addAll(Collection<? extends GIS_element> arg0) {

		return element.addAll(arg0);
	}

	/*
	 * This function clears the Set.
	 */
	@Override
	public void clear() {

		element.clear();
	}

	/*
	 * This function checks if the Set contains an Element.
	 */
	@Override
	public boolean contains(Object arg0) {

		return element.contains(arg0);
	}

	/*
	 * This function checks if the Set contains Elements.
	 */
	@Override
	public boolean containsAll(Collection<?> arg0) {

		return element.containsAll(arg0);
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
	public Iterator<GIS_element> iterator() {

		return element.iterator();
	}

	/*
	 * This function removes a row from the Set.
	 */
	@Override
	public boolean remove(Object arg0) {

		return element.remove(arg0);
	}

	/*
	 * This function removes couple of rows from the Set.
	 */
	@Override
	public boolean removeAll(Collection<?> arg0) {

		return element.removeAll(arg0);
	}

	/*
	 * This function retains the Set.
	 */
	@Override
	public boolean retainAll(Collection<?> arg0) {

		return element.retainAll(arg0);
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
	public <T> T[] toArray(T[] arg0) {

		return element.toArray(arg0);
	}

	/*
	 * This function fets the Meta data functions of this layer.
	 */
	@Override
	public Meta_data get_Meta_data() {

		mDATA_2 d = new mDATA_2(this.data);
		return d;
	}

	public Set<GIS_element> getElement() {

		return element;
	}

	public void setElement(Set<GIS_element> element) {

		this.element = element;
	}

	/*
	 * This function gets a csv file (String)
	 * split the elements by ",".
	 * create for every line an element,
	 * and returns an array list of elements.
	 */
	public Set<GIS_element> ReadCsvFile(String file){

		Set<GIS_element> Csv = new HashSet<>();
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
			Element row = new Element(in);
			Csv.add(row); //adds the line.
		}
		sc.close(); //closes the scanner.
		return Csv;
	}

	/**
	 * This function creates a kml file.
	 * @param list a list of element.
	 * @param f represent the file.
	 */
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
	public static void main(String[] args) throws ParseException {

//		Layer r = new Layer();
//		Set<GIS_element> Csv = r.ReadCsvFile("C:\\Users\\מעיין\\Desktop\\Ex2\\data\\WigleWifi_20171201110209.csv");
//		for (GIS_element it: Csv) {
//			System.out.println(it.toString());
//		}
//		System.out.println(r.get_Meta_data().toString());
//		r.createKML(Csv, "C:\\Users\\מעיין\\Desktop\\Ex2\\data\\null.kml");
//		
//		System.out.println(r.get_Meta_data().toString());
//		
//		Element a = new Element("40:65:a3:35:4c:c4,Efrat,[WPA-PSK-CCMP+TKIP][WPA2-PSK-CCMP+TKIP][ESS],2017-12-01 10:49:08,1,-75,32.17218268216534,34.81446401702757,13.65040888895076,6,WIFI");
//		Element b = new Element("08:97:58:32:69:c6,Volvo247,[WPA2-PSK-CCMP][WPS][ESS],2017-12-01 10:49:14,4,-81,32.172209259560766,34.814448298318844,15.376435938028056,8,WIFI");
//		Element c = new Element("09:97:58:32:69:c6,Volvbbbo247,[WPA2-PSK-CCMP][WPS][ESS],2017-12-01 10:49:14,4,-81,32.172209259560766,34.814448298318844,16.376435938028056,8,WIFI");
//
//		System.out.println(a.getData().toString());
//
//		Set<GIS_element> element = new HashSet<>();
//		element.add(a);
//		element.add(b);
//
//		Layer l = new Layer(element);
//		System.out.println(l.data);
//		System.out.println(l.get_Meta_data().toString());
//
//		System.out.println(r.toString());
//
//		System.out.println(l.isEmpty());
//
//		System.out.println(l.size());
//
//		System.out.println(l.contains(a));
//
//		System.out.println(l.get_Meta_data().toString());
//
//		System.out.println(l.isEmpty());
//
//		System.out.println(b.getData().toString());
//
//		System.out.println(l.get_Meta_data().toString());
//
//		System.out.println(l.remove(a));
	}

}
package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * This class represents an object of Layer's array (csv file).
 */
public class Project implements GIS_project {
	
	mDATA_3 data3 = new mDATA_3();
	Set<GIS_layer> element = new HashSet<>();
	Set<GIS_element> e = new HashSet<>();
	ArrayList<mDATA_2> data = new ArrayList<>();

	/*
	 * A default constructor.
	 */
	public Project() {
		
	}
	
	/*
	 * A constructor that gets a set of layers.
	 */
	public Project(Set<GIS_layer> elementList) {

		for(GIS_layer it: elementList) {
			this.element.add(it);
		}
		for(GIS_layer it: elementList) {
			this.data.add((mDATA_2) it.get_Meta_data());
		}	
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

	/**
	 * This function creates a kml file.
	 * @param list represent a list of GIS layer.
	 * @param f represent the file name.
	 */
	public static void createKML(Set<GIS_layer> list, String f) {

		Set<GIS_element> e = new HashSet<>();
		for(GIS_layer it: list) {
			Layer l = new Layer(it);
			l.getElement();
			e.addAll(l.getElement());
		}
		Layer p = new Layer(e);
		p.createKML(e, f);
	}
	
	/*
	 * Examples.
	 */
	public static void main(String[] args) {
		
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
//		System.out.println(l.get_Meta_data().toString());
//		Set<GIS_element> element2 = new HashSet<>();
//		element2.add(c);
//		Layer l2 = new Layer(element2);
//
//		Set<GIS_layer> layer = new HashSet<>();
//		layer.add(l);
//		layer.add(l2);
//		
//		Project p = new Project(layer);
//		System.out.println(p.get_Meta_data().toString());
//		
//		p.createKML(layer, "C:\\Users\\מעיין\\Desktop\\Ex2\\data\\myKML.kml");
	}

}

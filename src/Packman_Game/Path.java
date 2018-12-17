package Packman_Game;

import java.util.ArrayList;

/*
 * This class represents a track consisting of a collection of points.
 */
public class Path {

	ArrayList<Line> path;
	double distance;
	ArrayList<Fruit> fruit_list;

	/*
	 * An empty constructor.
	 */
	public Path() {

		this.path = new ArrayList<Line>();
		this.distance = 0;
		this.fruit_list = new ArrayList<Fruit>();
	}

	public Path(Pacman pac) {

		this.path = new ArrayList<Line>();
		this.distance = 0;
		this.fruit_list = new ArrayList<Fruit>();
	}

	/*
	 * Constructor.
	 */
	public Path(ArrayList<Line> path) {

		this.path = path;
		for(Line it: path) {
			this.distance += it.distance;
		}
	}

	@Override
	public String toString() {
		String s = "Path: ";
		for (int i = 0; i < path.size(); i++) {
			s += path.get(i).toString()+ ", ";		
		}
		return s;
	}

	public ArrayList<Line> getPath() {
		return this.path;
	}

	public void setPath(ArrayList<Line> path) {
		this.path = path;
	}

	public double getDis() {
		return distance;
	}

	public void setDis(double distance) {
		this.distance = distance;
	}

	public void add(Line line) {
		this.path.add(line);
	}

}

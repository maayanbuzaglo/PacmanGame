package Pacman_game;

import java.util.ArrayList;

/*
 * This class represents a track consisting of a collection of points.
 * @author maayan
 * @author nahama
 */
public class Path {

	ArrayList<Line> path;
	double distance;

	/*
	 * An empty constructor.
	 */
	public Path() {

		this.path = new ArrayList<Line>();
		this.distance = 0;
	}

	public Path(Pacman pac) {

		this.path = new ArrayList<Line>();
		this.distance = 0;
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

	/*
	 * This function adds a line to the path.
	 */
	public void add(Line line) {
		this.path.add(line);
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

}

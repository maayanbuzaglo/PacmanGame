package Packman_Game;

import java.util.ArrayList;

/*
 * This class represents a track consisting of a collection of points.
 */
public class Path {
	
	ArrayList<Line> path;
	double distance;
	
	/*
	 * An empty constructor.
	 */
	public Path() {
		
		this.path = null;
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

	@Override
	public String toString() {
		return "Path [path = " + path +
				", Distance = " + distance + "]\n";
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

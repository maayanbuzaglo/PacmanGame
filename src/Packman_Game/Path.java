package Packman_Game;

import java.util.ArrayList;

/*
 * This class represents a track consisting of a collection of points.
 */
public class Path {

	ArrayList<Line> path;
	double distance;
	Pacman pacman;
	ArrayList<Fruit> fruit_list;

	/*
	 * An empty constructor.
	 */
	public Path() {

		this.path = new ArrayList<Line>();
		this.distance = 0;
		this.pacman = null;
		this.fruit_list = new ArrayList<Fruit>();

	}

	public Path(Pacman pac) {

		this.path = new ArrayList<Line>();
		this.distance = 0;
		this.pacman = new Pacman(pac);
		this.fruit_list = new ArrayList<Fruit>();
	}

	public Pacman getPacman() {
		return this.pacman;
	}

	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
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
		return "Path [Path = " + path.toString() + 
				", Distance = " + distance + 
				", Pacman =" + pacman.toString() +
				", Fruit list = " + fruit_list.toString() + "]\n";
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

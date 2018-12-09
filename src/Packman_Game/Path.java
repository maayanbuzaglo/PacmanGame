package Packman_Game;

import java.util.ArrayList;

public class Path {
	
	ArrayList<Line> path;
	double dis;
	
	public Path(ArrayList<Line> path) {
		
		this.path = path;
		for(Line it: path)
		{
			this.dis += it.dis;
		}
	}
	
	public Path() {
		
		this.path = null;
		this.dis = 0;
	}

	@Override
	public String toString() {
		return "Path [path = " + path +
				", dis = " + dis + "]";
	}

	public ArrayList<Line> getPath() {
		return path;
	}

	public void setPath(ArrayList<Line> path) {
		this.path = path;
	}

	public double getDis() {
		return dis;
	}

	public void setDis(double dis) {
		this.dis = dis;
	}
}

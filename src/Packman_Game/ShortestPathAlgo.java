package Packman_Game;
import java.util.ArrayList;

import Coords.Coords;
import Geom.Point3D;
import Packman_Game.Game;

public class ShortestPathAlgo {

	/*
	 * This function gets the closest fruit to the pacman.
	 */
	public static ArrayList<Path> closestFruit(Game g) {

		Coords c = new Coords();
		ArrayList<Path> path_list = new ArrayList<Path>();
		ArrayList<SaveData> data = new ArrayList<SaveData>();
		for (Pacman it: g.getPacman_list()) {
			Path path = new Path(it);
			path_list.add(path);
		}
		Line l = new Line();
		double newTime;
		while (!g.getFruit_list().isEmpty()) {
			Pacman p = g.getPacman_list().get(0);
			Fruit f = g.getFruit_list().get(0);
			double minTime = c.distance2d(p.getLocation(), f.getLocation()) / p.speed;
//			double minTime = p.getLocation().distance2D(f.getLocation()) / p.speed;
			long id = f.getID();
			int weight = f.getWeight();
			Point3D point = f.getLocation();
			int indexFruit = 0;
			int j;
			for(Pacman it2: g.getPacman_list()) {
				j=0;
				for(Fruit it: g.getFruit_list()) {
					newTime = c.distance2d(it2.location, it.getLocation()) / p.speed;
//					newTime = it2.location.distance2D() / p.speed;
					System.out.println(minTime+"-----"+newTime);
					if(minTime > newTime) {
						f = new Fruit(it);
						minTime = newTime;
						id = it.getID();
						weight = it.getWeight();
						point = it.getLocation();
						indexFruit = j;
					}
					j++;
				}				
				data.add(new SaveData(it2, f, minTime));
			}
			int indexPacman = 0;
			double mostMinTime = data.get(0).getTime();
			for (int i = 0; i < g.Pacman_list.size(); i++) {
				if(data.get(i).getTime() <= mostMinTime) {
					mostMinTime = data.get(i).getTime();
					l.point1 = new Point3D(data.get(i).getPacman().getLocation());
					l.point2 = new Point3D(data.get(i).getFruit().getLocation());
					l.distance = mostMinTime;
					indexPacman = i;
				}
			}
			System.out.println(indexPacman);
			g.Pacman_list.get(indexPacman).setTime(g.Pacman_list.get(indexPacman).getTime() + mostMinTime);
			for(Path it: path_list) {
				if(it.getPacman().getID() == g.Pacman_list.get(indexPacman).getID()) {
					it.add(l);
					g.Pacman_list.get(indexPacman).setLocation(g.getFruit_list().get(indexFruit).getLocation());
					g.getFruit_list().remove(indexFruit);
				}
			}
			
		}
		return path_list;
	}

	public static void main(String[] args) {

		Game g = new Game();
		g.readCsv("C:\\Users\\מעיין\\eclipse-workspace\\OopNavigtion\\data\\game_1543693822377.csv");
		for (Pacman it1: g.Pacman_list) {
			System.out.println(it1.toString());
		}
		for (Fruit it: g.Fruit_list) {
			System.out.println(it.toString());
		}
		ArrayList<Path> ans = closestFruit(g);
		for(Path it: ans) {
			System.out.println(it.toString());
			System.out.println();
		}
	}
}	


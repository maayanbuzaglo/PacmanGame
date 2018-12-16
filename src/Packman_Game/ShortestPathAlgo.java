package Packman_Game;
import java.util.ArrayList;

import javax.sound.sampled.Line;

import org.junit.experimental.max.MaxCore;

import Coords.Coords;
import Geom.Point3D;
import Packman_Game.Game;

public class ShortestPathAlgo {

	/*
	 * This function gets the closest fruit to the pacman.
	 */
	//	public static ArrayList<Path> closestFruit(Game g) {
	//
	//		Coords c = new Coords();
	//		ArrayList<Path> path_list = new ArrayList<Path>();
	//		ArrayList<SaveData> data = new ArrayList<SaveData>();
	//		for (Pacman it: g.getPacman_list()) {
	//			Path path = new Path(it);
	//			path_list.add(path);
	//		}
//			Line l = new Line();
	//		double newTime;
	//		while (!g.getFruit_list().isEmpty()) {
	//			Pacman p = g.getPacman_list().get(0);
	//			Fruit f = g.getFruit_list().get(0);
	//			double minTime = c.distance2d(p.getLocation(), f.getLocation()) / p.speed;
	////			double minTime = p.getLocation().distance2D(f.getLocation()) / p.speed;
	//			long id = f.getID();
	//			int weight = f.getWeight();
	//			Point3D point = f.getLocation();
	//			int indexFruit = 0;
	//			int j;
	//			for (int i = 0; i < g.Pacman_list.size(); i++) {
	//				j=0;
	//				for(Fruit it: g.getFruit_list()) {
	//					newTime = c.distance2d(g.Pacman_list.get(i).location, it.getLocation()) / p.speed;
	////					newTime = it2.location.distance2D() / p.speed;
	//					System.out.println(minTime+"-----"+newTime);
	//					if(minTime > newTime + g.getPacman_list().get(i).getTime()) {
	//						f = new Fruit(it);
	//						minTime = newTime + g.getPacman_list().get(i).getTime();
	//						id = it.getID();
	//						weight = it.getWeight();
	//						point = it.getLocation();
	//						indexFruit = j;
	//					}
	//					j++;
	//				}				
	//				data.add(new SaveData(g.Pacman_list.get(i), f, minTime));
	//			}
	//			int indexPacman = 0;
	//			double mostMinTime = data.get(0).getTime();
	//			for (int i = 0; i < g.Pacman_list.size(); i++) {
	//				if(data.get(i).getTime() <= mostMinTime) {
	//					mostMinTime = data.get(i).getTime();
	//					l.point1 = new Point3D(data.get(i).getPacman().getLocation());
	//					l.point2 = new Point3D(data.get(i).getFruit().getLocation());
	//					l.distance = mostMinTime;
	//					indexPacman = i;
	//				}
	//			}
	//			System.out.println(indexPacman);
	//			g.Pacman_list.get(indexPacman).setTime(g.Pacman_list.get(indexPacman).getTime() + mostMinTime);
	//			path_list.get(indexPacman).getPacman().setTime(g.Pacman_list.get(indexPacman).getTime());
	//			for(Path it: path_list) {
	//				if(it.getPacman().getID() == g.Pacman_list.get(indexPacman).getID()) {
	//					it.add(l);
	//					g.Pacman_list.get(indexPacman).setLocation(g.getFruit_list().get(indexFruit).getLocation());
	//					g.getFruit_list().remove(indexFruit);
	//				}
	//			}
	//			data = new ArrayList<SaveData>();
	//		}
	//		return path_list;
	//	}

	public static ArrayList<Path> closestFruit(Game g) {

		Line l;
		ArrayList<Fruit> fruitLeft = new ArrayList<Fruit>(); // array list of the fruit that left.
		for(Fruit it: g.getFruit_list()) 
		{
			fruitLeft.add(it);
		}
		Coords c = new Coords();
		ArrayList<Path> path_list = new ArrayList<Path>();
		ArrayList<SaveData> data_list = new ArrayList<SaveData>();
		double time;
		Fruit f = null;
		
		for (int i = 0; i < g.Pacman_list.size(); i++) {
			Path path = new Path(g.getPacman_list().get(i));
			path_list.add(path);
		}
		while (!fruitLeft.isEmpty()) { //until there is stil fruit to eat.
			int indexFruit = 0;
			int indexPacman = 0;
			data_list = new ArrayList<SaveData>();
			for (int i = 0; i < g.Pacman_list.size(); i++) {
				double minTime = Double.MAX_VALUE;
				for (int j = 0; j < fruitLeft.size(); j++) {
					double speedP = g.Pacman_list.get(i).getSpeed(); 
					time = c.distance2d(fruitLeft.get(j).getLocation(), g.Pacman_list.get(i).getLocation()) / speedP; // calculait the time.
					if ((time + g.Pacman_list.get(i).getTime()) < minTime)
					{
						f = new Fruit(fruitLeft.get(j));
						minTime = time + g.getPacman_list().get(i).getTime();
						indexFruit = j;
					}
				}
				data_list.add(new SaveData(g.getPacman_list().get(i), f, minTime, indexFruit)); //the most closer fruit to pacman i.
			}

			SaveData eatData = new SaveData(data_list.get(0));

			// checking which pacman should eat.
			for (int i = 0; i < data_list.size(); i++) {
				if (eatData.getTime() >= data_list.get(i).getTime())
				{
					eatData.setPacman(data_list.get(i).getPacman());
					eatData.setFruit(data_list.get(i).getFruit());
					eatData.setTime(data_list.get(i).getTime());
					eatData.setIndexFruit(data_list.get(i).getIndexFruit());
					indexPacman = i;

				}
			}
//			l = new Line(eatData.getPacman().getLocation(), fruitLeft.get(eatData.getIndexFruit()).getLocation());
//			g.Pacman_list.get(indexPacman).getPat().add(l); //add the line to the pacman path.
			g.Pacman_list.get(indexPacman).setTime(eatData.getTime()); //seting the time of the pacman that ate.
			g.Pacman_list.get(indexPacman).setLocation(fruitLeft.get(eatData.getIndexFruit()).getLocation()); //moving the pacman to the location of the fruit.
			fruitLeft.remove(eatData.getIndexFruit()); // removing the fruit.
			System.out.println(eatData.toString());
		}
		return null;

	}




	public static void main(String[] args) {

		Game g = new Game();
		g.readCsv("C:\\Users\\nahama\\eclipse-workspace\\OopNavigtion\\game.csv");
		for (Pacman it1: g.Pacman_list) {
			System.out.println(it1.toString());
		}
		for (Fruit it: g.Fruit_list) {
			System.out.println(it.toString());
		}
		ArrayList<Path> ans = closestFruit(g);
		//		for(Path it: ans) {
		//			System.out.println(it.toString());
		//			System.out.println();
		//		}
	}
}	


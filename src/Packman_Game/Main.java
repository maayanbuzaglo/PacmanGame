package Packman_Game;

import java.util.ArrayList;

import javax.swing.JFrame;




public class Main 
{
	public static void main(String[] args)
	{
		//		MainWindow window = new MainWindow();
		//		window.setVisible(true);
		//		window.setSize(window.myImage.getWidth(),window.myImage.getHeight());
		//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//		Pacman p = new Pacman();
		//		ArrayList<Pacman> Csv = p.ReadCsvFile("C:\\Users\\מעיין\\Desktop\\data\\game_1543684662657.csv");
		//		for (Pacman it: Csv) {
		//			System.out.println(it.toString());
		//		}

		//		Fruit f = new Fruit();
		//		ArrayList<Fruit> Csv = f.ReadCsvFile("C:\\Users\\מעיין\\Desktop\\data\\game_1543684662657.csv");
		//		for (Fruit it: Csv) {
		//			System.out.println(it.toString());
		//		}
		//		r.createKML(Csv,"C:\\Users\\מעיין\\Desktop\\Ex2\\data\\f.kml");

		Game g = new Game("C:\\Users\\מעיין\\Desktop\\data\\game_1543684662657.csv");
		for (Pacman it1: g.Pacman_list) {
			System.out.println(it1.toString());
		}
		for (Fruit it: g.Fruit_list) {
			System.out.println(it.toString());
		}

	}
}

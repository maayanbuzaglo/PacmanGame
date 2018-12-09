package Packman_Game;

import java.util.ArrayList;

public class Game {
	
	ArrayList<Pacman> Pacman_list;
	ArrayList<Fruit> Fruit_list;
	
	public Game(ArrayList<Pacman> pacman_list, ArrayList<Fruit> fruit_list) {
		
		this.Pacman_list = pacman_list;
		this.Fruit_list = fruit_list;
	}
	
	public Game() {
		
		this.Pacman_list = null;
		this.Fruit_list = null;
	}
	
	public Game(String file) {
		
		Pacman p  = new Pacman();
		this.Pacman_list = p.ReadCsvFile(file);
		Fruit f = new Fruit();
		this.Fruit_list = f.ReadCsvFile(file);
	}

	public ArrayList<Pacman> getPacman_list() {
		return Pacman_list;
	}

	public void setPacman_list(ArrayList<Pacman> pacman_list) {
		Pacman_list = pacman_list;
	}

	public ArrayList<Fruit> getFruit_list() {
		return Fruit_list;
	}

	public void setFruit_list(ArrayList<Fruit> fruit_list) {
		Fruit_list = fruit_list;
	}
	
	

}

package Packman_Game;

public class SaveData {
	
	Pacman pacman;
	Fruit fruit;
	double time;

	public SaveData() {
		
		this.pacman = null;
		this.fruit = null;
		this.time = 0;
	}

	public SaveData(Pacman pacman, Fruit fruit,	double time) {
		
		this.pacman = new Pacman(pacman);
		this.fruit = fruit;
		this.time = time;
	}
	
	public Pacman getPacman() {
		return pacman;
	}

	public void setPacman(Pacman pacman) {
		this.pacman = pacman;
	}

	public Fruit getFruit() {
		return fruit;
	}

	public void setFruit(Fruit fruit) {
		this.fruit = fruit;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
	
}

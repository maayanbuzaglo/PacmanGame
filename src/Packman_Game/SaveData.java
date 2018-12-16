package Packman_Game;

public class SaveData {

	Pacman pacman;
	Fruit fruit;
	double time;
	int indexFruit;

	public SaveData() {

		this.pacman = null;
		this.fruit = null;
		this.time = 0;
		this.indexFruit = 0;
	}

	public SaveData(Pacman pacman, Fruit fruit,	double time, int indexFruit) {

		this.pacman = new Pacman(pacman);
		this.fruit = fruit;
		this.time = time;
		this.indexFruit = indexFruit;

	}

	public SaveData(SaveData s) {

		this.pacman = new Pacman(s.getPacman());
		this.fruit = s.getFruit();
		this.time = s.getTime();
		this.indexFruit = s.indexFruit;
	}
	@Override
	public String toString() {
		return "SaveData [pacman=" + pacman.getID() + ", fruit=" + fruit.getID() + ", time=" + time + ", indexFruit=" + indexFruit + "]";
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

	public int getIndexFruit() {
		return this.indexFruit;
	}

	public void setIndexFruit(int indexFruit) {
		this.indexFruit = indexFruit;
	}

}

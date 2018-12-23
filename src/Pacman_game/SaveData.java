package Pacman_game;

/*
 * This class represents the data of the pacman should eat next,
 * the fruit should be eaten next,
 * and the time it took.
 * @author maayan
 * @author nahama
 */
public class SaveData {

	Pacman pacman;
	Fruit fruit;
	double time;
	int indexFruit;

	/*
	 * An empty constructor.
	 */
	public SaveData() {

		this.pacman = null;
		this.fruit = null;
		this.time = 0;
		this.indexFruit = 0;
	}

	/*
	 * Constructor.
	 */
	public SaveData(Pacman pacman, Fruit fruit,	double time, int indexFruit) {

		this.pacman = new Pacman(pacman);
		this.fruit = fruit;
		this.time = time;
		this.indexFruit = indexFruit;
	}

	/*
	 * Constructor.
	 */
	public SaveData(SaveData s) {

		this.pacman = new Pacman(s.getPacman());
		this.fruit = s.getFruit();
		this.time = s.getTime();
		this.indexFruit = s.indexFruit;
	}
	
	@Override
	public String toString() {
		return "Step [Pacman = " + pacman.getID() +
				", Fruit = " + fruit.getID() +
				", Time = " + time +
				", Fruit index = " + indexFruit + "]";
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

package Pacman_game;

/*
 * This class represents a pixel in the game.
 * @author maayan
 * @author nahama
 */
public class Pixel {

	private double x;
	private double y;

	/*
	 * An empty constructor.
	 */
	public Pixel() {
		
		this.x = 0;
		this.y = 0;
	}
	
	/*
	 * Constructor.
	 */
	public Pixel(double dx, double dy) {
		
		this.x = dx;
		this.y = dy;
	}

	/*
	 * Constructor.
	 */
	public Pixel(Pixel p) {
		
		this.x = p.x;
		this.y = p.y;
	}

	/*
	 * This function checks if 2 pixels are equal.
	 */
	public boolean equals(Pixel arg0) {
		
		if (this.getX() != arg0.getX() || this.getY() != arg0.getY())
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Pixel [X = " + x +
				", Y = " + y + "]\n";
	}

	public double getX() {
		return this.x;
	}

	public double getY() {

		return this.y;
	}
	
	public void setX(double d) {
		this.x = d;
	}

	public void setY(double d) {
		this.y = d;
	}

}
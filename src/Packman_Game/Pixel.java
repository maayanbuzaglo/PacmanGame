package Packman_Game;

public class Pixel {

	private double x;
	private double y;

	public Pixel(double x, double y) {
		
		this.x = x;
		this.y = y;
	}

	public boolean equals(Pixel arg0) {
		
		if (getX() != arg0.getX() || getY() != arg0.getY())
			return false;

		return true;
	}

	public double getX() {
		return this.x;
	}

	public double getY() {

		return this.y;
	}
	
	public void setX(double x) {
		this.x = x;
	}

	public void setY(double y) {
		this.y = y;
	}

	public Pixel move(Pixel p)  {
		
		double pX = this.getX() + p.getX();
		double pY = this.getY() + p.getY();
		Pixel ans = new Pixel (pX, pY);
		return ans;
	}
	
	public Pixel subtract(Pixel p) {
		
		double pX = this.getX() - p.getX();
		double pY = this.getY() - p.getY();
		Pixel ans = new Pixel (pX, pY);
		return ans;
	}

	@Override
	public String toString()
	{
		return " x value : " + this.getX() + " y value : " + this.getY();
	}

}
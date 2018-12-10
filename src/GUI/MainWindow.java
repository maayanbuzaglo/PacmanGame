package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class MainWindow extends JFrame implements MouseListener {
	
	public BufferedImage background;
	public BufferedImage pacmanImage;
	
	public MainWindow() {
		
		initGUI();		
		this.addMouseListener(this); 
	}
	
	private void initGUI() {
		
		MenuBar menuBar = new MenuBar();
		Menu menu = new Menu("Menu"); 
		MenuItem pacman = new MenuItem("Pacman");
		MenuItem fruit = new MenuItem("Fruit");
		
		Menu options = new Menu("Options");
		MenuItem speed = new MenuItem("Speed");
		MenuItem radius = new MenuItem("Radius");
		MenuItem clear = new MenuItem("Clear");
		
		menuBar.add(menu);
		menu.add(pacman);
		menu.add(fruit);
		
		menuBar.add(options);
		options.add(speed);
		options.add(radius);
		options.add(clear);
		
		this.setMenuBar(menuBar);
		
		try {
			 background = ImageIO.read(new File("C:\\Users\\מעיין\\Desktop\\data\\Ariel1.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			 pacmanImage = ImageIO.read(new File("C:\\Users\\מעיין\\Desktop\\data\\pacman.gif"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	int x = -1;
	int y = -1;

	public void paint(Graphics g) {
		
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
		
		if(x != -1 && y != -1) {
			int r = 10;
			x = x - (r / 2);
			y = y - (r / 2);
			g.fillOval(x, y, r, r);
            g.setColor(Color.YELLOW);
            g.fillOval(x,y,10,10);

		}

	}

	
	

	@Override
	public void mouseClicked(MouseEvent arg) {
		
		System.out.println("mouse Clicked");
		System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		x = arg.getX();
		y = arg.getY();
		repaint();
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		System.out.println("mouse entered");
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}

package GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import Geom.Point3D;
import Packman_Game.Map;
import Packman_Game.Pixel;


public class MainWindow extends JFrame implements MouseListener {
	
	public BufferedImage background;
	public BufferedImage pacmanImage;
	private JPanel _panel;
	private Graphics _paper;
	private boolean isGamer;
	public ArrayList<Point> pList;
	
	public MainWindow() {
		pList = new ArrayList<>();
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
		
		pacman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = false;
			}
		});
		
		fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isGamer = false;
			}
		});
				
		try {
			 background = ImageIO.read(new File("C:\\Users\\מעיין\\Desktop\\data\\Ariel1.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
//		try {
//			 pacmanImage = ImageIO.read(new File("C:\\Users\\מעיין\\Desktop\\data\\pacman.gif"));
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	int x = -1;
	int y = -1;

	public void paint(Graphics g) {
		
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
		
//		if(x != -1 && y != -1) {
//			int r = 10;
//			x = x - (r / 2);
//			y = y - (r / 2);
//			g.fillOval(x, y, r, r);
//            g.setColor(Color.YELLOW);
//            g.fillOval(x, y, 10, 10);
//		}
		
		for (int i = 0; i < pList.size(); i++) {
			int r = 10;
		
			g.setColor(Color.YELLOW);
			g.fillOval(pList.get(i).x,pList.get(i).y,r,r);
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg) {
		
		x = arg.getX();
		y = arg.getY();
		pList.add(new Point(x, y));
		Pixel p = new Pixel(x, y);
		Map m = new Map();
		Point3D p1 = m.Pixel2Point(p);
		System.out.println("("+ p1.x() + "," + p1.y() +")");
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

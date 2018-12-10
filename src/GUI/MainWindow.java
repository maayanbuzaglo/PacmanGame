package GUI;

import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import Geom.Point3D;
import Packman_Game.Map;
import Packman_Game.Pixel;

public class MainWindow extends JFrame implements MouseListener {

	public BufferedImage background;
	public BufferedImage pacmanImage;
	public BufferedImage fruitImage;
	private boolean WhoAreYOU;
	public ArrayList<Point> pList;
	public ArrayList<Point> fList;


	public MainWindow() {
		
		pList = new ArrayList<Point>();
		fList = new ArrayList<Point>();
		initGUI();		
		this.addMouseListener(this);
	}

	private void initGUI() {

		MenuBar menuBar = new MenuBar();
		Menu icons = new Menu("Icons"); 
		MenuItem pacman = new MenuItem("Pacman");
		MenuItem fruit = new MenuItem("Fruit");

		Menu data = new Menu("Data");
		MenuItem speed = new MenuItem("Speed");
		MenuItem radius = new MenuItem("Radius");
		
		Menu options = new Menu("Options");
		MenuItem createKML = new MenuItem("Create kml file");
		MenuItem createCSV = new MenuItem("Create csv file");
		MenuItem exportCSV = new MenuItem("Export csv file");
		MenuItem clear = new MenuItem("Clear");

		menuBar.add(icons);
		icons.add(pacman);
		icons.add(fruit);

		menuBar.add(data);
		data.add(speed);
		data.add(radius);
		
		menuBar.add(options);
		options.add(createKML);
		options.add(createCSV);
		options.add(exportCSV);
		options.add(clear);

		this.setMenuBar(menuBar);

		pacman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WhoAreYOU = true;
			}
		});

		fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WhoAreYOU = false;
			}
		});
		
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					pList.clear();
					fList.clear();
					repaint();
			}
		});

		radius.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					Scanner s = new Scanner("Enter radius: ");
			}
		});
		
		speed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					Scanner s = new Scanner("Enter speed: ");
			}
		});
		
		createKML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					
			}
		});
		
		try {
			background = ImageIO.read(new File("C:\\Users\\מעיין\\Desktop\\data\\Ariel1.png"));
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}

		try {
			pacmanImage = ImageIO.read(new File("C:\\Users\\מעיין\\Desktop\\data\\pacman.png"));
		}
		
		catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			fruitImage = ImageIO.read(new File("C:\\Users\\מעיין\\Desktop\\data\\fruit.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	int x = -1;
	int y = -1;

	public void paint(Graphics g) {

		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
			for (int i = 0; i < pList.size(); i++) {
				g.drawImage(pacmanImage, pList.get(i).x, pList.get(i).y, 30, 30, this);
			}
			for (int i = 0; i < fList.size(); i++) {
				g.drawImage(fruitImage, fList.get(i).x, fList.get(i).y, 50, 50, this);
			}
	}

			@Override
			public void mouseClicked(MouseEvent arg) {

				x = arg.getX();
				y = arg.getY();
				Pixel p = new Pixel(x, y);
				if(WhoAreYOU) {
					pList.add(new Point(x,y));
				}
				else {
					fList.add(new Point(x,y));
				}
				Map m = new Map();
				Point3D p1 = m.Pixel2Point(p);
				System.out.println("(" + p1.x() + "," + p1.y() + ")");
				repaint();		
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {

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

package GUI;

import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
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
import Packman_Game.Fruit;
import Packman_Game.Game;
import Packman_Game.Map;
import Packman_Game.Pacman;
import Packman_Game.Pixel;

/*
 * This class represents the game frame.
 */
public class MainWindow extends JFrame implements MouseListener {

	public BufferedImage background;
	public BufferedImage pacmanImage;
	public BufferedImage fruitImage;
	private boolean WhoAreYOU = true; //if true  - pacman. else - fruit.
	public ArrayList<Pacman> pList;
	public ArrayList<Fruit> fList;
	public ArrayList<Pixel> pacmanPixel;
	public ArrayList<Pixel> fruitPixel;
	public ArrayList<Point3D> pointList;

	public Map m = new Map();
	public int countPacman = 0;
	public int countFruit = 0;

	/*
	 * Constructor.
	 */
	public MainWindow() {

		pList = new ArrayList<Pacman>();
		fList = new ArrayList<Fruit>();
		pointList = new ArrayList<Point3D>();
		pacmanPixel = new ArrayList<Pixel>();
		fruitPixel = new ArrayList<Pixel>();

		initGUI();		
		this.addMouseListener(this);
	}

	/*
	 * This functions makes the frame.
	 */
	private void initGUI() {

		MenuBar menuBar = new MenuBar();
		Menu icons = new Menu("Icons"); //Icons - Pacman, Fruit.
		MenuItem pacman = new MenuItem("Pacman");
		MenuItem fruit = new MenuItem("Fruit");

		Menu data = new Menu("Data"); //Data - Speed, Radius.
		MenuItem speed = new MenuItem("Speed");
		MenuItem radius = new MenuItem("Radius");

		Menu options = new Menu("Options"); //Options - Create kml file, Create csv file, Export csv file, Clear.
		MenuItem createKML = new MenuItem("Create kml file");
		MenuItem readCSV = new MenuItem("Read csv file");
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
		options.add(readCSV);
		options.add(exportCSV);
		options.add(clear);

		this.setMenuBar(menuBar);

		//listens to pacman key.
		pacman.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WhoAreYOU = true;
			}
		});

		//listens to fruit key.
		fruit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WhoAreYOU = false;
			}
		});

		//listens to clear key.
		clear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				pList.clear();
				fList.clear();
				repaint();
			}
		});

		//listens to radius key.
		radius.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Scanner s = new Scanner("Enter radius: ");
			}
		});

		//listens to speed key.
		speed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Scanner s = new Scanner("Enter speed: ");
			}
		});

		//listens to create kml file key.
		createKML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});
		
		//listens to create csv file key.
		readCSV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new Game(pList, fList);
				
			}
		});

		//gets the backgrounds image.
		try {
			background = ImageIO.read(new File("C:\\Users\\מעיין\\Desktop\\Ex3\\data\\Ariel1.png"));
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		//gets the pacman image.
		try {
			pacmanImage = ImageIO.read(new File("C:\\Users\\מעיין\\Desktop\\Ex3\\data\\pacman.png"));
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		//gets the fruit image.
		try {
			fruitImage = ImageIO.read(new File("C:\\Users\\מעיין\\Desktop\\Ex3\\data\\fruit.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	int x = -1;
	int y = -1;

	/*
	 * This function paints pacmans and fruits on the game frame.
	 * @see java.awt.Window#paint(java.awt.Graphics)
	 */
	public void paint(Graphics g) {
				
		g.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), this);
		Pixel pFram = new Pixel(background.getWidth(), background.getHeight());
		m.changFrame(pFram, pacmanPixel, fruitPixel);
		for (int i = 0; i < pacmanPixel.size(); i++) {
			g.drawImage(pacmanImage, (int)pacmanPixel.get(i).getX(), (int)pacmanPixel.get(i).getY(), 30, 30, this);
			Point3D p1 = m.Pixel2Point(pacmanPixel.get(i));
			pointList.add(p1);
			System.out.println("(" + pointList.get(i).x() + "," + pointList.get(i).y() + ")");
		}
		for (int i = 0; i < fList.size(); i++) {
			g.drawImage(fruitImage, (int)fruitPixel.get(i).getX(), (int)fruitPixel.get(i).getY(), 40, 30, this);
			Point3D f1 = m.Pixel2Point(fruitPixel.get(i));
			pointList.add(f1);
			System.out.println("(" + pointList.get(i).x() + "," + pointList.get(i).y() + ")");
		}
	}

	/*
	 * This function handles mouse clicks events.
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg) {

		x = arg.getX();
		y = arg.getY();
		Pixel p = new Pixel(x, y);
		if(WhoAreYOU) {
			pacmanPixel.add(p);
			Point3D point = m.Pixel2Point(p);
			countPacman++;
			Pacman pac = new Pacman(point, countPacman, 1, 1);
			pList.add(pac);
		}
		else {
			fruitPixel.add(p);
			Point3D point = m.Pixel2Point(p);
			countFruit++;
			Fruit fru = new Fruit(point, countFruit, 0);
			fList.add(fru);
		}
		repaint();		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

}

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
public class MyFrame extends JFrame implements MouseListener {

	public Map m;
	public BufferedImage background; //game background image.
	public BufferedImage pacmanImage; //pacman icon.
	public BufferedImage fruitImage; //fruit icon.
	public ArrayList<Pacman> pList;
	public ArrayList<Fruit> fList;
	public ArrayList<Pixel> pacmanPixel; //pacmans pixels list.
	public ArrayList<Pixel> fruitPixel; //fruits pixel list.
	public int countPacman; //pacman id.
	public int countFruit; //fruit id.
	private boolean WhoAreYOU; //if true - draws pacman. else - draws fruit.

	/*
	 * Constructor.
	 */
	public MyFrame() throws IOException {
		
		m = new Map();
		pList = new ArrayList<Pacman>();
		fList = new ArrayList<Fruit>();
		pacmanPixel = new ArrayList<Pixel>();
		fruitPixel = new ArrayList<Pixel>();
		countPacman = 0;
		countFruit = 0;
		WhoAreYOU = true;

		initGUI();		
		this.addMouseListener(this);
	}

	/*
	 * This function makes the frame.
	 */
	private void initGUI() {

		MenuBar menuBar = new MenuBar();
		Menu icons = new Menu("Icons"); //Icons - Pacman, Fruit.
		MenuItem pacman = new MenuItem("Pacman");
		MenuItem fruit = new MenuItem("Fruit");

		Menu data = new Menu("Data"); //Data - Speed, Radius.
		MenuItem speed = new MenuItem("Speed (pacman)");
		MenuItem radius = new MenuItem("Radius (pacman)");
		MenuItem weight = new MenuItem("Weight (fruit)");

		Menu options = new Menu("Options"); //Options - Create kml file, Create csv file, Export csv file, Clear.
		MenuItem createKML = new MenuItem("Create kml file");
		MenuItem readCSV = new MenuItem("Read game");
		MenuItem exportCSV = new MenuItem("Save game");
		MenuItem clear = new MenuItem("Clear");

		menuBar.add(icons);
		icons.add(pacman);
		icons.add(fruit);

		menuBar.add(data);
		data.add(speed);
		data.add(radius);
		data.add(weight);

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
				pacmanPixel.clear();
				fruitPixel.clear();
				countPacman = 0;
				countFruit = 0;
				repaint();
			}
		});
		
		//listens to speed key.
		speed.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(WhoAreYOU) {
		        System.out.print("Enter pacman speed: ");
				Scanner sc = new Scanner(System.in);
		        double speed = sc.nextDouble();
		        pList.get(pList.size()-1).setSpeed(speed);
		        System.out.println(pList);
				}
			}
		});

		//listens to radius key.
		radius.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(WhoAreYOU) {
		        System.out.print("Enter pacman radius: ");
				Scanner sc = new Scanner(System.in);
		        double radius = sc.nextDouble();
		        pList.get(pList.size()-1).setRadius(radius);
		        System.out.println(pList);
				}
			}
		});
		
		//listens to weight key.
		weight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!WhoAreYOU) {
			        System.out.print("Enter fruit price: ");
					Scanner sc = new Scanner(System.in);
			        int weight = sc.nextInt();
			        fList.get(fList.size()-1).setWeight(weight);
			        System.out.println(fList);
				}	
			}
		});

		//listens to create kml file key.
		createKML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new Game(pList, fList);
				g.createKML(g, "C:\\Users\\מעיין\\eclipse-workspace\\OopNavigtion\\data\\myGame.kml");
			}
		});
		
		//listens to read csv file key.
		readCSV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new Game();
				pList.clear();
				fList.clear();
				g.readCsv("C:\\Users\\nahama\\Desktop\\Ex3\\data\\game_1543693822377.csv");
			
				for(Pacman it: g.Pacman_list) {
					pList.add(it);
					
					Pixel p = new Pixel(m.Point2Pixel(it.getLocation().y(),it.getLocation().x()));
					System.out.println(p);
					pacmanPixel.add(p);
				}
				for(Fruit it: g.Fruit_list) {
					fList.add(it);
					
					Pixel f = new Pixel(m.Point2Pixel(it.getLocation().y(), it.getLocation().x()));
					System.out.println(f);
					fruitPixel.add(f);
				}

				repaint();
			}
		});
		
		//listens to create kml file key.
		exportCSV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new Game(pList, fList);
				g.createCSV(g);
			}
		});

		
		//gets the pacman image.
		try {
			pacmanImage = ImageIO.read(new File("C:\\Users\\מעיין\\eclipse-workspace\\OopNavigtion\\pictures\\pacman.png"));
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		//gets the fruit image.
		try {
			fruitImage = ImageIO.read(new File("C:\\Users\\מעיין\\eclipse-workspace\\OopNavigtion\\pictures\\fruit.png"));
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
	
		g.drawImage(m.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		Pixel pFram = new Pixel(this.getWidth(), this.getHeight());
		m.changeFrame(pFram, pacmanPixel, fruitPixel);


		for (int i = 0; i < pacmanPixel.size(); i++) {
			
			g.drawImage(pacmanImage, (int)pacmanPixel.get(i).getX(), (int)pacmanPixel.get(i).getY(), 30, 30, this);

			System.out.println("(" + pacmanPixel.get(i).getX() + "," + pacmanPixel.get(i).getY() + ")");
//			System.out.println("(" + pointListP.get(i).x() + "," + pointListP.get(i).y() + ")");
		}
		for (int i = 0; i < fruitPixel.size(); i++) {
			
			System.out.println("(" + fruitPixel.get(i).getX() + "," + fruitPixel.get(i).getY() + ")");

			g.drawImage(fruitImage, (int)fruitPixel.get(i).getX(), (int)fruitPixel.get(i).getY(), 40, 30, this);
//			System.out.println("(" + pointListF.get(i).x() + "," + pointListF.get(i).y() + ")");
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

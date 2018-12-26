package GUI;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import Geom.Point3D;
import Pacman_game.Fruit;
import Pacman_game.Game;
import Pacman_game.Line;
import Pacman_game.Map;
import Pacman_game.Pacman;
import Pacman_game.Pixel;
import Pacman_game.ShortestPathAlgo;

/*
 * This class represents the game frame.
 */
public class MyFrame extends JFrame implements MouseListener {

	public Map m;
	public BufferedImage background; //game background image.
	public BufferedImage pacmanImage; //pacman icon.
	public BufferedImage fruitImage; //fruit icon.
	public ArrayList<Line> lList; //list of lines.
	public ArrayList<Pacman> pList; //list of pacmans.
	public ArrayList<Fruit> fList; //list of fruits.
	public ArrayList<Pixel> linePixel; //lines pixels list for point 1.
	public ArrayList<Pixel> linePixel2; //lines pixels list for point 2.
	public ArrayList<Pixel> pacmanPixel; //pacmans pixels list.
	public ArrayList<Pixel> fruitPixel; //fruits pixel list.
	public int countPacman; //pacman id.
	public int countFruit; //fruit id.
	private boolean WhoAreYOU; //if true - draws pacman. else - draws fruit.

	/*
	 * An empty constructor.
	 */
	public MyFrame() throws IOException {

		m = new Map();
		lList = new ArrayList<Line>();
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
	 * Constructor.
	 */
	public MyFrame(Game game) throws IOException {

		m = new Map();
		pList =  game.Pacman_list;
		fList = game.Fruit_list;
		pacmanPixel = new ArrayList<Pixel>();
		fruitPixel = new ArrayList<Pixel>();
		WhoAreYOU = true; //draws pacmans.

		repaint();
		initGUI();		
		this.addMouseListener(this);
	}

	/*
	 * This function makes the frame.
	 */
	private void initGUI() {

		MenuBar menuBar = new MenuBar();
		Menu icons = new Menu("Type"); //Icons - Pacman, Fruit.
		MenuItem pacman = new MenuItem("Pacman");
		MenuItem fruit = new MenuItem("Fruit");

		Menu data = new Menu("Data"); //Data - Speed, Radius, Weight.
		MenuItem speed = new MenuItem("Speed (pacman)");
		MenuItem radius = new MenuItem("Radius (pacman)");
		MenuItem weight = new MenuItem("Weight (fruit)");

		Menu options = new Menu("Options"); //Options - Run, Create kml file, Read game, Save game, Clear.
		MenuItem run = new MenuItem("Run");
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
		options.add(run);
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
				lList.clear();
				pacmanPixel.clear();
				fruitPixel.clear();
				linePixel.clear();
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
					pList.get(pList.size() - 1).setSpeed(speed); //gets the last pacman and changes its speed.
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
					pList.get(pList.size()-1).setRadius(radius); //gets the last pacman and changes its radius.
				}
			}
		});

		//listens to weight key.
		weight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(!WhoAreYOU) {
					System.out.print("Enter fruit weight: ");
					Scanner sc = new Scanner(System.in);
					int weight = sc.nextInt();
					fList.get(fList.size()-1).setWeight(weight); //gets the last fruit and changes its weight.
				}	
			}
		});

		//listens to run key.
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ShortestPathAlgo shortPath = new ShortestPathAlgo();
				Game g = new Game(pList, fList , lList);
				try {
					shortPath.closestFruit(g);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				ThreadPacks P = new ThreadPacks(); //NEW
				P.start(); //NEW
				repaint();
			}
		});

		//listens to create kml file key.
		createKML.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new Game(pList, fList ,lList);
				try {
					g.createKML(g, "C:\\Users\\nahama\\eclipse-workspace\\OopNavigtion\\data\\myGame.kml");
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		//listens to read game key.
		readCSV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				Game g = new Game();
				//clears all before read a new game.
				pList.clear();
				fList.clear();
				lList.clear();
				pacmanPixel.clear();
				fruitPixel.clear();
				linePixel.clear();
				String place = "";
				JButton open = new JButton();
				JFileChooser fc = new JFileChooser();
				fc.setDialogTitle("Choose A Pacman Game");
				fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION) {
					place = fc.getSelectedFile().getAbsolutePath();
				}
				g.readCsv(place);
				//adds all the pacmans in the game to pacman list in this game.
				for(Pacman it: g.Pacman_list) {
					pList.add(it);

					Pixel p = new Pixel(m.Point2Pixel(it.getLocation().x(),it.getLocation().y()));
					pacmanPixel.add(p);
				}
				//adds all the fruits in the game to pacman list in this game.
				for(Fruit it: g.Fruit_list) {
					fList.add(it);

					Pixel f = new Pixel(m.Point2Pixel(it.getLocation().x(), it.getLocation().y()));
					fruitPixel.add(f);
				}
				repaint();

			}
		});

		//listens to save game key.
		exportCSV.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Game g = new Game(pList, fList, lList);
				g.createCSV(g);
			}
		});

		//gets the pacman image.
		try {
			pacmanImage = ImageIO.read(new File("C:\\Users\\מעיין\\eclipse-workspace\\OopNavigtion\\pictures\\pacman2.png"));
		}

		catch (IOException e) {
			e.printStackTrace();
		}

		//gets the fruit image.
		try {
			fruitImage = ImageIO.read(new File("C:\\Users\\מעיין\\eclipse-workspace\\OopNavigtion\\pictures\\fruit2.png"));
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	int x = -1;
	int y = -1;

	/*
	 * This function paints pacmans and fruits on the game frame.
	 * @see java.awt.Window#paint(java.awt.Graphics).
	 */
	public void paint(Graphics g) {

		g.drawImage(m.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
		Pixel pFram = new Pixel(this.getWidth(), this.getHeight());
		pacmanPixel = new ArrayList<Pixel>();
		fruitPixel = new ArrayList<Pixel>();
		linePixel = new ArrayList<Pixel>();
		linePixel2 = new ArrayList<Pixel>();

		m.changeFrame(pFram, pacmanPixel, fruitPixel, linePixel); //upload the game pixels if change the frame size.


		//changes points of pacmans in game to pixels.
		for (int i = 0; i < pList.size(); i++) {
			Pixel pix = m.Point2Pixel(pList.get(i).getLocation().x(), pList.get(i).getLocation().y());
			pacmanPixel.add(pix);
		}

		//changes points of fruits in game to pixels.
		for (int i = 0; i < fList.size(); i++) {
			Pixel pix = m.Point2Pixel(fList.get(i).getLocation().x(), fList.get(i).getLocation().y());
			fruitPixel.add(pix);
		}

		//changes points of lines in game to pixels (point 1).
		for (int i = 0; i < lList.size(); i++) {
			Pixel pix = m.Point2Pixel(lList.get(i).getPoint1().x(), lList.get(i).getPoint1().y());
			linePixel.add(pix);
		}

		//changes points of lines in game to pixels (point 2).
		for (int i = 0; i < lList.size(); i++) {
			Pixel pix = m.Point2Pixel(lList.get(i).getPoint2().x(), lList.get(i).getPoint2().y());
			linePixel2.add(pix);
		}

		//draws all the lines on the list.
		for (int i = 0; i < linePixel.size(); i++) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setStroke(new BasicStroke(1));
			g2.setColor(Color.orange);
			g2.drawLine((int)linePixel.get(i).getX(), (int)linePixel.get(i).getY(), (int)linePixel2.get(i).getX(), (int)linePixel2.get(i).getY());
		}

		//draws all the pacmans on the list.
		for (int i = 0; i < pacmanPixel.size(); i++) {
			g.drawImage(pacmanImage, (int)pacmanPixel.get(i).getX(), (int)pacmanPixel.get(i).getY(), 30, 30, this);
		}

		//draws all the fruits on the list.
		for (int i = 0; i < fruitPixel.size(); i++) {
			g.drawImage(fruitImage, (int)fruitPixel.get(i).getX(), (int)fruitPixel.get(i).getY(), 40, 30, this);
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
		if(WhoAreYOU) { //draws pacmans where mouse clicked.
			pacmanPixel.add(p);
			Point3D point = m.Pixel2Point(p);
			countPacman++; //+1 to pacman id.
			Pacman pac = new Pacman(point, countPacman, 1, 1); 
			pList.add(pac);
		}
		else { //draws fruits where mouse clicked.
			fruitPixel.add(p);
			Point3D point = m.Pixel2Point(p);
			countFruit++; //+1 to fruit id.
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

	
	public  class ThreadPacks extends Thread
	{
		/**
		 * This class run the thread of the pacman.
		 */
		@Override
		public void run() {
			double max =0;
			for (int i = 0; i < pList.size(); i++) {
				if (pList.get(i).getTime() > max) {
					max = pList.get(i).getTime();
				}
			}
			for (int i = 1; i < 1000; i++) {
				repaint();
				for(Pacman it: pList)
				{
					Point3D p = it.When(i, m);

					if (p != null) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for (int j = 0; j < fList.size(); j++) {
							if(fList.get(j).getEndTime() < i) {
								fList.remove(j);
								System.out.println("ok");
							}
						}
					} 
					else if (it.getTime() == max && !fList.isEmpty())
					{
						fList.remove(0);
					}
				}
			}
			repaint();
		}
	}
}

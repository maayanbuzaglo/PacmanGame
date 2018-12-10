
package MyGui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author annaf
 */
public class PanelImageMenuWithMouseDemo extends JFrame implements MouseListener {
        // private variables
        private Container window;
	private JPanel _panel;
	private Graphics _paper;
        private int x, y;
        private boolean isGamer;
       
	public PanelImageMenuWithMouseDemo(){
		super("Map Demo"); //setTitle("Map Counter");  // "super" Frame sets its title
                //	Moves and resizes this component. 
                //	The new location of the top-left corner is  specified by x and y, 
                //	and the new size is specified by width and height
                //	setBounds(x,y,width,height)
		this.setBounds(0,0,1433,700); //setSize(1433,700);        // "super" Frame sets its initial window size
                //      Exit the program when the close-window button clicked
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                pack();
                
	}
	public void createGui(){              				
                //	A Container is a component  that can contain other GUI components
		window = this.getContentPane(); 
		window.setLayout(new FlowLayout());
                
                //	Add "panel" to be used for drawing            
		_panel = new JPanel();
		Dimension d= new Dimension(1433,642);
		_panel.setPreferredSize(d);		               
		window.add(_panel);
                
                // A menu-bar contains menus. A menu contains menu-items (or sub-Menu)
                JMenuBar menuBar;   // the menu-bar
                JMenu menu;         // each menu in the menu-bar
                JMenuItem menuItem1, menuItem2; // an item in a menu

                menuBar = new JMenuBar();

                // First Menu
                menu = new JMenu("Menu A");
                menu.setMnemonic(KeyEvent.VK_A);  // alt short-cut key
                menuBar.add(menu);  // the menu-bar adds this menu

                menuItem1 = new JMenuItem("Menu Item 1", KeyEvent.VK_F);
                menu.add(menuItem1); // the menu adds this item
                menuItem1.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                      isGamer = true;
                   }
                });
                menuItem2 = new JMenuItem("Menu Item 2", KeyEvent.VK_S);
                menu.add(menuItem2); // the menu adds this item
                menuItem2.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                      isGamer = false;
                   }
                });                     
                                           
                setJMenuBar(menuBar);  // "this" JFrame sets its menu-bar
                          
                // Prepare an ImageIcon
                String imgMapFilename = "images/Ariel1.png";    
                ImageIcon imgBck = new ImageIcon(getClass().getResource(imgMapFilename));
                JLabel labelMap = new JLabel();
                labelMap.setIcon(imgBck);
                _panel.add(labelMap);
                
                 // panel (source) fires the MouseEvent.
                 //panel adds "this" object as a MouseEvent listener.
		_panel.addMouseListener(this);
	}
        
        protected void paintElement() {
                //	The method getGraphics is called to obtain a Graphics object
		_paper = _panel.getGraphics();
                if(isGamer){
                    _paper.setColor(Color.YELLOW);
                    _paper.fillOval(x,y,10,10);
                } else {
                    _paper.setColor(Color.RED);
                    _paper.fillOval(x,y,10,10);
                }
                _paper.setFont(new Font("Monospaced", Font.PLAIN, 14));               
		_paper.drawString("("+Integer.toString(x)+", "+Integer.toString(y)+")",x,y-10);
        }
  
        //	public void mouseClicked(MouseEvent event){
        @Override
	public void mousePressed(MouseEvent event) {
		x = event.getX();
		y = event.getY();  
                paintElement();
	}
        // Not Used, but need to provide an empty body for compilation
	public void mouseReleased(MouseEvent event){}
	public void mouseClicked(MouseEvent event){}
	public void mouseExited(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public static void main(String[] args) {
		PanelImageMenuWithMouseDemo frame = new PanelImageMenuWithMouseDemo();
		frame.setBounds(0, 0, 1433, 642);
		frame.createGui();
		frame.setVisible(true);
	}
}

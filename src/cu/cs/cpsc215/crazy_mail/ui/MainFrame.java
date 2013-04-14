package cu.cs.cpsc215.crazy_mail.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class MainFrame extends JFrame{
	
	/**
	 * Primary frame class for the program. 
	 * This sets up the window and global layout, along with providing a means to access the global elements.
	 * Will eventually contain methods to handle state changing for the program.
	 */
	private static final long serialVersionUID = 2113187488340018117L;	
	
	//Global panels
	JPanel mainPanel;
	JPanel leftPanel;
	JMenuBar mainMenu;
	JPanel footerPanel;
	
	//Singleton pattern
	static MainFrame inst;
	public static void init(){
		inst = new MainFrame();
		inst.setVisible(true);
		inst.sizeGlobalElements(); //TODO: make this be called on every resize
		
	}
	public static MainFrame getInst(){
		return inst;
	}
	
	//Initial constructor - sets up the window
	private MainFrame(){
	   setTitle("Crazy Mail");
       setSize(760,520);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
       setIcon();
       makeGlobalElements();
	}
	
	//Makes the global elements for the layout
	private void makeGlobalElements(){
		mainPanel = new JPanel();
		leftPanel = new JPanel();
		footerPanel = new JPanel();
				
		makeMenu();
		
		//Positioning
		add(leftPanel);
		add(footerPanel,BorderLayout.SOUTH);
		add(mainPanel,BorderLayout.CENTER);
		add(mainMenu,BorderLayout.NORTH);

		//Temp colors for identification
		leftPanel.setBackground(new Color(240,240,255));
		footerPanel.setBackground(new Color(240,255,240));
		mainPanel.setBackground(new Color(255,240,240));
		
		
		//Placeholder text
		leftPanel.add(new JLabel("Left panel."));
		mainPanel.add(new JLabel("Main panel."));
		footerPanel.add(new JLabel("This is a footer"));

	}
	
	//Makes the main menu
	private void makeMenu(){
		mainMenu = new JMenuBar();
		
		JMenu fileMenu = new JMenu("File");
		JMenu configMenu = new JMenu("Configuration");
		JMenu helpMenu = new JMenu("Help");
		
		JMenuItem about = new JMenuItem("About");
		JMenuItem exit = new JMenuItem("Exit");
		JMenuItem stuff = new JMenuItem("Stuff");
		JMenuItem editConfig = new JMenuItem("Edit Configuration");
		

		fileMenu.add(about);
		fileMenu.add(exit);
		helpMenu.add(stuff);
		configMenu.add(editConfig);

		mainMenu.add(fileMenu);
		mainMenu.add(configMenu);
		mainMenu.add(helpMenu);
	}
	
	/*Does any sizing on the global elements. Currently used to set the bounds for the left panel.
	 * 	This is done outside of the constructor because panels will not return their proper dimensions inside of it.
	 *  (Possibly because swing needs time to set up the window) */
	public void sizeGlobalElements(){
		int innerHeight = this.rootPane.getHeight();
		int menuH = mainMenu.getHeight();
		leftPanel.setBounds(0, menuH, 150, innerHeight - ( menuH + footerPanel.getHeight() ));
	}
	
	//Sets the icon.
	public void setIcon(){
		BufferedImage img = null;
	       try {
	           img = ImageIO.read(new File("icon.png"));
	           this.setIconImage(img);
	       } catch (IOException e){
	    	   //TODO: Make an alert about the icon
	       }
	}
	
	//Getters for all the global elements. Will probably replace with more specific functions later.
	public JPanel getMainPanel(){
		return mainPanel;
	}
	
	public JPanel getFooterPanel(){
		return footerPanel;
	}
	
	public JPanel getLeftPanel(){
		return leftPanel;
	}
	
	public JMenuBar getMainMenu(){
		return mainMenu;
	}

}

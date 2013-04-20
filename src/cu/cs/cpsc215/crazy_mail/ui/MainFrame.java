package cu.cs.cpsc215.crazy_mail.ui;

import com.apple.laf.AquaMenuBarBorder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
/**
 * 
 * @author Kevin Jett
 * @author Emmanuel John
 * 
 */
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
        private JLabel statuslabel;
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
            
            Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
            
            setTitle("Crazy Mail");
            setSize(d.width-70,d.height-100);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setIcon();
            makeGlobalElements();
	}
	
	//Makes the global elements for the layout
	private void makeGlobalElements(){
		mainPanel = new JPanel();
		
                leftPanel = createSidePane();
		footerPanel = createStatusPane();
		mainPanel.setBorder(new LineBorder(Color.DARK_GRAY));
                makeMenu();
		
		//Positioning
		add(leftPanel, "West");
		add(footerPanel,BorderLayout.SOUTH);
		add(mainPanel,BorderLayout.CENTER);
		add(mainMenu,BorderLayout.NORTH);

		leftPanel.setPreferredSize(new Dimension((int)(this.getWidth()*0.25),200));

	}
        public JPanel createStatusPane(){
            JPanel panel = new JPanel();
            panel.setLayout(new FlowLayout(FlowLayout.LEADING));
            statuslabel = new JLabel("Downloading... 90%");
            panel.add(statuslabel);
            
            return panel;
        }
        
        public JPanel createSidePane(){
            
            JPanel main_panel = new JPanel();
            main_panel.setLayout(new BorderLayout());
            
            JPanel n_panel = new JPanel();
            n_panel.setLayout(new FlowLayout(FlowLayout.LEADING));
            JButton composebtn = new JButton("New");
            composebtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent ae){
                    new EmailTransmissionDlg(MainFrame.this, null);
                }
            
            });
            
            JButton retrievebtn = new JButton("Get");
            
            n_panel.add(composebtn);
            n_panel.add(retrievebtn);
            
            JPanel c_panel = new JPanel();
            c_panel.setLayout(new BorderLayout());
            JList<String> list = new JList(new String[]{"Contacts","Inbox",
                "Sent", "Trash"});
            list.setBackground(null);
            list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            list.setFixedCellHeight(50);
            
            c_panel.add(new JScrollPane(list));
            
            main_panel.add(n_panel, "North");
            main_panel.add(c_panel);
            
            return main_panel;
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
	
	public JPanel getLeftPanel(){
		return leftPanel;
	}
	
	public JMenuBar getMainMenu(){
		return mainMenu;
	}
        
        public void setStatus(String s){
            statuslabel.setText(s);
        }
        
        public String getStatus(){
            return statuslabel.getText();
        }

}

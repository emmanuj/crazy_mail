package cu.cs.cpsc215.crazy_mail.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

public class ViewConfigurationsDlg extends JDialog{
	private JPanel mainPanel;
	 private JSplitPane splitPane;
	 private JList accountList;
	 private JButton addButton,editButton,deleteButton;
	 private JPanel content;
	 private MainFrame parent;
	 public ViewConfigurationsDlg(MainFrame parent)
	 {
		 //Set parent, and modal to true
		 this.parent = parent;
		 this.setModal(true);
		 
		 //Make the panels
		 mainPanel = new JPanel();
		 mainPanel.setLayout(new BorderLayout());
		 String[] names = {"Test","Test 2","Test 3"};
		 accountList = new JList(names);
		 content = new JPanel();
		 content.setBorder(BorderFactory.createEtchedBorder());
		 JScrollPane p = new JScrollPane(accountList);
		 splitPane= new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,p,content);
		 
		 //Min sizes
		 p.setMinimumSize(new Dimension(150,200));
		 content.setMinimumSize(new Dimension(300,300));
		 accountList.setFixedCellHeight(40);

		 //Footer
		 JPanel footerPanel = new JPanel();
		 footerPanel.setLayout(new FlowLayout());
		 addButton = new JButton("Add");
		 editButton = new JButton("Edit");
		 deleteButton = new JButton("Delete");
		 footerPanel.add(addButton);
		 footerPanel.add(editButton);
		 footerPanel.add(deleteButton);
		 
		 //Header
		 JLabel header = new JLabel("Use the buttons below to modify your mail accounts.");
		 header.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		 //Add to main
		 mainPanel.add(splitPane,"Center");
		 mainPanel.add(footerPanel,"South");
		 mainPanel.add(header,"North");
		 
		 //Remaining window settings
		 this.add(mainPanel);
		 this.setTitle("Configurations");
		 this.setResizable(false);
		 this.setSize(600,400);
	     this.setLocationRelativeTo(parent);
	     
	     this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 
		 this.setVisible(true);

	     
	 }
	 
}

package cu.cs.cpsc215.crazy_mail.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import cu.cs.cpsc215.crazy_mail.data.DataStore;
import cu.cs.cpsc215.crazy_mail.util.Configuration;
import cu.cs.cpsc215.crazy_mail.util.MailAccount;

public class ViewConfigurationsDlg extends JDialog{
	private JPanel mainPanel;
	 private JSplitPane splitPane;
	 private JList accountList;
	 private JButton addButton,editButton,deleteButton;
	 private JPanel content;
	 private MainFrame parent;
	 private AddEditDeleteMediator buttonMediator;
	 private DefaultListModel listModel;
	 public ViewConfigurationsDlg(MainFrame parent)
	 {
		 super(MainFrame.getInst());
		 
		 //Set parent, and modal to true
		 this.parent = parent;
		 this.setModal(true);
		 
		 //Make the panels
		 mainPanel = new JPanel();
		 mainPanel.setLayout(new BorderLayout());
		 
		 //Make list
		 accountList = new JList();
		 listModel = new DefaultListModel();
		 accountList.setModel(listModel);
		 
		 ArrayList<MailAccount> accounts = DataStore.get().getAccounts();
		 for(int i = 0; i<accounts.size(); i++)
		 {
			 listModel.add(i,accounts.get(i));
		 }
		 
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
		 
		 buttonMediator = new AddEditDeleteMediator(addButton,editButton,deleteButton);
		 buttonMediator.setHasSelectedOption(false);
		 
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
		 
		 //Set handlers
		 setActionHandlers();
		 
		 //Remaining window settings
		 this.add(mainPanel);
		 this.setTitle("Configurations");
		 this.setResizable(false);
		 this.setSize(600,400);
	     this.setLocationRelativeTo(parent);
	     
	     this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 
		 this.setVisible(true);
		 
		
	     
	 }
	 private void setActionHandlers()
	 {
		 final ViewConfigurationsDlg t = this;
		 accountList.addMouseListener(new MouseAdapter(){

			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2)	
				{
					//edit
				}
			}
			
			public void mousePressed(MouseEvent arg0)
			{
				if(accountList.getModel().getSize()>0)
				{
					buttonMediator.setHasSelectedOption(true);
				}
			}
			 
		 });
		 
		 addButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				ConfigurationDlg dlg = new ConfigurationDlg(t, null);
				buttonMediator.setHasSelectedOption(false);
			}
		 });
		 
		 editButton.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent arg0){
				 int a = accountList.getSelectedIndex();
				 ConfigurationDlg dlg = new ConfigurationDlg(t,(MailAccount)accountList.getSelectedValue());
				 accountList.setSelectedIndex(a);
			 }
		 });
		 
		 deleteButton.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent arg0){
				 int remove = JOptionPane.showConfirmDialog(t,"Are you sure you want to delete the mail account?");
				 if(remove == JOptionPane.YES_OPTION)
				 {
					 DataStore.get().getAccounts().remove(accountList.getSelectedValue());
					 DefaultListModel m = (DefaultListModel)accountList.getModel();
					 m.removeElementAt(accountList.getSelectedIndex());
					 buttonMediator.setHasSelectedOption(false);
				 }
			 }
		 });
	 }
	 
	 public void updateList()
	 {
		 listModel.clear();
		 ArrayList<MailAccount> accounts = DataStore.get().getAccounts();
		 for(int i = 0; i<accounts.size(); i++)
		 {
			 listModel.add(i,accounts.get(i));
		 }
	 }
}

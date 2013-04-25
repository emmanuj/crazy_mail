package cu.cs.cpsc215.crazy_mail.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;


import cu.cs.cpsc215.crazy_mail.data.DataStore;
import cu.cs.cpsc215.crazy_mail.ui.messages.InboxState;
import cu.cs.cpsc215.crazy_mail.util.MailAccount;

/**
 *
 * @author Kevin Jett
*/

public class ViewConfigurationsDlg extends JDialog{
	private static final long serialVersionUID = -939507760895083297L;
	private JPanel mainPanel;
	private JSplitPane splitPane;
	private JList accountList;
	private JButton addButton,editButton,deleteButton,makePrimaryButton,doneButton;
	private JPanel content;
	private AddEditDeleteMediator buttonMediator;
	private DefaultListModel listModel;
	private JLabel contentInfo;
	
	public ViewConfigurationsDlg(MainFrame parent)
	{
		super(parent);
		 
		//Set parent, and modal to true
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
		contentInfo = new JLabel();
		content.setLayout(new BorderLayout());
		content.add(contentInfo,"North");
		contentInfo.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
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
		makePrimaryButton = new JButton("Make Primary Account");
		doneButton = new JButton("Finish");
		buttonMediator = new AddEditDeleteMediator(addButton,editButton,deleteButton);
		
		if(accounts.size()>0)
		{
			accountList.setSelectedIndex(0);
		}
		else
		{
			buttonMediator.setHasSelectedOption(false);
		}
		

		footerPanel.add(addButton);
		footerPanel.add(editButton);
		footerPanel.add(deleteButton);
		footerPanel.add(doneButton);

		
		JPanel a = new JPanel();
		a.add(makePrimaryButton);
		content.add(a,"South");
		makePrimaryButton.setSize(new Dimension(50,24));
		 
		//Header
		JLabel header = new JLabel("Use the buttons below to modify your mail accounts.");
		header.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		//Add to main
		mainPanel.add(splitPane,"Center");
		mainPanel.add(footerPanel,"South");
		mainPanel.add(header,"North");
		 
		//Set handlers
		setActionHandlers();
		 
		updateContentInfo();
		 
		//Remaining window settings
		this.add(mainPanel);
		this.setTitle("Configurations");
		this.setResizable(false);
		this.setSize(600,350);
	    this.setLocationRelativeTo(parent);
	     
	    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		 
		this.setVisible(true);
		 
		
	     
	}
	private void setActionHandlers()
	{
		final ViewConfigurationsDlg t = this;
		 
		 //List adaptors
		accountList.addMouseListener(new MouseAdapter(){

			 //Upon double clicking an item, edit the account
			public void mouseClicked(MouseEvent arg0) {
				if(arg0.getClickCount() == 2)	
				{
					int a = accountList.getSelectedIndex();
					new ConfigurationDlg(t,(MailAccount)accountList.getSelectedValue());
					accountList.setSelectedIndex(a);
					updateContentInfo();
				}
			}
			
			//Change selection if pressed
			public void mousePressed(MouseEvent arg0)
			{
				if(accountList.getModel().getSize()>0)
				{
					buttonMediator.setHasSelectedOption(true);
				}
				updateContentInfo();
			}
			 
		 });
		 
		//Make a new config if the add is clicked
		addButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent arg0) {
				new ConfigurationDlg(t, null);
				if(listModel.getSize()>0)
				{
					accountList.setSelectedIndex(listModel.getSize()-1);
					updateContentInfo();
					buttonMediator.setHasSelectedOption(true);
				}
			}
		});
		
		//Edit
		editButton.addActionListener(new ActionListener(){
                        @Override
			public void actionPerformed(ActionEvent arg0){
				int a = accountList.getSelectedIndex();
				new ConfigurationDlg(t,(MailAccount)accountList.getSelectedValue());
				accountList.setSelectedIndex(a);
				updateContentInfo();
			}
		});
		 
		//Delete
		deleteButton.addActionListener(new ActionListener(){
                        @Override
			public void actionPerformed(ActionEvent arg0){
				int remove = JOptionPane.showConfirmDialog(t,"Are you sure you want to delete the mail account?");
				if(remove == JOptionPane.YES_OPTION)
				{
					DataStore.get().getAccounts().remove(accountList.getSelectedValue());
					DefaultListModel m = (DefaultListModel)accountList.getModel();
					m.removeElementAt(accountList.getSelectedIndex());
					buttonMediator.setHasSelectedOption(false);
					updateContentInfo();
				 }
			 }
		 });
		 
		//Makes this the primary account
		makePrimaryButton.addActionListener(new ActionListener(){
                        @Override
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<MailAccount>accounts = DataStore.get().getAccounts();
				int index = accountList.getSelectedIndex();
				MailAccount account = accounts.get(index);
				accounts.remove(index);
				accounts.add(0,account);
				updateList();
				accountList.setSelectedIndex(0);
				makePrimaryButton.setEnabled(false);	
			}
			 
		 });
		
		//Done
		doneButton.addActionListener(new ActionListener(){
                        @Override
			public void actionPerformed(ActionEvent arg0){
				t.dispose();
			}
		});
	}
	 
	//Update the account info display
	private void updateContentInfo()
	{
		if(accountList == null)
			return;
		
		//If selected a value, build some html for the display
		if(accountList.getSelectedValue()!=null)
		{
			MailAccount account = (MailAccount) accountList.getSelectedValue();
			String content = "<b>Name:</b> "+account.getFullname()+"<br/>"+
					 		"<b>Email:</b> "+account.getAccountEmail()+"<br/>"+
					 		"<b>Host:</b> "+account.getHost()+"<br/>"+
					 		"<b>Outgoing Host:</b> "+account.getInHost()+"<br/>"+
					 		"<b>Port:</b> "+account.getPort()+"<br/>"+
					 		"<b>Incoming Protocol:</b> "+account.getIncomingMail()+"<br/>"+
					 		"<b>Outgoing Protocol:</b> "+account.getOutgoingMail()+"<br/>";
			if(account.isUseTLS())
			{
				content+="<i>TLS Secured</i><br/>";
			}
			if(account.isUseSSL())
			{
				content+="<i>SSL Secured</i><br/>";
			}
			 
			contentInfo.setText("<html><h3 style='margin:0px; margin-bottom:5px;'>Configuration Properties</h3>"+content+"</html>");
			makePrimaryButton.setVisible(true);
			if(accountList.getSelectedIndex()==0)
			{
				makePrimaryButton.setEnabled(false);
			}
			else
			{
				makePrimaryButton.setEnabled(true);
			}
		}
		else //Otherwise hide everything
		{
			contentInfo.setText("");
			makePrimaryButton.setVisible(false);
		}
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

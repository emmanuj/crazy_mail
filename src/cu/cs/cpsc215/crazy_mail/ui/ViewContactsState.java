package cu.cs.cpsc215.crazy_mail.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.Border;
import javax.swing.table.AbstractTableModel;

import cu.cs.cpsc215.crazy_mail.data.Contact;
import cu.cs.cpsc215.crazy_mail.data.DataStore;
import cu.cs.cpsc215.crazy_mail.util.MailAccount;
import cu.cs.cpsc215.crazy_mail.util.Protocol;

/**
* State for viewing contacts.
* @author Kevin Jett
* 
*/
public class ViewContactsState implements FrameState{

	private JPanel statePanel;
	private JLabel headerLabel,infoLabel;
	private JButton addButton,editButton,deleteButton;
	private AbstractTableModel tableModel;
	private ContactButtonMediator buttonMediator;
	private JTable table;
	private static ViewContactsState inst = null;
	
	//Singleton pattern
	public static ViewContactsState get()
	{
		if(inst == null)
		{
			inst = new ViewContactsState();
		}
		return inst;
	}
	
	//Sets up the view for this state...
	private ViewContactsState()
	{
		//Panels and labels.
		statePanel = new JPanel();
		headerLabel = new JLabel();
		infoLabel = new JLabel();
		JPanel footerPanel = new JPanel(); //Footer - buttons
		JPanel innerPanel = new JPanel(); //Panel to hold header + table
		statePanel.setLayout(new BorderLayout());
		innerPanel.setLayout(new BorderLayout());
		
		
		statePanel.add(headerLabel,"North");
		statePanel.add(innerPanel,"Center");
		statePanel.add(footerPanel,"South");

		tableModel = new ContactTableModel();
		table = new JTable(tableModel);
		table.setRowHeight(table.getRowHeight()*2);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(table);
		innerPanel.add(scrollPane,"Center");
		innerPanel.add(infoLabel,"North");
		
		makeButtons();
		
		footerPanel.add(addButton);
		footerPanel.add(editButton);
		footerPanel.add(deleteButton);
		
		headerLabel.setText("Contacts");
		headerLabel.setFont(new Font("Serif", Font.BOLD, 24));
		
		Border paddingBorder = BorderFactory.createEmptyBorder(5,5,5,5);
		infoLabel.setBorder(paddingBorder);

		//If no contacts were loaded, display a message
		if(DataStore.get().getContacts().size() == 0)
		{
			infoLabel.setText("You have no contacts yet. Click the add button below to add one.");
		}
		else
		{
			infoLabel.setText("View your contacts here.");
		}
		
		//Table handler
		table.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				buttonMediator.setHasSelectedRow(true);

				if (e.getClickCount() == 2) {
					JTable target = (JTable)e.getSource();
					int row = target.getSelectedRow();
					ArrayList<MailAccount> accounts = new ArrayList();
					        
					MailAccount conf = new MailAccount();
					conf.setPort(465);
					conf.setAccountEmail("emmylifeline@gmail.com");
					conf.setAccountPassword("mmcoofkovqdwezjs");
					conf.setHost("smtp.gmail.com");
					conf.setIncomingMail(Protocol.SMTP);
					conf.setOutgoingmail(Protocol.IMAP);
					conf.setFullname("Emmanuel John");
					    
					accounts.add(conf);
					String email = DataStore.get().getContacts().get(row).getEmail();
					EmailTransmissionDlg dlg = new EmailTransmissionDlg(MainFrame.getInst(),accounts);
					dlg.setRecepient(email);
				 }
			   }
			});
	}
	
	//Buttony things
	private void makeButtons()
	{
		addButton = new JButton("Add");
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
		buttonMediator = new ContactButtonMediator(addButton,editButton,deleteButton);
		buttonMediator.setHasSelectedRow(false);
		
		//Add button action
		addButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ContactEditingDlg dialog = new ContactEditingDlg(MainFrame.getInst(),null);
				
				Contact c = dialog.getContact();
				if(c!=null)
				{
					try {
						DataStore.get().saveContact(c);
					} catch (IOException e) {
					}
					updateTable();
					buttonMediator.setHasSelectedRow(false);
				}
			}
		});
		
		//Edit button action
		editButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				JTable t = ViewContactsState.get().getTable();
				int row = t.getSelectedRow();
				Contact contact = DataStore.get().getContact(row);
				ContactEditingDlg dialog = new ContactEditingDlg(MainFrame.getInst(),contact);
				Contact c = dialog.getContact();
				if(c!=null)
				{
					ArrayList<Contact> contacts = DataStore.get().getContacts();
					contacts.remove(row);
					contacts.add(row,c);
					try {
						DataStore.get().saveContacts(contacts);
					} catch (IOException e) {
					}
					updateTable();
				}
				buttonMediator.setHasSelectedRow(false);
			}
		});
		
		//Delete button action
		deleteButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0){
				JTable t = ViewContactsState.get().getTable();
				int row = t.getSelectedRow();
				ArrayList<Contact> contacts = DataStore.get().getContacts();
				String name = contacts.get(row).getFirstname()+" "+contacts.get(row).getLastname();
				
				int remove = JOptionPane.showConfirmDialog(ViewContactsState.get().getPanel(),"Are you sure you want to delete the contact \""+name+"\"?");
				if(remove == JOptionPane.YES_OPTION)
				{
					contacts.remove(row);
					try {
						DataStore.get().saveContacts(contacts);
					} catch (IOException e) {
					}
					updateTable();
					buttonMediator.setHasSelectedRow(false);
				}
			}
		});
	}
	
	//Forces the table to rebuild
	public void updateTable()
	{
		if(DataStore.get().getContacts().size() == 0)
		{
			infoLabel.setText("You have no contacts yet. Click the add button below to add one.");
		}
		else
		{
			infoLabel.setText("View your contacts here.");
		}
		tableModel.fireTableDataChanged();
	}
	
	//Hash identifier
	public String getName()
	{
		return "Contacts";
	}
	
	public JPanel getPanel()
	{
		return statePanel;
	}
	
	public JTable getTable()
	{
		return table;
	}
	
	//Clear the selection on the hiding of this state
	public void onHide()
	{
		table.clearSelection();
		buttonMediator.setHasSelectedRow(false);
	}
	
	public void onShow()
	{
	}
}

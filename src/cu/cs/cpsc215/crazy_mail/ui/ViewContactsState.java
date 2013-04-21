package cu.cs.cpsc215.crazy_mail.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import cu.cs.cpsc215.crazy_mail.data.Contact;
import cu.cs.cpsc215.crazy_mail.data.DataStore;

/**
* State for viewing contacts.
* @author Kevin Jett
* 
*/
public class ViewContactsState implements FrameState{

	private JPanel statePanel;
	private JLabel infoLabel;
	private JButton addButton,editButton,deleteButton;
	private AbstractTableModel tableModel;
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
		statePanel = new JPanel();
		infoLabel = new JLabel();
		JPanel footerPanel = new JPanel();
		
		statePanel.setLayout(new BorderLayout());
		tableModel = new ContactTableModel();
		JTable table = new JTable(tableModel);
		
		statePanel.add(infoLabel,"North");
		statePanel.add(table,"Center");
		statePanel.add(footerPanel,"South");

		makeButtons();
		
		footerPanel.add(addButton);
		footerPanel.add(editButton);
		footerPanel.add(deleteButton);
		
		//If no contacts were loaded, display a message
		if(DataStore.get().getContacts().size() == 0)
		{
			infoLabel.setText("You have no contacts yet. Click the add button below to add one.");
			infoLabel.setAlignmentX(0.5f);
		}
	}
	
	private void makeButtons()
	{
		addButton = new JButton("Add");
		editButton = new JButton("Edit");
		deleteButton = new JButton("Delete");
				
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
				}
			}
		});
		
	}
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
	public String getName()
	{
		return "Contacts";
	}
	
	public JPanel getPanel()
	{
		return statePanel;
	}
	
	public void onHide()
	{
	}
	
	public void onShow()
	{
	}
}

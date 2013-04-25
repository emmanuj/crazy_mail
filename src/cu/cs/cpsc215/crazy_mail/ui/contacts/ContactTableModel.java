package cu.cs.cpsc215.crazy_mail.ui.contacts;


import javax.swing.table.AbstractTableModel;


import cu.cs.cpsc215.crazy_mail.data.Contact;
import cu.cs.cpsc215.crazy_mail.data.DataStore;

/**
* Class to model the contact table
* @author Kevin Jett
* 
*/
public class ContactTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -1371663695436029138L;
	private String[] columnNames = {"Name","Email","Cell Phone","Home Phone","Address"};


	public ContactTableModel(){}

	public String getColumnName(int col)
	{
		return columnNames[col];
	}
	public int getColumnCount() {
		return columnNames.length;
	}

	public int getRowCount() {
		return DataStore.get().getContacts().size();
	}

	public Object getValueAt(int arg0, int arg1) {
		Contact contact = DataStore.get().getContact(arg0);
		switch(arg1)
		{
		case 0:
			return contact.getFirstname() +" " + contact.getLastname();
		case 1:
			return contact.getEmail();
		case 2:
			return contact.getCellphone();
		case 3:
			return contact.getHomephone();
		case 4:
			return contact.getAddress().getFormattedString();
		default:
			return "???!";
		}
	}

}

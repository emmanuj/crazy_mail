package cu.cs.cpsc215.crazy_mail.ui;


import javax.swing.table.AbstractTableModel;


import cu.cs.cpsc215.crazy_mail.data.Contact;
import cu.cs.cpsc215.crazy_mail.data.DataStore;

/**
* 
* @author Kevin Jett
* 
*/
public class ContactTableModel extends AbstractTableModel {
	private static final long serialVersionUID = -1371663695436029138L;
	private String[] columnNames = {"First Name","Last Name","Email","Cell Phone Number","Home Phone Number","Address"};


	public ContactTableModel()
	{
		
	}

	public int getColumnCount() {
		return columnNames.length;

	}

	@Override
	public int getRowCount() {
		return DataStore.initDataStore().getContacts().size();
	}

	@Override
	public Object getValueAt(int arg0, int arg1) {
		Contact contact = DataStore.get().getContact(arg0);
		switch(arg1)
		{
		case 0:
			return contact.getFirstname();
		case 1:
			return contact.getLastname();
		case 2:
			return contact.getEmail();
		case 3:
			return contact.getCellphone();
		case 4:
			return contact.getHomephone();
		case 5:
			return contact.getAddress().toString();
		default:
			return "???!";
		}
	}

}

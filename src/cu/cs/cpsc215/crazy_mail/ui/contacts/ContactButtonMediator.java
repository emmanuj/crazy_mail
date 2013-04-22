package cu.cs.cpsc215.crazy_mail.ui.contacts;

import javax.swing.JButton;

public class ContactButtonMediator {
	JButton addButton, editButton,deleteButton;
	public ContactButtonMediator(JButton addButton, JButton editButton, JButton deleteButton)
	{
		this.addButton = addButton;
		this.editButton = editButton;
		this.deleteButton = deleteButton;
	}
	public void setHasSelectedRow(boolean selected)
	{
		if(selected)
		{
			addButton.setEnabled(true);
			editButton.setEnabled(true);
			deleteButton.setEnabled(true);
		}
		else
		{
			addButton.setEnabled(true);
			editButton.setEnabled(false);
			deleteButton.setEnabled(false);
		}
	}
}
package cu.cs.cpsc215.crazy_mail.ui;

import javax.swing.JComponent;

public class AddEditDeleteMediator {
	JComponent add, edit,delete;
	public AddEditDeleteMediator(JComponent add, JComponent edit, JComponent delete)
	{
		this.add = add;
		this.edit = edit;
		this.delete = delete;
	}
	public void setHasSelectedOption(boolean selected)
	{
		if(selected)
		{
			add.setEnabled(true);
			edit.setEnabled(true);
			delete.setEnabled(true);
		}
		else
		{
			add.setEnabled(true);
			edit.setEnabled(false);
			delete.setEnabled(false);
		}
	}
}
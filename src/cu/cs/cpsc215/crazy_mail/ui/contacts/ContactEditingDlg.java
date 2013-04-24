package cu.cs.cpsc215.crazy_mail.ui.contacts;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import cu.cs.cpsc215.crazy_mail.data.Address;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import cu.cs.cpsc215.crazy_mail.data.Contact;
import cu.cs.cpsc215.crazy_mail.ui.MainFrame;
import cu.cs.cpsc215.crazy_mail.util.Validator;

public class ContactEditingDlg extends JDialog{
	private static final long serialVersionUID = 6538858318123150195L;
	private Contact contact;
	private MainFrame parent;
	
	private JTextField firstNameField,lastNameField,emailField,cellField,homeField;
	private JTextField addressFieldStreet,addressFieldCity, addressFieldZip;
	private JComboBox addressStateDropdown;
	public ContactEditingDlg(MainFrame parent, Contact contact){
		super(MainFrame.getInst());
		this.parent = parent;
		this.contact = contact;
		this.setModal(true);

		JButton button = new JButton("Done");
		JPanel n_panel = new JPanel();
		n_panel.setLayout(new MigLayout("wrap 2"));
		
		//All the labels...
		JLabel firstNameLabel = new JLabel("First Name");
		JLabel lastNameLabel = new JLabel("Last Name");
		JLabel emailLabel = new JLabel("Email");
		JLabel cellLabel = new JLabel("Cell Phone Number");
		JLabel homeLabel = new JLabel("Home Phone Number");
		JLabel addressLabel = new JLabel("Address");
		JLabel addressLabelStreet = new JLabel("Street");
		JLabel addressLabelCity = new JLabel("City");
		JLabel addressLabelZip = new JLabel("Zip Code");
		JLabel addressLabelState = new JLabel("State or Territory");
		
		//And the fields...
		firstNameField = new JTextField(50);
		lastNameField = new JTextField(50);
		emailField = new JTextField(50);
		cellField = new JTextField(50);
		homeField = new JTextField(50);
		addressFieldStreet = new JTextField(50);
		addressFieldCity = new JTextField(50);
		addressFieldZip = new JTextField(50);
		
		
		addressStateDropdown = new JComboBox(Address.getStates());

		
		if(contact!=null)
		{
			setTitle("Editing Contact");
			//Set the text
			firstNameField.setText(contact.getFirstname());
			lastNameField.setText(contact.getLastname());
			emailField.setText(contact.getEmail());
			cellField.setText(contact.getCellphone());
			homeField.setText(contact.getHomephone());
			
			Address a = contact.getAddress();
			if(a!=null)
			{
				addressFieldStreet.setText(a.getStreet());
				addressFieldCity.setText(a.getCity());
				addressFieldZip.setText(a.getZip());
				addressStateDropdown.setSelectedItem(contact.getAddress().getState());
			}
		}
		else
		{
			setTitle("New Contact");
		}
		
		//Add everything to our panel
		n_panel.add(firstNameLabel);
		n_panel.add(firstNameField);
		n_panel.add(lastNameLabel);
		n_panel.add(lastNameField);
		n_panel.add(emailLabel);
		n_panel.add(emailField);
		n_panel.add(cellLabel);
		n_panel.add(cellField);
		n_panel.add(homeLabel);
		n_panel.add(homeField);
		n_panel.add(addressLabel,"wrap");
		n_panel.add(addressLabelStreet);
		n_panel.add(addressFieldStreet);
		n_panel.add(addressLabelCity);
		n_panel.add(addressFieldCity);
		n_panel.add(addressLabelState);
		n_panel.add(addressStateDropdown);
		n_panel.add(addressLabelZip);
		n_panel.add(addressFieldZip);
		
		n_panel.add(button);
		
		//Keep track of this instance for the handler
		final ContactEditingDlg t = this;
		button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				//Attempt to build the contact
				Contact c = buildContact();
				if(c != null)
				{
					//If successful, set the contact to it and close the dialog
					t.contact = c;
					t.dispose();
				}
				
			}
	    });
		
		//Add panel to the dialog, and set the window stuff
		add(n_panel);
		
        setSize(600,400);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
		
	}
	
	//Attempts to build a contact from the form. Will return null if any validation failed (and also show a message)
	private Contact buildContact()
	{
		Contact contact = new Contact();
		Address address = new Address();
		String error_string = "";
		
		/* Validate! */
		
		//Check first name not blank
		if(Validator.validateBlank(firstNameField.getText()))
			contact.setFirstname(firstNameField.getText());
		else
			error_string+="The contact's first name was left blank. ";

		contact.setLastname(lastNameField.getText()); //Last name is optional
			
		//Check for valid email
		if(Validator.validateEmail(emailField.getText()))
			contact.setEmail(emailField.getText());
		else
			error_string+="The email was invalid. ";
		
		//Check that the phone number is valid (or blank)
		if(Validator.validatePhone(cellField.getText()))
			contact.setCellphone(cellField.getText());
		else if(!Validator.validateBlank(cellField.getText()))
			error_string+="The cell phone was in an invalid format. ";
		
		//Check that the phone number is valid (or blank)
		if(Validator.validatePhone(homeField.getText()))
			contact.setHomephone(homeField.getText());
		else if(!Validator.validateBlank(cellField.getText()))
			error_string+="The home phone was in an invalid format. ";
		
		//Address fields are optional
		address.setCity(addressFieldCity.getText());
		address.setStreet(addressFieldStreet.getText());
		address.setZip(addressFieldZip.getText());
		address.setState(addressStateDropdown.getSelectedItem()+"");
		
		contact.setAddress(address);
		
		//If no errors, return
		if(error_string.equals(""))
		{
			return contact;
		}
		else //Otherwise show a message and return null
		{
			JOptionPane.showMessageDialog(parent,"<html><div width='250px'>There were one or more errors with the contact. <br/>"+error_string+"</div></html>","Error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
	}
	
	public Contact getContact()
	{
		return contact;
	}
	
}

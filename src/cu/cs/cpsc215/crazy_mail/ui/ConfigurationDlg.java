package cu.cs.cpsc215.crazy_mail.ui;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import cu.cs.cpsc215.crazy_mail.data.DataStore;
import cu.cs.cpsc215.crazy_mail.util.Configuration;
import cu.cs.cpsc215.crazy_mail.util.MailAccount;
import cu.cs.cpsc215.crazy_mail.util.Protocol;
import cu.cs.cpsc215.crazy_mail.util.Validator;

public class ConfigurationDlg extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8603424428300283258L;
	private Configuration config;
	private ViewConfigurationsDlg parentDlg;
	private JTextField nameField,emailField,hostField,portField;
	private JPasswordField passwordField;
    private JComboBox incomingOptions, outgoingOptions;
    private JCheckBox tlsBox, sslBox;
	public ConfigurationDlg(ViewConfigurationsDlg dlg, Configuration configuration)
	{
		super(MainFrame.getInst());
		this.parentDlg = dlg;
		this.config = configuration;
		this.setModal(true);
		
		JPanel n_panel = new JPanel();
		n_panel.setLayout(new MigLayout("wrap 2"));
		
		JLabel nameLabel = new JLabel("Name");
		JLabel emailLabel = new JLabel("Email");
        JLabel hostLabel = new JLabel("Host");
        JLabel portLabel = new JLabel("Port");
        JLabel passwordLabel = new JLabel("Password");
        JLabel incomingLabel = new JLabel("Incoming Protocol");
        JLabel outgoingLabel = new JLabel("Outgoing Protocol");
        JLabel useTLS = new JLabel("Use TLS");
        JLabel useSSL = new JLabel("Use SSL");
        
        nameField = new JTextField(20);
        emailField = new JTextField(20);
        passwordField = new JPasswordField(20);
        hostField = new JTextField(20);
        portField = new JTextField(20);
        portField.setText("465");
        Protocol[] iOptions = {Protocol.IMAP,Protocol.IMAPS,Protocol.POP3,Protocol.POP3S};
        Protocol[] oOptions = {Protocol.SMTP,Protocol.SMTPS};
        incomingOptions = new JComboBox(iOptions);
        outgoingOptions = new JComboBox(oOptions);
        tlsBox = new JCheckBox();
        sslBox = new JCheckBox();
        sslBox.setSelected(true);
        sslBox.setEnabled(false);
        JButton button = new JButton();
        
        //Read in values
        if(configuration!=null)
        {
	        hostField.setText(configuration.getHost());
	        portField.setText(""+configuration.getPort());
	        button.setText("Edit Configuration");
        }
        else
        {
        	button.setText("Add Configuration");
        }
        
        n_panel.add(nameLabel);
        n_panel.add(nameField);
        n_panel.add(emailLabel);
        n_panel.add(emailField);
        n_panel.add(hostLabel);
        n_panel.add(hostField);
        n_panel.add(passwordLabel);
        n_panel.add(passwordField);
        n_panel.add(portLabel);
        n_panel.add(portField);
        n_panel.add(incomingLabel);
        n_panel.add(incomingOptions);
        n_panel.add(outgoingLabel);
        n_panel.add(outgoingOptions);
        n_panel.add(useTLS);
        n_panel.add(tlsBox);
        n_panel.add(useSSL);
        n_panel.add(sslBox);
        
        n_panel.add(button,"x 130");
        
        final ConfigurationDlg t = this;

        button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				
				MailAccount account = validateAndBuild();
				if(account != null)
				{
					DataStore.get().addMailAccount(account);
					if(t.parentDlg != null)
					{
						parentDlg.updateList();
					}
					t.dispose();
				}
			}
        	
        });
        
        add(n_panel,"Center");
        if(config == null)
        {
        	setTitle("New Configuration");
        }
        else
        {
        	setTitle("Editing Configuration");
        }
        pack();
        setLocationRelativeTo(MainFrame.getInst());
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
	}
	
	private MailAccount validateAndBuild()
	{
		
		String errors = "";
		String password = new String(passwordField.getPassword());
		
		if(!Validator.validateBlank(nameField.getText()))
		{
			errors+="The name was left blank. ";
		}
		if(!Validator.validateEmail(emailField.getText()))
		{
			errors+="The email was invalid. ";
		}
		if(!Validator.validateBlank(hostField.getText()))
		{
			errors+="The host was left blank. ";
		}
		if(!Validator.validateBlank(password))
		{
			errors+="The password was left blank. ";
		}
		if(!Validator.validateNumericString(portField.getText()))
		{
			errors+="The port was invalid.";
		}
		
		if(!errors.equals(""))
		{
			JOptionPane.showMessageDialog(MainFrame.getInst(),"<html><div width='250px'>"+errors+"</div></html>","Error",JOptionPane.ERROR_MESSAGE);
			return null;
		}
		else
		{
			//Check for valid host
			/**/
			
			MailAccount account = new MailAccount();
			account.setAccountEmail(emailField.getText());
			account.setAccountPassword(password);
			account.setFullname(nameField.getText());
			account.setHost(hostField.getText());
			account.setUseSSL(true);
			account.setUseTLS(tlsBox.isSelected());
			int port = Integer.parseInt(portField.getText());
			account.setPort(port);
			account.setIncomingMail( (Protocol)incomingOptions.getSelectedItem() );
			account.setOutgoingMail( (Protocol)outgoingOptions.getSelectedItem() );
			
			return account;
		}
	}
	
}

package cu.cs.cpsc215.crazy_mail.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

import cu.cs.cpsc215.crazy_mail.util.Configuration;

public class ConfigurationDlg extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8603424428300283258L;
	private Configuration config;
	private MainFrame parent;
	
	public ConfigurationDlg(MainFrame parent, Configuration configuration)
	{
		this.parent = parent;
		this.config = configuration;
		this.setModal(true);
		
		JPanel n_panel = new JPanel();
		n_panel.setLayout(new MigLayout("wrap 2"));
		
        JLabel hostLabel = new JLabel("Host");
        JLabel portLabel = new JLabel("Port");
        JLabel incomingLabel = new JLabel("Incoming Protocol");
        JLabel outgoingLabel = new JLabel("Outgoing Protocol");
        JLabel useTLS = new JLabel("Use TLS");
        JLabel useSSL = new JLabel("Use SSL");
        
        JTextField hostField = new JTextField(60);
        JTextField portField = new JTextField(60);
        String[] options = {"POP","IMAP","MAPI"};
        JComboBox<String> incomingOptions = new JComboBox<String>(options);
        JComboBox<String> outgoingOptions = new JComboBox<String>(options);
        JCheckBox tlsBox = new JCheckBox();
        JCheckBox sslBox = new JCheckBox();
        
        hostField.setText(configuration.getHost());
        portField.setText(""+configuration.getPort());
        
        JButton button = new JButton("Finish Editing");
        n_panel.add(hostLabel);
        n_panel.add(hostField);
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
        
        n_panel.add(button);
        
        final ConfigurationDlg t = this;

        button.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				t.dispose();
			}
        	
        });
        
        add(n_panel,"Center");
        
        setTitle("Configuration");
        setSize(400,250);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
	}
	
	
}

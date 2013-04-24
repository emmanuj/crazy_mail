package cu.cs.cpsc215.crazy_mail.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class SystemInformationDlg extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7391229034595790968L;
	JLabel mainHTML;
	public SystemInformationDlg(MainFrame parent)
	{
		super(parent);
		Border padding = BorderFactory.createEmptyBorder(10,10,10,10);
		String header = "<h2>Crazy Mail version 1.0</h2>";
		String main = "<h3>About Crazy Mail</h3>This application was developed by Kevin Jett and Emmanuel John for Clemson's Software Development Foundations class.";
		mainHTML = new JLabel("<html>"+header+main+"</html>");

		mainHTML.setBorder(padding);
		this.setLayout(new BorderLayout());
		this.add(mainHTML,"North");

		ImageIcon image = new ImageIcon(MainFrame.getInst().getIconImage());
		JLabel imageLabel = new JLabel(image);
		imageLabel.setBorder(BorderFactory.createEmptyBorder(10,10,20,10));
		this.add(new JLabel(image),"Center");
		
		JPanel footer = new JPanel();
		JButton doneButton = new JButton("Close");
		footer.add(doneButton);
		footer.setLayout(new FlowLayout(FlowLayout.RIGHT));
		footer.setBorder(padding);
		this.add(footer,"South");
		
		final SystemInformationDlg t = this;
		doneButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				t.dispose();
			}
		});
		
		this.setTitle("About Crazy Mail");
	    this.setSize(550,475);
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setModal(true);
        this.setResizable(false);
        this.setVisible(true);
	}
}

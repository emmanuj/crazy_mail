/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.ui;

import cu.cs.cpsc215.crazy_mail.mail.Email;
import cu.cs.cpsc215.crazy_mail.mail.MailListener;
import cu.cs.cpsc215.crazy_mail.util.MailAccount;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingWorker;
import javax.swing.border.EtchedBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Emmanuel John
 * @author Kevin Jett
 */
public class EmailTransmissionDlg extends JDialog {
    private JTextPane contentArea;
    //private JTextField 
    private ArrayList<MailAccount> mailaccounts;
    private MainFrame parent;
    private JTextField subjfield;
    private JTextField bccfield;
    private JTextField ccfield;
    private JTextField tofield;
    private JComboBox fromcbox;
    
    private static int count = 0;
    public EmailTransmissionDlg(MainFrame parent, ArrayList<MailAccount> mailaccounts){
        count++;
        this.parent = parent;
        this.mailaccounts = mailaccounts;
        
        JPanel n_panel = new JPanel();
        n_panel.setLayout(new BorderLayout());
        n_panel.add(createButtonPanel(),"North");
        n_panel.add(createInputPanel());

        add(n_panel, "North");
        add(createComposePanel(),"Center");
        
        this.addWindowListener(new WindowAdapter(){
           @Override
           public void windowOpened(WindowEvent e){
               contentArea.requestFocus();
           }
        });
        
        setTitle("New Message");
        setSize(parent.getWidth()-70,parent.getHeight()-50);
        
        setLocationRelativeTo(parent);
        setLocation(32 + count*24,32 + count*24); //Don't want the windows to be on top of each other
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
        
        //Special closer
        final EmailTransmissionDlg t = this;
        addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent we) {
        	t.dispose();
        	count--;
        	}
        });
    }
    private JPanel createButtonPanel(){
        JPanel btnPanel = new JPanel();
        
        btnPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        JButton sendbtn = new JButton("Send");
        sendbtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sendAction();
                } catch (AddressException ex) {
                    Logger.getLogger(EmailTransmissionDlg.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(EmailTransmissionDlg.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(EmailTransmissionDlg.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        });
        
        JButton attachbtn = new JButton("Attach");
        attachbtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                attachAction();
            }
        
        });
        btnPanel.add(attachbtn);
        btnPanel.add(sendbtn);
        
        return btnPanel;
    } 
    private JPanel createInputPanel(){
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new MigLayout("wrap 2"));
        
        JLabel tolabel = new JLabel("To");
        JLabel cclabel = new JLabel("Cc");
        JLabel bcclabel = new JLabel("Bcc");
        JLabel subjlabel = new JLabel("Subject");
        JLabel fromlabel = new JLabel("From");
        
        fromcbox = new JComboBox(mailaccounts.toArray());
        tofield = new JTextField(100);
        ccfield = new JTextField(100);
        bccfield = new JTextField(100);
        subjfield = new JTextField(100);
        
        inputPanel.add(tolabel);
        inputPanel.add(tofield);
        
        inputPanel.add(cclabel);
        inputPanel.add(ccfield);
        
        inputPanel.add(bcclabel);
        inputPanel.add(bccfield);
        
        inputPanel.add(subjlabel);
        inputPanel.add(subjfield);
        
        inputPanel.add(fromlabel);
        inputPanel.add(fromcbox);
        
        return inputPanel;
    }
    private JPanel createComposePanel(){
        JPanel areaPanel = new JPanel();
        areaPanel.setLayout(new BorderLayout());
        contentArea = new JTextPane();
        contentArea.setContentType("text/plain");
        contentArea.setMargin(new Insets(15, 15, 15, 15));
        
        areaPanel.add(new JScrollPane(contentArea));
        
        
        return areaPanel;
    }
    public void attachAction(){
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Attach a File");
        chooser.setMultiSelectionEnabled(false);
        chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                
        int option = chooser.showOpenDialog(this);
        if(option == JFileChooser.APPROVE_OPTION){
            File file = chooser.getSelectedFile();
            
            System.out.println(file.getPath());
        }
    }
    
    public void sendAction() throws AddressException, MessagingException, IOException{
        SwingWorker worker = new SwingWorker() {

            @Override
            protected Object doInBackground() throws Exception {
                MailAccount userAccount = (MailAccount) fromcbox.getSelectedItem();
        
                Email email = new Email(userAccount);
                email.addTo(tofield.getText());
                email.setSubject(subjfield.getText());
                email.setMsg(contentArea.getText());
                MailListener listener = new MailListener(parent);
                email.setListener(listener);
                email.sendEmail();
                
                

                return null;
            }
        };
        worker.execute();
        EmailTransmissionDlg.this.dispose();
    }
    
    public void setRecepient(String email)
    {
    	tofield.setText(email);
    }

    
    
}
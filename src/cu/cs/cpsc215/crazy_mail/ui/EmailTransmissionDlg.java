/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.ui;

import cu.cs.cpsc215.crazy_mail.util.MailAccount;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
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
    private MailAccount mailaccount;
    private MainFrame parent;
    public EmailTransmissionDlg(MainFrame parent, MailAccount mailaccount){
        
        this.parent = parent;
        this.mailaccount = mailaccount;
        
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
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
    public JPanel createButtonPanel(){
        JPanel btnPanel = new JPanel();
        
        btnPanel.setBorder(new EtchedBorder(EtchedBorder.LOWERED));
        btnPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        JButton sendbtn = new JButton("Send");
        sendbtn.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                sendAction();
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
    public JPanel createInputPanel(){
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new MigLayout("wrap 2"));
        
        JLabel tolabel = new JLabel("To");
        JLabel cclabel = new JLabel("Cc");
        JLabel bcclabel = new JLabel("Bcc");
        JLabel subjlabel = new JLabel("Subject");
        JLabel fromlabel = new JLabel("From");
        
        JComboBox fromcbox = new JComboBox(new String[]{"Emmanuel John <emmylifeline@gmail.com>","Kevin Jett <kmjett@clemson.edu>"});
        
        JTextField tofield = new JTextField(100);
        JTextField ccfield = new JTextField(100);
        JTextField bccfield = new JTextField(100);
        JTextField subjfield = new JTextField(100);
        
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
    public JPanel createComposePanel(){
        JPanel areaPanel = new JPanel();
        areaPanel.setLayout(new BorderLayout());
        contentArea = new JTextPane();
        contentArea.setContentType("text/html");
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
    
    public void sendAction(){
        
    }
    
    public static void main(String args[]){
        new EmailTransmissionDlg(null,null);//for testing only. remove this line
    }
}

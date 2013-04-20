/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author abby
 */
public class EmailTransmissionDlg extends JFrame {
    //private JTextField 
    public EmailTransmissionDlg(){
        
        
        setSize(700,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public JPanel createButtonPanel(){
        return null;
    } 
    public JPanel createInputPanel(){
        return null;
    }
    public JPanel createComposePanel(){
        return null;
    }
    public static void main(String args[]){
        new EmailTransmissionDlg();
    }
}

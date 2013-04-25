/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.ui.messages;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Emmanuel John
 * 
 */
public class MessageListCellRenderer implements ListCellRenderer {
    protected static Border noFocusBorder = new EmptyBorder(15, 1, 1, 1);

    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    @Override
    public Component getListCellRendererComponent(JList list, Object ovalue, int index, boolean isSelected, boolean cellHasFocus) {
        
    	final Message value = (Message)ovalue;
    	JLabel renderer = (JLabel) defaultRenderer.getListCellRendererComponent(list, value, index,
        isSelected, cellHasFocus);
        try {
            renderer.setText("<html><div><b>"+value.getSubject()+"</b><br/>"+value.getFrom()[0].toString()+"<br/>"+value.getSentDate()+"</div></html>");
        } catch (MessagingException ex) {
            Logger.getLogger(MessageListCellRenderer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return renderer;
    }
	
	
    
}

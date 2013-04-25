/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.ui.messages;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author Hermoine
 */
public class MessageListCellRenderer implements ListCellRenderer<Message> {
    protected static Border noFocusBorder = new EmptyBorder(15, 1, 1, 1);

    protected DefaultListCellRenderer defaultRenderer = new DefaultListCellRenderer();
    @Override
    public Component getListCellRendererComponent(JList<? extends Message> list, final Message value, int index, boolean isSelected, boolean cellHasFocus) {
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

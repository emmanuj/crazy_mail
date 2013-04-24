/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.ui.messages;

import java.awt.BorderLayout;
import java.awt.Component;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.CommandInfo;
import javax.activation.CommandObject;
import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Emmanuel
 */
public class MailViewer extends JPanel implements CommandObject {
    private String action;
    private DataHandler datahandler;
    private Message m_message;
    private Component body_component;
    public MailViewer(Message m_message){
        setLayout(new BorderLayout());
        
        
        setMessage(m_message);
    }
    
    @Override
    public void setCommandContext(String string, DataHandler dh) throws IOException {
        action = string;
        datahandler = dh;
        
        Object obj = dh.getContent();
        
        if(obj instanceof Message)
            setMessage((Message)obj);
        else
            System.out.println("Content not a message object");
            
    }
    
    public void setMessage(Message m_message){
        this.m_message = m_message;
        JEditorPane display_pane = new JEditorPane();
        
        
        if(body_component!=null){
            remove(body_component);
        }
        
        if(m_message != null){
            body_component = getBodyView();
        }else{
            display_pane.setEditable(false);
            body_component = display_pane;
        }
        
        add(new JScrollPane(body_component));
        
        
    }
    
    protected void getRelevantHeaders(){
        
    }
    
    protected Component getBodyView(){
        try {
            DataHandler d_handler = m_message.getDataHandler();
            CommandInfo c_info = d_handler.getCommand(action);
            
            if(c_info == null){
                throw new MessagingException("view commadn failed on: "+ m_message.getContentType() );
            }
            
            Object data_bean = d_handler.getBean(c_info);
            
            if(data_bean instanceof Component){
                return (Component) data_bean;
            }else{
                throw new MessagingException("Bean is not a component: "+ 
                        data_bean.getClass().toString());
            }
           
        } catch (MessagingException ex) {
            Logger.getLogger(MailViewer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         return new JLabel("Error occured while retrieving message body: "+ m_message);
    }
    
}

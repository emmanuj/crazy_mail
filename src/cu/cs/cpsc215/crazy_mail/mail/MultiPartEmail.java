/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.mail;

import cu.cs.cpsc215.crazy_mail.util.MailAccount;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Hermoine
 */
public class MultiPartEmail extends Email {
    
    private EmailAttachment attachment;
    private ArrayList<File> filelist = new ArrayList();
    public EmailAttachment getAttachment() {
        return attachment;
    }

    public MultiPartEmail(MailAccount mailaccount) {
        super(mailaccount);
    }
    
    public void attach(EmailAttachment attachment){
        
        
        
    }

    @Override
    public void sendEmail() throws AddressException, MessagingException, IOException {
       MailAccount account = super.getMailaccount();
       final Properties confProperties = parseConfiguration(account);
        
        Transport transport = null;
        
        Session session = Session.getInstance(confProperties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(confProperties.getProperty("mail.user"),confProperties.getProperty("password"));
            }
        });
        
        MimeMultipart multipart_msg = new MimeMultipart();
        
        MimeMessage mimeMsg = new MimeMessage(session);
        mimeMsg.setFrom(confProperties.getProperty("mail.from"));
        mimeMsg.setSubject(this.getSubject());
        mimeMsg.setRecipients(Message.RecipientType.TO, (Address[]) getTo().toArray());
        //mimeMsg.setText(this.getMsg());
        mimeMsg.setSentDate(new Date());
        
        if(getCc().size()>0){
           mimeMsg.setRecipients(Message.RecipientType.CC, (Address[]) getCc().toArray()); 
        }
        
        if(getBcc().size()>0){
           mimeMsg.setRecipients(Message.RecipientType.BCC, (Address[]) getBcc().toArray()); 
        }
        
        MimeBodyPart body = new MimeBodyPart();
        body.setText(getMsg());
        
        
        MimeBodyPart attachment = new MimeBodyPart();
        //attachment.
        
        MailListener m_listener = new MailListener();
        transport = session.getTransport();
        //add transport listener and connection listener
        transport.addConnectionListener(m_listener);
        transport.addTransportListener(m_listener);
        
        //connect to the host server
        transport.connect();
        
        try{
            
            //send message
            transport.sendMessage(mimeMsg, mimeMsg.getAllRecipients());
        }finally{
            //close connection
            transport.close();
        }
    }
    
}

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
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Emmanuel
 */
public class MultiPartEmail extends Email {
    
    private ArrayList<EmailAttachment> attachments = new ArrayList();
    public ArrayList<EmailAttachment> getAttachments() {
        return attachments;
    }

    public MultiPartEmail(MailAccount mailaccount) {
        super(mailaccount);
    }
    
    public void attach(EmailAttachment attachment){
        attachments.add(attachment);
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
        mimeMsg.setFrom(new InternetAddress(confProperties.getProperty("mail.from")));
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
        multipart_msg.addBodyPart(body);
        for(EmailAttachment at: attachments){
            MimeBodyPart at_part = new MimeBodyPart();
            at_part.attachFile(at.getFile());
            multipart_msg.addBodyPart(at_part);
        }
        
        mimeMsg.setContent(multipart_msg);
        MailListener listener = getListener();
        if(listener ==null)
            listener =   new MailListener();
        
        transport = session.getTransport();
        //add transport listener and connection listener
        transport.addConnectionListener(listener);
        transport.addTransportListener(listener);
        
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

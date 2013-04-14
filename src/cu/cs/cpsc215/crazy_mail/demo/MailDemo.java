package cu.cs.cpsc215.crazy_mail.demo;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Emmanuel John
 */
public class MailDemo {
    
    private String email;
    private String password;
    private String fullname;
    
    public MailDemo(){
        try {
            sendPlainMessage("emmylifeline@gmail.com", "Yes it works", "This is a test message from JavaMail");
        } catch (AddressException ex) {
            Logger.getLogger(MailDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(MailDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void retrieveMailServerDetails(){
        
    }
    
    public static void main(String argv[]){
        new MailDemo();
    }
    
    public void sendPlainMessage(String to, String subject, String content) throws AddressException, MessagingException{
        Properties props = new Properties();
        
       // props.put("mail.from", "emmylifeline@gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        
        Session session = Session.getInstance(props,null);
        
        //System.out.println(session.getTransport().getURLName());
        
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom("emmylifeline@gmail.com");
        msg.setSubject(subject);
        msg.setRecipients(Message.RecipientType.TO, to);
        msg.setText(content);
        msg.setSentDate(new Date());
        
        Transport.send(msg,"emmylifeline@gmail.com","unekwu");
        
    }
    
}

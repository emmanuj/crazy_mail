package cu.cs.cpsc215.crazy_mail.demo;

import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Provider;
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
        props.put("mail.transport.protocol","smtp");
        props.put("mail.from", "john.emmanuel10@yahoo.com");
        props.put("mail.smtp.host", "smtp.yahoomail.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");  // If you need to authenticate
        // Use the following if you need SSL
        //props.put("mail.smtp.socketFactory.port", 25);
        //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.socketFactory.fallback", "false");
        Session session = Session.getInstance(props,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("john.emmanuel10@yahoo.com","unekwu01");
            }
        });
        Provider[] providers = session.getProviders();
        for(Provider p: providers){
            System.out.println(p.getProtocol());
        }
        
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom("john.emmanuel10@yahoo.com");
        msg.setSubject(subject);
        msg.setRecipients(Message.RecipientType.TO, to);
        msg.setText(content);
        msg.setSentDate(new Date());
        
        Transport.send(msg);
        System.out.println("mesage sent");
    }
    
}

package cu.cs.cpsc215.crazy_mail.demo;

import cu.cs.cpsc215.crazy_mail.mail.MailListener;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
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
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author Emmanuel John
 */
public final class MailDemo {
    
    public MailDemo(){
        try {
            
            sendWithAttachment("emmylifeline@gmail.com", "Yes it works", "This is a test message from JavaMail");
            //sendHTMLWithAttachment("emmylifeline@gmail.com", "Yes it works", "This is a test message from JavaMail");
        } catch (AddressException ex) {
            Logger.getLogger(MailDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException | IOException ex) {
            Logger.getLogger(MailDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void retrieveMailServerDetails(){
        
    }
    
    public static void main(String argv[]){
        new MailDemo();
    }
    public void retrieveEmail(){
        
    }
    
    public void sendPlainMessage(String to, String subject, String content) throws AddressException, MessagingException, IOException{
        Properties props = new Properties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.from", "john.emmanuel10@yahoo.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        
        Session session = Session.getInstance(props,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("physicistjohn@gmail.com","unekwu01");
            }
        });
        
       
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom("john.emmanuel10@yahoo.com");
        msg.setSubject(subject);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setText(content);
        msg.setSentDate(new Date());
        
        Transport.send(msg);
        System.out.println("mesage sent");
    }
    public void sendWithAttachment(String to, String subject, String content) throws AddressException, MessagingException, IOException{
        Properties props = new Properties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.from", "john.emmanuel10@yahoo.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.port", "25");
        props.put("mail.smtp.starttls.enable","true");
        
        
        // Use the following if you need SSL
        //props.put("mail.smtp.socketFactory.port", 465);
        //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        //props.put("mail.smtp.socketFactory.fallback", "false");
        
        Session session = Session.getInstance(props,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("physicistjohn@gmail.com","unekwu01");
            }
        });
        
        MailListener listener = new MailListener();
        
        Transport trans = session.getTransport();
        trans.addConnectionListener(listener);
        trans.addTransportListener(listener);
        
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom("john.emmanuel10@yahoo.com");
        msg.setSubject(subject);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        //msg.setText(content);
        msg.setSentDate(new Date());
        
        //attach file
        MimeBodyPart body = new MimeBodyPart();
        body.setText(content);
        
        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(new File("image.jpg"));
        
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(body);
        mp.addBodyPart(attachment);
        
        msg.setContent(mp);
        msg.setHeader("X-mailer", "MailDemo");
        
        trans.connect();
        
        try{
            trans.sendMessage(msg, msg.getAllRecipients());
        }finally{
            trans.close();
        }
        
       // Transport.send(msg);
        //System.out.println("mesage sent");
    }
    public void sendHTMLWithAttachment(String to, String subject, String content) throws AddressException, MessagingException, IOException{
        Properties props = new Properties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.from", "john.emmanuel10@yahoo.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.port", "25");
        props.put("mail.smtp.starttls.enable","true");
        
        Session session = Session.getInstance(props,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("physicistjohn@gmail.com","unekwu01");
            }
        });
        
       
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom("john.emmanuel10@yahoo.com");
        msg.setSubject(subject);
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
        
        msg.setSentDate(new Date());
        
        //Create HTML Message
        MimeBodyPart body = new MimeBodyPart();
	StringBuffer sb = new StringBuffer();
	sb.append("<HTML>\n");
	sb.append("<HEAD>\n");
	sb.append("<TITLE>\n");
	sb.append(subject).append("\n");
	sb.append("</TITLE>\n");
	sb.append("</HEAD>\n");

	sb.append("<BODY>\n");
	sb.append("<H1>").append(subject).append("</H1>" + "\n");

	sb.append(content);

	sb.append("</BODY>\n");
	sb.append("</HTML>\n");

	body.setDataHandler(new DataHandler(
		new ByteArrayDataSource(sb.toString(), "text/html")));
        
        //attach file
        MimeBodyPart attachment = new MimeBodyPart();
        attachment.attachFile(new File("image.jpg"));
        
        MimeMultipart mp = new MimeMultipart();
        mp.addBodyPart(body);
        mp.addBodyPart(attachment);
        
        msg.setContent(mp);
        msg.setHeader("X-mailer", "MailDemo");
        
        Transport.send(msg);
        System.out.println("mesage sent");
    }
}

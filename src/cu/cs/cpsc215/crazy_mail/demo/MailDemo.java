package cu.cs.cpsc215.crazy_mail.demo;

import cu.cs.cpsc215.crazy_mail.mail.MailListener;
import cu.cs.cpsc215.crazy_mail.ui.messages.MailViewer;
import cu.cs.cpsc215.crazy_mail.ui.messages.MultipartViewer;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.CommandInfo;
import javax.activation.DataHandler;
import javax.mail.Authenticator;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

/**
 *
 * @author Emmanuel John
 */
public final class MailDemo {
    
    public MailDemo(){
        try {
            retrieveEmail();
            //sendWithAttachment("emmylifeline@gmail.com", "Yes it works", "This is a test message from JavaMail");
            //sendHTMLWithAttachment("emmylifeline@gmail.com", "Yes it works", "This is a test message from JavaMail");
        } catch (AddressException ex) {
            Logger.getLogger(MailDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(MailDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MailDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void retrieveMailServerDetails(){
        
    }
    
    public static void main(String argv[]){
        new MailDemo();
    }
    public void retrieveEmail() throws NoSuchProviderException, MessagingException, MessagingException, IOException{
        Properties props = getConf();
        //props.put("mail.imap.port","993");
        
        //create session object from properties file
        Session session = Session.getDefaultInstance(System.getProperties(),null);
        
        //get store object
        Store store = session.getStore("imaps");
        
        store.connect("imap.gmail.com", "", "");
        
        System.out.println(store);
        
        //create folder
        Folder inbox = store.getFolder("INBOX");
        
        inbox.open(Folder.READ_ONLY);
        
        Message [] messages = inbox.getMessages();
        
        //messageViewer(messages[3]).setVisible(true);
        //return;
        
        DataHandler d = null;
        CommandInfo in=null;
        for(Message message: messages){
            
            
            //message.
            d = message.getDataHandler();
            
            in = d.getCommand("view");
            System.out.println(in);
            //for(CommandInfo i : d.getAllCommands())
              //  System.out.println(message.getMessageNumber()+":"+i.getCommandName());
//            if(message.getContentType().contains("HTML")){
//                 messageViewer(message).setVisible(true);
//                 break;
//            }
            
                
        }
        
       
        
        
        
    }
    public static String getMessageContent(Message message) throws IOException, MessagingException
      {
        Object content = message.getContent();
        if (content instanceof Multipart) {
            StringBuffer messageContent = new StringBuffer();
            Multipart multipart = (Multipart) content;
            for (int i = 0; i < multipart.getCount(); i++) {
                Part part = (Part) multipart.getBodyPart(i);
                if (part.isMimeType("text/html")) {
                    messageContent.append(part.getContent().toString());
                }
                
            }
            return messageContent.toString();
        } else {
            return content.toString();
        }
    }
    public JFrame messageViewer(Message m) throws MessagingException, IOException{
        JFrame frame = new JFrame();
        JTextPane area = new JTextPane();
        MailViewer viewer = new MailViewer();
        viewer.setMessage(m);
        //area.setEditable(false);
        //area.setContentType("text/html");
        //area.setText(getMessageContent(m));
        
        frame.add(viewer);
        frame.setTitle(m.getSubject());
        frame.setSize(700,600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        return frame;
    }
    public Properties getConf(){
        Properties props = new Properties();
        props.put("mail.transport.protocol","smtp");
        
        //props.put("mail.store.protocol","imap");
        //props.put("mail.imap.host","imap.gmail.com");
        
        props.put("mail.from", "");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        
        
        return props;
    }
    
    
    public void sendPlainMessage(String to, String subject, String content) throws AddressException, MessagingException, IOException{
        Properties props = new Properties();
        props.put("mail.transport.protocol","smtp");
        props.put("mail.from", "");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable","true");
        
        Session session = Session.getInstance(props,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("","");
            }
        });
        
       
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(""));
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
        props.put("mail.from", "");
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
                return new PasswordAuthentication("","");
            }
        });
        
        MailListener listener = new MailListener();
        
        Transport trans = session.getTransport();
        trans.addConnectionListener(listener);
        trans.addTransportListener(listener);
        
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(""));
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
        props.put("mail.from", "");
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
                return new PasswordAuthentication("","");
            }
        });
        
       
        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(""));
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

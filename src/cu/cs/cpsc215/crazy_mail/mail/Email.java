/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.mail;

import cu.cs.cpsc215.crazy_mail.util.MailAccount;
import cu.cs.cpsc215.crazy_mail.util.Protocol;
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
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Emmanuel John
 * 
 * Represents a mail object e.g file, image etc
 */
public class Email implements Mail {
    
    private String from;
    private ArrayList<InternetAddress> to;
    private ArrayList<InternetAddress> bcc;
    private ArrayList<InternetAddress> cc;
    private String msg;
    private MailAccount mailaccount;
    private String subject;

    public Email(MailAccount mailaccount) {
        this.mailaccount = mailaccount;
    }
    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public ArrayList<InternetAddress> getTo() {
        return to;
    }

    public ArrayList<InternetAddress> getBcc() {
        return bcc;
    }

    public void setBcc(ArrayList<InternetAddress> bcc) {
        this.bcc = bcc;
    }

    public ArrayList<InternetAddress> getCc() {
        return cc;
    }

    public void setCc(ArrayList<InternetAddress> cc) {
        this.cc = cc;
    }

    public void setTo(ArrayList<InternetAddress> to) {
        this.to = to;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public MailAccount getMailaccount() {
        return mailaccount;
    }

    public void setMailaccount(MailAccount mailaccount) {
        this.mailaccount = mailaccount;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
    
    public void addTo(String email) throws AddressException{
        to.add(new InternetAddress(email));
    }
    public void addCC(String email) throws AddressException{
        cc.add(new InternetAddress(email));
    }
    public void addBCC(String email) throws AddressException{
        bcc.add(new InternetAddress(email));
    }
    
    
    @Override
    public void sendEmail()throws AddressException, MessagingException, IOException {
        
        final Properties confProperties = parseConfiguration(mailaccount);
        
        Transport transport = null;
        
        Session session = Session.getInstance(confProperties,new Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(confProperties.getProperty("mail.user"),confProperties.getProperty("password"));
            }
        });
        
        
        MimeMessage mimeMsg = new MimeMessage(session);
        mimeMsg.setFrom(confProperties.getProperty("mail.from"));
        mimeMsg.setSubject(this.getSubject());
        mimeMsg.setRecipients(Message.RecipientType.TO, (Address[]) to.toArray());
        mimeMsg.setText(this.getMsg());
        mimeMsg.setSentDate(new Date());
        
        if(cc.size()>0){
           mimeMsg.setRecipients(Message.RecipientType.CC, (Address[]) cc.toArray()); 
        }
        
        if(bcc.size()>0){
           mimeMsg.setRecipients(Message.RecipientType.BCC, (Address[]) bcc.toArray()); 
        }
        
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
    
    public Properties parseConfiguration(MailAccount mailaccount){
        //Retrieve configuration info from mail account
        String fromEmail = mailaccount.getAccountEmail();
        if(this.from==null||this.from.equals("")){
            fromEmail = this.getFrom();
        }
        boolean auth = mailaccount.isAuth();
        Protocol sendProtocol = mailaccount.getIncomingMail();
        String host = mailaccount.getHost();
        int port = mailaccount.getPort();
        String pwd = mailaccount.getAccountPassword();
        boolean useTLS = mailaccount.isUseTLS();
        boolean useSSL = mailaccount.isUseSSL();
        String fullname = mailaccount.getFullname();
        String user = mailaccount.getAccountEmail();
        
        Properties props = new Properties();
        
        switch(sendProtocol){
            case SMTP:
                props.put("mail.transport.protocol",sendProtocol.value());
                break;
            case SMTPS:
                props.put("mail.transport.protocol",sendProtocol.value());
                break;
            default:
                props.put("mail.transport.protocol","smtp");
        }
        
        props.put("mail.from", fromEmail);
        props.put("mail.smtp.host", host);
        
        props.put("mail.smtp.auth", auth+"");
        
        //use SSL?
        if(useSSL){
            props.put("mail.smtp.socketFactory.port", port+"");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            //props.put("mail.smtp.socketFactory.fallback", "false");
        }
        
        //use TLS?
        if(useTLS){
            props.put("mail.smtp.starttls.enable","true");
        }
        
        //use SSL by default!
        if(!useSSL && !useTLS){
            props.put("mail.smtp.socketFactory.port", port+"");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            //props.put("mail.smtp.socketFactory.fallback", "false");
        }
        
        props.put("mail.user", user);
       
        //other necessary but not mail conf related properties
        props.put("password", pwd);
        props.put("fullname", fullname);
        
        return props;
    }
    
}

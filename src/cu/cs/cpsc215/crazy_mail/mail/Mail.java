/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.mail;

import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

/**
 *
 * @author Emmanuel John
 * @Author Kevin Jett
 * Represents a single mail
 * 
 * 
 */
public interface Mail{
    
    
    void sendEmail()throws AddressException, MessagingException, IOException;
}

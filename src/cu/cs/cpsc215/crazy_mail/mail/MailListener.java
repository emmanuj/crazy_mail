/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.mail;

import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;

/**
 *
 * @author Hermoine
 */
public class MailListener implements TransportListener, ConnectionListener {

    @Override
    public void messageDelivered(TransportEvent te) {
        System.out.println("Message Delivered");
    }

    @Override
    public void messageNotDelivered(TransportEvent te) {
        System.out.println("Message Could not be delivered");
    }

    @Override
    public void messagePartiallyDelivered(TransportEvent te) {
        System.out.println("Message Partially Delivered");
    }

    @Override
    public void opened(ConnectionEvent ce) {
        System.out.println("Connection opened");
    }

    @Override
    public void disconnected(ConnectionEvent ce) {
        System.out.println("Connection Disconnected");
    }

    @Override
    public void closed(ConnectionEvent ce) {
        System.out.println("Connection Closed");
    }
    
}

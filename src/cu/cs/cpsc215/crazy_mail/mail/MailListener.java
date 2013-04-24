/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.mail;

import cu.cs.cpsc215.crazy_mail.ui.MainFrame;
import cu.cs.cpsc215.crazy_mail.ui.StatusWindow;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.mail.event.ConnectionEvent;
import javax.mail.event.ConnectionListener;
import javax.mail.event.TransportEvent;
import javax.mail.event.TransportListener;

/**
 * @author Emmanuel
 */
public class MailListener implements TransportListener, ConnectionListener {
    private MainFrame main;
    public MailListener(){
        
    }
    public MailListener(MainFrame main){
        this.main = main;
    }
    @Override
    public void messageDelivered(TransportEvent te) {
        System.out.println("Message Delivered");
        
        if(main!=null){
            final StatusWindow statusWindow = new StatusWindow(main,"Message Delievered :)");
            int numberOfMillisecondsInTheFuture = 10000; // 10 sec
            
            Date timeToClose = new Date(System.currentTimeMillis()+numberOfMillisecondsInTheFuture);
            Timer timer = new Timer();

            timer.schedule(new TimerTask() {
                    @Override
                    public void run() {
                        statusWindow.dispose();
                    }
                }, timeToClose);
            }
    }

    @Override
    public void messageNotDelivered(TransportEvent te) {
        main.setStatus("Message could not be delivered: "+ te.getMessage());
    }

    @Override
    public void messagePartiallyDelivered(TransportEvent te) {
        main.setStatus("Message was delivered partially: "+ te.getMessage());
    }

    @Override
    public void opened(ConnectionEvent ce) {
        main.setStatus("Opened Connection...");
    }

    @Override
    public void disconnected(ConnectionEvent ce) {
         main.setStatus("Connection lost");
    }

    @Override
    public void closed(ConnectionEvent ce) {
         main.setStatus(" ");
    }
    
}

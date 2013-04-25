/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.cs.cpsc215.crazy_mail.ui.messages;

import cu.cs.cpsc215.crazy_mail.data.DataStore;
import cu.cs.cpsc215.crazy_mail.ui.FrameState;
import cu.cs.cpsc215.crazy_mail.util.MailAccount;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.Session;
import javax.mail.Store;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.SwingWorker;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Emmanuel
 */
public class SendState implements FrameState {
    private JPanel main_panel;
    private JPanel c_panel;
    private static SendState inst;
    private JPanel message_panel;
    private ArrayList<Component> states = new ArrayList();
    private HashMap<MailAccount, DefaultListModel> account_map = new HashMap();
    private JComboBox accountsBox;
    private JTextPane textpane;
    private JList messagelist;
    private SendState(){
        main_panel = new JPanel();
        c_panel = new JPanel();
        message_panel = new JPanel();
        main_panel.setLayout(new BorderLayout());
        c_panel.setLayout(new BorderLayout());
        message_panel.setLayout(new BorderLayout());
        
        message_panel.setBackground(Color.white);
        
        final DataStore dstore = DataStore.get();
        for(MailAccount account: dstore.getAccounts()){
            account_map.put(account, new DefaultListModel());
        }
        
        main_panel.add(createNorthPanel(account_map),"North");
        
        c_panel.add(messageListView(),BorderLayout.WEST);
        c_panel.add(message_panel, BorderLayout.CENTER);
        
        main_panel.add(c_panel, BorderLayout.CENTER);
        
        
        SwingWorker loadMessageWorker = new SwingWorker(){

            @Override
            protected Object doInBackground() throws Exception {
                for(final MailAccount account: account_map.keySet()){
                    Thread t = new Thread(new Runnable(){
                        @Override
                        public void run(){
                            try {
                                loadMessages(account, account_map.get(account));
                            } catch (NoSuchProviderException ex) {
                                Logger.getLogger(SendState.class.getName()).log(Level.SEVERE, null, ex);
                            } catch (MessagingException ex) {
                                Logger.getLogger(SendState.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    
                    t.start();
                }
                return null;
            }
        
        };
        
        loadMessageWorker.execute();
        
        Thread monitor_accounts = new Thread(new Runnable(){
            @Override
            public void run(){
                while(true){
                    if(dstore.getAccounts().size()>account_map.size()){
                        //update model
                        for(final MailAccount account: dstore.getAccounts()){
                            if(!account_map.containsKey(account)){
                                account_map.put(account, new DefaultListModel());
                                accountsBox.addItem(account);
                                new Thread(new Runnable(){
                                    @Override
                                    public void run(){
                                        try {
                                            loadMessages(account, account_map.get(account));
                                        } catch (NoSuchProviderException ex) {
                                            Logger.getLogger(SendState.class.getName()).log(Level.SEVERE, null, ex);
                                        } catch (MessagingException ex) {
                                            Logger.getLogger(SendState.class.getName()).log(Level.SEVERE, null, ex);
                                        }
                                    }
                                }).start();
                                
                            }
                        }
                    }
                    
                }
            }
        });
        
        monitor_accounts.start();
        
    }
    
    public static SendState get()
    {
		if(inst == null)
		{
			inst = new SendState();
		}
		return inst;
    }
    
    
    public JPanel messageListView(){
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setPreferredSize(new Dimension(300,700));
        MailAccount account = (MailAccount) accountsBox.getSelectedItem();
        DefaultListModel model = account_map.get(account);
        
        if(model==null){
            messagelist = new JList();
        }else{
            messagelist = new JList(model);
        }
        messagelist.setFixedCellHeight(60);
        messagelist.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                try {
                    textpane.setText(getMessageContent((Message)messagelist.getSelectedValue()));
                } catch (IOException ex) {
                    Logger.getLogger(SendState.class.getName()).log(Level.SEVERE, null, ex);
                } catch (MessagingException ex) {
                    Logger.getLogger(SendState.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        panel.add(new JScrollPane(messagelist));
        
        
        return panel;
        
    }
    
    public JPanel createMessageViewer(Message message) throws IOException, MessagingException{
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        textpane = new JTextPane();
        textpane.setEditable(false);
        textpane.setContentType("text/html");
        if(message == null){
            textpane.setText("");
            
        }else{
            textpane.setText(getMessageContent(message));
        }
        
        textpane.setMargin(new Insets(7,7,7,7));
        
        panel.add(new JScrollPane(textpane));
        
        return panel;
    }
    public void loadMessages(MailAccount account, DefaultListModel<Message> model) throws NoSuchProviderException, MessagingException{
        Properties props = System.getProperties();
        
        //create session object from properties file
        Session session = Session.getDefaultInstance(props,null);
        
        //get store object
        Store store = session.getStore(account.getIncomingMail().value());
        
        store.connect(account.getInHost(), account.getAccountEmail(), account.getAccountPassword());
        
        //create folder
        Folder inbox = store.getFolder("SENT");
        
        inbox.open(Folder.READ_ONLY);
        
        Message [] messages = inbox.getMessages();
        
        for(Message msg: messages){
            model.add(0, msg);
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
                if (part.isMimeType("text/html")||part.isMimeType("text/plain")) {
                    messageContent.append(part.getContent().toString());
                }
                
            }
            return messageContent.toString();
        } else {
            return content.toString();
        }
    }
    public JPanel createNorthPanel(HashMap<MailAccount, DefaultListModel> accounts){
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        accountsBox = new JComboBox(accounts.keySet().toArray());
        accountsBox.addItemListener(new ItemListener(){

            @Override
            public void itemStateChanged(ItemEvent e) {
                MailAccount account = (MailAccount) accountsBox.getSelectedItem();
                
                messagelist.setModel(account_map.get(account));
            }
            
        });
        panel.add(accountsBox);
        
        return panel;
    }
    @Override
    public void onHide() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void onShow() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getName() {
        return "Sent";//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public JPanel getPanel() {
        return main_panel;
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}

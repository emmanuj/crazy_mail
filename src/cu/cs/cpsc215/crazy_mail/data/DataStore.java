package cu.cs.cpsc215.crazy_mail.data;

import cu.cs.cpsc215.crazy_mail.util.Configuration;
import cu.cs.cpsc215.crazy_mail.util.Validator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import cu.cs.cpsc215.crazy_mail.exceptions.ConfigurationException;
import cu.cs.cpsc215.crazy_mail.util.MailAccount;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*@Author Emmanuel John
*@Date 04/12/13
* 
* Responsible for persistence operations. Data is persisted as objects
* It uses ObjectInputStream and ObjectOutputStream as a result all objects passed to 
* the datastore must implement Serializable
*/

public final class DataStore{

    public Map<Long, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Map<Long, Contact> contacts) {
        this.contacts = contacts;
    }

    public Configuration getConfig(String accountEmail) {
        return config;
    }

    public Contact getContact(String email){
        return null;
    }
    
    public Contact getContact(long id){
        return null;
    }
    
    private DataStore() {
        this.contacts = new HashMap();
        try {
            loadContacts();
            loadConfigurations();
        } catch (IOException ex) {
            Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static DataStore initDataStore(){
        if(!Validator.validateNotNull(store))
            store = new DataStore();
        
        return store;
    }
    
    public void loadContacts() throws IOException, ClassNotFoundException{
        is = new ObjectInputStream(new FileInputStream("cdb.dat"));
        
        contacts = (Map<Long, Contact>) is.readObject();
        
        
    }
    //TODO Check if contact exists before saving
    public void saveContact(Contact c) throws IOException{
        c.setContactID(generateId());
        
        contacts.put(c.getContactID(), c);
        
        new Thread(new Runnable(){
            @Override
            public void run(){
                try {
                    saveContacts(contacts);
                } catch (IOException ex) {
                    Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }).start();
        
        
        
    }
    
    public void saveContacts(Map<Long,Contact> contacts) throws IOException{
        os = new ObjectOutputStream(new FileOutputStream("cdb.dat"));
        
        os.writeObject(contacts);
        
    }
    
    public void loadConfigurations() throws IOException, ClassNotFoundException{
        is = new ObjectInputStream(new FileInputStream("fdb.config"));
        
        mailaccounts = (ArrayList) is.readObject();
        
    }
    //TODO Check if mail account exists before saving
    public void saveConfiguration(MailAccount mailAcount) throws ConfigurationException,
            IOException{
        os = new ObjectOutputStream(new FileOutputStream("fdb.config"));
        
        mailaccounts.add(mailAcount);
        os.writeObject(mailaccounts);
        
    }
    
    public ArrayList<Contact> findAllByEmail(String email){
        return null;
    }
    
    public ArrayList<Contact> findAllByFirstname(String firstname){
        return null;
    }

    public ArrayList<Contact> findAllByLastname(String lastname){
        return null;
    }
    
    public ArrayList<Contact> findAllByCity(String city){
        return null;
    }
    
    public ArrayList<Contact> findAllByState(String state){
        return null;
    }
    
    public ArrayList<Contact> findAllByZip(String zip){
        return null;
    }
    
    private long generateId() throws IOException{
        
        long idx = 1;
        if(contacts.size()>=1){
            idx = contacts.size()+1;
        }
        
        return idx;
    }
    
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private Map<Long,Contact> contacts;
     private Configuration config;
    private ArrayList<MailAccount> mailaccounts = new ArrayList();
    private static DataStore store;
}
package cu.cs.cpsc215.crazy_mail.data;

import cu.cs.cpsc215.crazy_mail.util.Configuration;
import cu.cs.cpsc215.crazy_mail.util.Validator;
import java.io.IOException;
import cu.cs.cpsc215.crazy_mail.exceptions.ConfigurationException;
import cu.cs.cpsc215.crazy_mail.util.MailAccount;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
*@Author Emmanuel John
*@Author Kevin Jett
* 
* Responsible for persistence operations. Data is persisted as objects
* It uses ObjectInputStream and ObjectOutputStream as a result all objects passed to 
* the datastore must implement Serializable
*/

public final class DataStore{

	//Singleton
	public static DataStore initDataStore(){
	    if(!Validator.validateNotNull(store))
	        store = new DataStore();
	    
	    return store;
	}
	public static DataStore get(){ //short hand
		return initDataStore();
	}
	    
	//Getters
	public ArrayList<MailAccount> getAccounts(){
		return mailaccounts;
	}

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public Configuration getConfig(String accountEmail) {
        return config;
    }

    public Contact getContact(String email){
        return null;
    }
    
    public Contact getContact(int id){
        return contacts.get(id);
    }
    
    private DataStore(){
        this.contacts = new ArrayList<Contact>();
        try {
            loadContacts();
            loadConfigurations();
        } catch (FileNotFoundException e){ //Make the file if it's not found
        	File f = new File("cdb.dat");
        	try
        	{
				f.createNewFile();
			} catch (IOException e1) {
				Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE,null,e1);
			}
    	} 
        catch (EOFException eof){ //Do nothing for eof exceptions...
    	}
        catch (IOException ex) { //Log all other exceptions
            Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
    public void loadContacts() throws IOException, ClassNotFoundException{
        is = new ObjectInputStream(new FileInputStream("cdb.dat"));
        
        contacts = (ArrayList<Contact>) is.readObject();
        
        
    }
    //TODO Check if contact exists before saving
    public void saveContact(Contact c) throws IOException{
        c.setContactID(generateId());
        
        contacts.add(c);
        
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
    
    public void saveContacts(ArrayList<Contact> contacts) throws IOException{
        os = new ObjectOutputStream(new FileOutputStream("cdb.dat"));
        
        os.writeObject(contacts);
        
    }
    
    public void loadConfigurations() throws IOException, ClassNotFoundException{
        is = new ObjectInputStream(new FileInputStream("fdb.config"));
        
        mailaccounts = (ArrayList) is.readObject();
        
    }
    //TODO Check if mail account exists before saving
    public void saveConfiguration(MailAccount mailAccount) throws ConfigurationException,
            IOException{
        if(mailaccounts.contains(mailAccount))
            return;

        os = new ObjectOutputStream(new FileOutputStream("fdb.config"));
        
        mailaccounts.add(mailAccount);
        os.writeObject(mailaccounts);
        
    }
    
    public void saveAll()
    {
    	
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
    
    public void setAccounts(ArrayList<MailAccount> accounts)
    {
    	mailaccounts = accounts;
    }
    
    public void addMailAccount(MailAccount account)
    {
    	mailaccounts.add(account);
    }
    
    public void setPrimary(MailAccount account)
    {
    	if(mailaccounts.contains(account))
    	{
    		int index = mailaccounts.indexOf(account);
    		mailaccounts.remove(index);
    	}
    	mailaccounts.add(0,account);
    }
    
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private ArrayList<Contact> contacts;
    private Configuration config;
    private ArrayList<MailAccount> mailaccounts = new ArrayList<MailAccount>();
    private static DataStore store;
}
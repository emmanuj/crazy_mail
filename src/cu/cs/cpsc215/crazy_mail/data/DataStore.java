package cu.cs.cpsc215.crazy_mail.data;

import cu.cs.cpsc215.crazy_mail.ui.MainFrame;
import cu.cs.cpsc215.crazy_mail.util.Configuration;
import java.io.IOException;
import cu.cs.cpsc215.crazy_mail.util.MailAccount;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

/**
*@Author Emmanuel John
*@Author Kevin Jett
* 
* Responsible for persistence operations. Data is persisted as objects
* It uses ObjectInputStream and ObjectOutputStream as a result all objects passed to 
* the datastore must implement Serializable
*/

public final class DataStore{
    private ArrayList<Contact> contacts;
    private Configuration config;
    private ArrayList<MailAccount> mailaccounts = new ArrayList<MailAccount>();
    private static DataStore store;
    
	//Singleton
	public static DataStore get(){
	    if(store == null)
	        store = new DataStore();
	    
	    return store;
	}
	
    private DataStore(){
    	File f = new File("cdb.dat");
    	
    	//Make config file if it doesn't exist
    	if(!f.exists())
    	{
    		try
        	{
				f.createNewFile();
			} catch (IOException e1) {
				Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE,null,e1);
				JOptionPane.showMessageDialog(MainFrame.getInst(),"<html><b>FATAL ERROR</b><br/>Unable to make config file.</html>","ERROR",JOptionPane.ERROR_MESSAGE);
			}
    	}
    	
    	//Attempt to load everything
        try {	
        	loadAll();
        }
        catch (EOFException eof){ //Do nothing for eof exceptions...
    	}
        catch (IOException ex) { //Log all other exceptions
            Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataStore.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	//Getters
	public ArrayList<MailAccount> getAccounts(){
		return mailaccounts;
	}

    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    public Configuration getPrimaryAccount(String accountEmail) {
        return config;
    }
    
    public Contact getContact(int id){
        return contacts.get(id);
    }
            
    public void addContact(Contact c)
    {
    	c.setContactID(generateId());
    	contacts.add(c);
    }
    
    public void addConfiguration(MailAccount mailAccount)
    {
    	if(mailaccounts.contains(mailAccount))
            return;
    	
    	mailaccounts.add(mailAccount);
    }
        
    public void addMailAccount(MailAccount account)
    {
    	mailaccounts.add(account);
    }
    
    public void setAccounts(ArrayList<MailAccount> accounts)
    {
    	mailaccounts = accounts;        
    }
    
    public void setContacts(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }
    

    
    public void setPrimary(MailAccount account)
    {
    	if(mailaccounts.contains(account))
    	{
    		int index = mailaccounts.indexOf(account);
    		mailaccounts.remove(index);
    	}
    	mailaccounts.add(0,account);
    	config = account;
    }
    
    private long generateId(){
        
        long idx = 1;
        if(contacts.size()>=1){
            idx = contacts.size()+1;
        }
        
        return idx;
    }
    
    //Loading...
    public void loadAll() throws IOException, ClassNotFoundException{
    	contacts = new ArrayList<Contact>();
    	mailaccounts = new ArrayList<MailAccount>();
    	File f = new File("fdb.config");
    	if(!f.exists())
    	{
    		f.createNewFile();
    	}
        ObjectInputStream is = new ObjectInputStream(new FileInputStream("fdb.config"));
        contacts = (ArrayList) is.readObject();
        mailaccounts = (ArrayList) is.readObject();
        
        System.out.println("Loaded");
        is.close();
    }
        
    //Saving...
    public void saveAll() throws IOException
    {
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("fdb.config"));
        os.writeObject(contacts);
        os.writeObject(mailaccounts);
        os.close();
    }
}
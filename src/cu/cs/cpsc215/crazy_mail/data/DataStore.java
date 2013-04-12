package cu.cs.cpsc215.crazy_mail.data;

import cu.cs.cpsc215.crazy_mail.util.Configuration;
import cu.cs.cpsc215.crazy_mail.util.Validator;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import cu.cs.cpsc215.crazy_mail.exceptions.ConfigurationException;
import java.util.ArrayList;

/**
*@Author Emmanuel John
*@Date 04/12/13
* 
* Responsible for persistence operations. Data is persisted as objects
* It uses ObjectInputStream and ObjectOutputStream as a result all objects passed to 
* the datastore must implement Serializable
*/

public class DataStore{

    public Map<Long, Contact> getContacts() {
        return contacts;
    }

    public void setContacts(Map<Long, Contact> contacts) {
        this.contacts = contacts;
    }

    public Configuration getConfig() {
        return config;
    }

    public void setConfig(Configuration config) {
        this.config = config;
    }
    
    public Contact getContact(String email){
        return null;
    }
    
    public Contact getContact(long id){
        return null;
    }
    
    private DataStore() {
        this.contacts = new HashMap();
        
    }
    
    public static DataStore initDataStore(){
        if(!Validator.validateNotNull(store))
            store = new DataStore();
        
        return store;
    }
    
    public void loadContacts() throws IOException{
        //TODO: load all contact objects into map
    }
    
    public void saveContact(Contact c) throws IOException{
        
    }
    
    public void saveContacts(ArrayList<Contact> contacts) throws IOException{
        
    }
    
    public void loadConfig() throws IOException{
        //get config object from file and store it in the config variable
    }
    
    public void saveConfig(Configuration config) throws ConfigurationException,
            IOException{
        
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
    
    private Map<Long,Contact> contacts;
    private Configuration config;
    private static DataStore store;
}
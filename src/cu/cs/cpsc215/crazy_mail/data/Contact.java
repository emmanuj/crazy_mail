package cu.cs.cpsc215.crazy_mail.data;

/**
*@Author Emmanuel John
*@Date 04/12/13
*
*/

import java.io.Serializable;
public class Contact implements Serializable{
    public Contact(){
        
    }
    public Contact(String email) {
        this.email = email;
    }

    public Contact(String firstname, String email) {
        this.firstname = firstname;
        this.email = email;
    }

    public Contact(String firstname, String lastname, long contactID, String email, String cellphone, String homephone, Address address) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.contactID = contactID;
        this.email = email;
        this.cellphone = cellphone;
        this.homephone = homephone;
        this.address = address;
    }
    
    public String getFirstname(){
            return this.firstname;
    }

    public String getLastname(){
            return this.lastname;
    }

    public long getContactID(){
            return this.contactID;
    }

    public String getEmail(){
            return this.email;
    }

    public Address getAddress(){
            return this.address;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getHomephone() {
        return homephone;
    }

    public void setHomephone(String homephone) {
        this.homephone = homephone;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setContactID(long contactID) {
        this.contactID = contactID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
    
    private String firstname;
    private String lastname;
    private long contactID;
    private String email;
    private String cellphone;
    private String homephone;
    private Address address;
        

}
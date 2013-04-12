package cu.cs.cpsc215.crazy_mail.data;

/**
*@Author Emmanuel John, Kevin Jett
*@Date 04/12/13
*
*/

import java.io.Serializable;
public class Contact implements Serializable{
	private String firstname;
	private String lastname;
	private long contactID;
	private String email;
	//private ENUM phone;
	private Address address;

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

}
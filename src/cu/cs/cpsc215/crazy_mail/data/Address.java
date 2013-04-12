package cu.cs.cpsc215.crazy_mail.data;

/**
*@Author Emmanuel John
*@Date 04/12/13
*
*/
import java.io.Serializable;

public class Address implements Serializable{
	private String street;
	private String city;
	private String state;
	private String zip;

	public Address(String street, String city, String state, String zip){
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Address(){
		this("","","","");
	}

	public String getStreet(){
		return this.street;
	}

	public String getCity(){
		return this.city;
	}

	public String getState(){
		return this.state;
	}

	public String getZip(){
		return this.zip;
	}

	public void setStreet(String street){
		this.street = street;
	}

	public void setCity(String city){
		this.city= city;
	}

	public void setState(String state){
		this.state = state;
	}

	public void setZip(String zip){
		this.zip = zip;
	}

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer();
		sb.append("<")
		.append(this.getStreet())
		.append(", ")
		.append(this.getCity())
		.append(", ")
		.append(this.getState())
		.append(" ")
		.append(this.getZip())
		.append(">");
		return sb.toString();

	}

	@Override
	public boolean equals(Object obj){
		if (!(obj instanceof Address))
			return false;

		Address adr = (Address) obj;
		if(adr.getCity().equals(this.city) && adr.getStreet().equals(this.street) && 
			adr.getState().equals(this.state) && adr.getZip().equals(this.zip))
			return true;

		return false;
	}

}
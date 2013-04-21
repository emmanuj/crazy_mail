package cu.cs.cpsc215.crazy_mail.data;

/**
*@Author Emmanuel John
*@Date 04/12/13
*
*/
import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable{
	private static final long serialVersionUID = 4267012014955078038L;
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

    public String toString() {
        return street + " "+city+" "+state+" "+zip;
    	//return "Address{" + "street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + '}';
    }
    
    public static String[] getStates(){
    	String[] states = 
    		{ "", "Alabama", "Alaska", "American Samoa", "Arizona","Arkansas",
    		"California", "Colorado", "Connecticut", "Delaware", "District of Columbia",
    		"Florida", "Georgia", "Guam", "Hawaii", "Idaho", "Illinois",
    		"Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland",
    		"Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri",
    		"Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico",
    		"New York", "North Carolina", "North Dakota", "Northern Marianas Islands",
    		"Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Puerto Rico", "Rhode Island",
    		"South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont",
    		"Virginia", "Virgin Islands", "Washington", "West Virginia", "Wisconsin",
    		"Wyoming" };
    	return states;
    }

    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.street);
        hash = 41 * hash + Objects.hashCode(this.city);
        hash = 41 * hash + Objects.hashCode(this.state);
        hash = 41 * hash + Objects.hashCode(this.zip);
        return hash;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Address other = (Address) obj;
        if (!Objects.equals(this.street, other.street)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.state, other.state)) {
            return false;
        }
        if (!Objects.equals(this.zip, other.zip)) {
            return false;
        }
        return true;
    }

    

	

}
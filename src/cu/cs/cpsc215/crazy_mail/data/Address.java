package cu.cs.cpsc215.crazy_mail.data;

/**
*@Author Emmanuel John
*@Date 04/12/13
*
*/
import java.io.Serializable;
import java.util.Objects;

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
    public String toString() {
        return "Address{" + "street=" + street + ", city=" + city + ", state=" + state + ", zip=" + zip + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.street);
        hash = 41 * hash + Objects.hashCode(this.city);
        hash = 41 * hash + Objects.hashCode(this.state);
        hash = 41 * hash + Objects.hashCode(this.zip);
        return hash;
    }

    @Override
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

package cu.cs.cpsc215.crazy_mail.util;

import java.io.Serializable;

/**
 *
 * @author Emmanuel John
 * Represents User email accounts
 * 
 */
public class MailAccount extends Configuration implements Serializable {
	private static final long serialVersionUID = 160333513933669500L;
	private String accountEmail;
    private String accountPassword;
    private String fullname;
    public MailAccount(){
        
    }

    public String getAccountEmail() {
        return accountEmail;
    }

    public void setAccountEmail(String accountEmail) {
        this.accountEmail = accountEmail;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }


    public boolean equalsNameEmail(MailAccount other) {
        if (other== null) {
            return false;
        }
        
        return(accountEmail.equals(other.accountEmail) && fullname.equals(other.fullname));
    }
    
    @Override  
    public boolean equals(Object obj)
    {
    	if (obj == null) { 
    		return false;
    	}  
        if (getClass() != obj.getClass()) { 
        	return false;
        }  
    	
    	if(obj.getClass() != this.getClass())
    	{
    		return false;
    	}
    	MailAccount other = (MailAccount)obj;

    	if(!this.equalsNameEmail(other))
    	{
    		return false;
    	}
    	
    	return(super.equals(obj));
    }

    @Override
    public String toString() {
        return fullname + " <"  + accountEmail +  ">";
    }
    
    
    
    
}

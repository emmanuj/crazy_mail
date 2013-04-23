
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


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MailAccount other = (MailAccount) obj;
        if (accountEmail.equals(other.accountEmail)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return fullname + " <"  + accountEmail +  ">";
    }
    
    
    
    
}

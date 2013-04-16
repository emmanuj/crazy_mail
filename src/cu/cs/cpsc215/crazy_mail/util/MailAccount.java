
package cu.cs.cpsc215.crazy_mail.util;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Emmanuel John
 * Represents User email accounts
 * 
 */
public class MailAccount extends Configuration implements Serializable {
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
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.accountEmail);
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
        final MailAccount other = (MailAccount) obj;
        if (!Objects.equals(this.accountEmail, other.accountEmail)) {
            return false;
        }
        return true;
    }
    
    
    
}

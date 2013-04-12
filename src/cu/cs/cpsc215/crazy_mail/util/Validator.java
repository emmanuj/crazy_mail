
package cu.cs.cpsc215.crazy_mail.util;

/**
 *
 * @author Emmanuel John
 * 
 * This class contains frequently used validators 
 * it used especially before persisting fields
 */

public class Validator {
    public static boolean validatePhone(String phone){
        return false;
    }
    
    public static boolean validateEmail(String email){
        if(email.equals(""))
            return false;
        
        
        return false;
    }
    
    public static boolean validateNotNull(Object obj){
        return obj!=null;
    }
    
    public static boolean validateBlank(String str){
        return !str.equals("");
    }
    
}

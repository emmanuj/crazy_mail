
package cu.cs.cpsc215.crazy_mail.util;

/**
 *
 * @author Emmanuel John
 * @author Kevin Jett
 * 
 * This class contains frequently used validators 
 * it used especially before persisting fields
 */

public class Validator {
    public static boolean validatePhone(String phone){
        return true;
    }
    
    public static boolean validateEmail(String email){
        if(email.equals(""))
            return false;
        
        if(!email.contains("@"))
        {
        	return false;
        }
        
        return true;

    }
    
    public static boolean validateNotNull(Object obj){
        return obj!=null;
    }
    
    public static boolean validateBlank(String str){
        return !str.equals("");
    }
    
    public static boolean validateNumericString(String str){
    	for(int i = 0; i<str.length(); i++)
    	{
    		try{
    			Integer.parseInt(""+str.charAt(i));
    		} catch (NumberFormatException e){
    			return false;
    		}
    	}
    	return true;
    }
    
}

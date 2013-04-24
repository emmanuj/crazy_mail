
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
        
        String[] parsedEmail = email.split("@");
        if(parsedEmail.length!=2)
            return false;
        
        String user = parsedEmail[0];
        
        //String [] secondPart = parsedEmail[1];
        
        
        if(user.equals("") ){
            return false;
        }
        
        
        
        
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
        if(str.length() == 0)
        	return false;
        
        for(int i = 0; i<str.length(); i++)
        {
        	if(str.charAt(i) != ' ' && str.charAt(i) != '\t')
        	{
        		return true;
        	}
        }
        return false;
    }
        
    public static boolean validateNumericString(String str){
    	if(str.length() == 0)
    		return false;
    	
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

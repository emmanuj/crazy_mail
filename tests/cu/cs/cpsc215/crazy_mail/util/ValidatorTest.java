
package cu.cs.cpsc215.crazy_mail.util;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Emmanuel John
 */
public class ValidatorTest {
    
    public ValidatorTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of validatePhone method, of class Validator.
     */
    @Test
    public void testValidatePhone() {
        System.out.println("validatePhone");
        String phone = "";
        boolean expResult = false;
        boolean result = Validator.validatePhone(phone);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateEmail method, of class Validator.
     */
    @Test
    public void testValidateEmail() {
        System.out.println("validateEmail");
        String email = "";
        boolean expResult = false;
        boolean result = Validator.validateEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateNotNull method, of class Validator.
     */
    @Test
    public void testValidateNotNull() {
        System.out.println("validateNotNull");
        Object obj = null;
        boolean expResult = false;
        boolean result = Validator.validateNotNull(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateBlank method, of class Validator.
     */
    @Test
    public void testValidateBlank() {
        System.out.println("validateBlank");
        String str = "";
        boolean expResult = false;
        boolean result = Validator.validateBlank(str);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
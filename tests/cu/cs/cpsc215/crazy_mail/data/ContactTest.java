package cu.cs.cpsc215.crazy_mail.data;

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
public class ContactTest {
    
    public ContactTest() {
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
     * Test of getFirstname method, of class Contact.
     */
    @Test
    public void testGetFirstname() {
        System.out.println("getFirstname");
        Contact instance = new Contact();
        String expResult = "";
        String result = instance.getFirstname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLastname method, of class Contact.
     */
    @Test
    public void testGetLastname() {
        System.out.println("getLastname");
        Contact instance = new Contact();
        String expResult = "";
        String result = instance.getLastname();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContactID method, of class Contact.
     */
    @Test
    public void testGetContactID() {
        System.out.println("getContactID");
        Contact instance = new Contact();
        long expResult = 0L;
        long result = instance.getContactID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmail method, of class Contact.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        Contact instance = new Contact();
        String expResult = "";
        String result = instance.getEmail();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAddress method, of class Contact.
     */
    @Test
    public void testGetAddress() {
        System.out.println("getAddress");
        Contact instance = new Contact();
        Address expResult = null;
        Address result = instance.getAddress();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCellphone method, of class Contact.
     */
    @Test
    public void testGetCellphone() {
        System.out.println("getCellphone");
        Contact instance = new Contact();
        String expResult = "";
        String result = instance.getCellphone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCellphone method, of class Contact.
     */
    @Test
    public void testSetCellphone() {
        System.out.println("setCellphone");
        String cellphone = "";
        Contact instance = new Contact();
        instance.setCellphone(cellphone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHomephone method, of class Contact.
     */
    @Test
    public void testGetHomephone() {
        System.out.println("getHomephone");
        Contact instance = new Contact();
        String expResult = "";
        String result = instance.getHomephone();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHomephone method, of class Contact.
     */
    @Test
    public void testSetHomephone() {
        System.out.println("setHomephone");
        String homephone = "";
        Contact instance = new Contact();
        instance.setHomephone(homephone);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstname method, of class Contact.
     */
    @Test
    public void testSetFirstname() {
        System.out.println("setFirstname");
        String firstname = "";
        Contact instance = new Contact();
        instance.setFirstname(firstname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastname method, of class Contact.
     */
    @Test
    public void testSetLastname() {
        System.out.println("setLastname");
        String lastname = "";
        Contact instance = new Contact();
        instance.setLastname(lastname);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContactID method, of class Contact.
     */
    @Test
    public void testSetContactID() {
        System.out.println("setContactID");
        long contactID = 0L;
        Contact instance = new Contact();
        instance.setContactID(contactID);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmail method, of class Contact.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "";
        Contact instance = new Contact();
        instance.setEmail(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAddress method, of class Contact.
     */
    @Test
    public void testSetAddress() {
        System.out.println("setAddress");
        Address address = null;
        Contact instance = new Contact();
        instance.setAddress(address);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
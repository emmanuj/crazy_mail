
package cu.cs.cpsc215.crazy_mail.data;

import cu.cs.cpsc215.crazy_mail.util.Configuration;
import java.util.ArrayList;
import java.util.Map;
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
public class DataStoreTest {
    
    public DataStoreTest() {
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
     * Test of getContacts method, of class DataStore.
     */
    @Test
    public void testGetContacts() {
        System.out.println("getContacts");
        DataStore instance = null;
        Map expResult = null;
        //Map result = instance.getContacts();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContacts method, of class DataStore.
     */
    @Test
    public void testSetContacts() {
        System.out.println("setContacts");
        Map<Long, Contact> contacts = null;
        DataStore instance = null;
        //instance.setContacts(contacts);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConfig method, of class DataStore.
     */
    @Test
    public void testGetConfig() {
        System.out.println("getConfig");
        DataStore instance = null;
        Configuration expResult = null;
//        Configuration result = instance.getConfig();
       // assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConfig method, of class DataStore.
     */
    @Test
    public void testSetConfig() {
        System.out.println("setConfig");
        Configuration config = null;
        DataStore instance = null;
//        instance.setConfig(config);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContact method, of class DataStore.
     */
    @Test
    public void testGetContact_String() {
        System.out.println("getContact");
        String email = "";
        DataStore instance = null;
        Contact expResult = null;
        Contact result = instance.getContact(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContact method, of class DataStore.
     */
    @Test
    public void testGetContact_long() {
        System.out.println("getContact");
        long id = 0L;
        DataStore instance = null;
        Contact expResult = null;
        //Contact result = instance.getContact(id);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initDataStore method, of class DataStore.
     */
    @Test
    public void testInitDataStore() {
        System.out.println("initDataStore");
        DataStore expResult = null;
        DataStore result = DataStore.initDataStore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadConfig method, of class DataStore.
     */
    @Test
    public void testLoadConfig() throws Exception {
        System.out.println("loadConfig");
        DataStore instance = null;
//        instance.loadConfig();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveConfig method, of class DataStore.
     */
    @Test
    public void testSaveConfig() throws Exception {
        System.out.println("saveConfig");
        Configuration config = null;
        DataStore instance = null;
        //instance.saveConfig(config);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByEmail method, of class DataStore.
     */
    @Test
    public void testFindAllByEmail() {
        System.out.println("findAllByEmail");
        String email = "";
        DataStore instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.findAllByEmail(email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByFirstname method, of class DataStore.
     */
    @Test
    public void testFindAllByFirstname() {
        System.out.println("findAllByFirstname");
        String firstname = "";
        DataStore instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.findAllByFirstname(firstname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByLastname method, of class DataStore.
     */
    @Test
    public void testFindAllByLastname() {
        System.out.println("findAllByLastname");
        String lastname = "";
        DataStore instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.findAllByLastname(lastname);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByCity method, of class DataStore.
     */
    @Test
    public void testFindAllByCity() {
        System.out.println("findAllByCity");
        String city = "";
        DataStore instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.findAllByCity(city);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByState method, of class DataStore.
     */
    @Test
    public void testFindAllByState() {
        System.out.println("findAllByState");
        String state = "";
        DataStore instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.findAllByState(state);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findAllByZip method, of class DataStore.
     */
    @Test
    public void testFindAllByZip() {
        System.out.println("findAllByZip");
        String zip = "";
        DataStore instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.findAllByZip(zip);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
    @Test
    public void testGenerateId(){
        System.out.println("generateID");
        
        
        
        long expResult = 1;
        
        //long id = 
    }
}
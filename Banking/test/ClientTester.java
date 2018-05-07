/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import banking.Client;
import banking.Date;
import banking.WithdrawAmountException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mahmoud
 */
public class ClientTester {
    
    public ClientTester() {
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

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    @Test
    public void newClientTest() {
        //Testing if the newly created isn't equal to null
        Client c = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "0000", "male");
        assertNotNull(c);
        
        //Testing if the newly created account values are initiallized correctly
        assertEquals("Mahmoud", c.getName());
        assertEquals(200.0, c.getBalance(), 0.0001);
        assertEquals("0000", c.getNumber());
        assertEquals("male", c.getGender());
    }
    
    @Test
    public void depositTest() {
        Client c = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "0000", "male");
        c.deposit(50);
        
        assertEquals(250.0, c.getBalance(), 0.0001);
        
        c.deposit(-10);
        assertEquals(250.0, c.getBalance(), 0.0001);
    }
    
    @Test
    public void withdrawTest() {
        Client c = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "0000", "male");
        
        try {
            c.withdraw(15);
            assertEquals(185, c.getBalance(), 0.0001);
        } catch(WithdrawAmountException ex) {
            fail("Function shouldn't throw an exception in this case");
        }
        
        try {
            c.withdraw(300);
            fail("Function shouldn't complete the try clause");
        } catch(WithdrawAmountException ex) {
            assertTrue(true);
        }
        
    }
    
    @Test
    public void transferToTest() {
        Client c1 = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "0000", "male");
        Client c2 = new Client("Mohamed", 200.0, new Date(17, 8, 1996), "0001", "male");
        
        try {
            Client.transferTo(c1, c2, 300);
            fail("Method should fail since that the amount to be withdrawn is more than the balance");
        } catch(WithdrawAmountException ex) {
            assertTrue(true);
        }
        
        try{
            Client.transferTo(c1, c2, 30);
            assertEquals(170, c1.getBalance(), 0.0001);
            assertEquals(230, c2.getBalance(), 0.0001);
        } catch(WithdrawAmountException ex) {
            fail("Function shouldn't throw an exception");
        }
    }
    
    @Test
    public void depositWithdrawTest() {
        Client c = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "0000", "male");
        c.deposit(100);
        c.deposit(50);
        
        try{
            c.withdraw(200);
        } catch(WithdrawAmountException ex) {
            fail("Shouldn't throw an exception");
        }
        
        assertEquals(150, c.getBalance(), 0.0001);
    }
}

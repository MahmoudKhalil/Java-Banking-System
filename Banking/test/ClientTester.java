/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import banking.Client;
import banking.Date;
import banking.NegativeMoneyException;
import banking.WithdrawAmountException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
        
        //Testing valid input and invalid inputs and exception handling
        try {
            c.deposit(50);

            assertEquals(250.0, c.getBalance(), 0.0001);
        } catch(NegativeMoneyException ex) {
            fail("Exception shouldn't be thrown");
        }
        
        try {
            c.deposit(-10);
            fail("Method should throw NegativeMoneyException");
        } catch(NegativeMoneyException ex) {
            assertEquals(250.0, c.getBalance(), 0.0001);
        }
        
        //Testing  vary Large double numbers
        try {
            c.deposit(Math.pow(2, 30));
            assertEquals(Math.pow(2, 30) + 250.0, c.getBalance(), 0.0001);
            double oldBalance = c.getBalance();
            c.deposit(Math.pow(2, 5));
            assertEquals(Math.pow(2, 5) + oldBalance, c.getBalance(), 0.0001);
            oldBalance = c.getBalance();
            c.deposit(Math.pow(2, 10));
            assertEquals(Math.pow(2, 10) + oldBalance, c.getBalance(), 0.00001);
        } catch(NegativeMoneyException ex) {
            
        }
    }
    
    @Test
    public void withdrawTest() {
        Client c = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "0000", "male");
        
        try {
            c.withdraw(0);
            assertEquals(200.0, c.getBalance(), 0.0001);
        } catch(WithdrawAmountException ex) {
            
        } catch(NegativeMoneyException ex) {
            
        }
        
        try {
            c.withdraw(15);
            assertEquals(185, c.getBalance(), 0.0001);
        } catch(WithdrawAmountException ex) {
            fail("Function shouldn't throw an exception in this case");
        } catch(NegativeMoneyException ex) {
            fail("Method shouldn't throw NegativeMoneyException");
        }
        
        try {
            c.withdraw(300);
            fail("Function shouldn't complete the try clause");
        } catch(WithdrawAmountException ex) {
            assertEquals(185, c.getBalance(), 0.0001);
        } catch(NegativeMoneyException ex) {
            assertEquals(185, c.getBalance(), 0.0001);
        }
        
        
        //testing invalid input
        try {
            c.withdraw(-100);
            fail("Method should throw NegativeMoneyException");
        } catch(NegativeMoneyException ex) {
            assertTrue(true);
        } catch(WithdrawAmountException ex) {
            
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
            assertEquals(200.0, c1.getBalance(), 0.0001);
            assertEquals(200.0, c2.getBalance(), 0.0001);
        } catch(NegativeMoneyException ex) {
            assertEquals(200.0, c1.getBalance(), 0.0001);
            assertEquals(200.0, c2.getBalance(), 0.0001);
        }
        
        try{
            Client.transferTo(c1, c2, 30);
            assertEquals(170, c1.getBalance(), 0.0001);
            assertEquals(230, c2.getBalance(), 0.0001);
        } catch(WithdrawAmountException ex) {
            fail("Function shouldn't throw an exception");
        } catch(NegativeMoneyException ex) {
            fail("Method shouldn't throw an exception");
        }
    }
    
    @Test
    public void depositWithdrawTest() {
        Client c = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "0000", "male");
        try {
            c.deposit(100);
            c.deposit(50);
        } catch(NegativeMoneyException ex) {
            
        }
        
        try{
            c.withdraw(200);
        } catch(WithdrawAmountException ex) {
            fail("Shouldn't throw an exception");
        } catch(NegativeMoneyException ex) {
            fail("Shouldn't throw an exception");
        }
        
        assertEquals(150, c.getBalance(), 0.0001);
    }
    
    @Test
    public void saveTest() {
        Client c = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "0000", "male");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("Accounts.data", false);
            oos = new ObjectOutputStream(fos);
            c.save(fos, oos);
            oos.close();
            fos.close();
            assertTrue(true);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Test
    public void saveLoadTest() {
        Client c = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "50", "male");
        try {
            FileOutputStream fos = new FileOutputStream("Accounts.data", false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            c.save(fos, oos);
            assertTrue(true);
            
            FileInputStream fis = new FileInputStream("Accounts.data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Client c2 = Client.load(fis, ois);
            assertNotNull(c);
            
            assertEquals(c2.getName(), c.getName());
            assertEquals(c2.getBalance(), c.getBalance(), 0.0000001);
            assertEquals(c2.getNumber(), c.getNumber());
            assertEquals(c2.getGender(), c.getGender());
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch(ClassNotFoundException ex) {
            fail("saveLoadTest shouldn't throw a ClassNotFoundException");
        }
    }
}

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
    public void depositWithdrawTest() {
        Client c = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "0000", "male");
        try {
            c.deposit(100);
            c.deposit(50);
        } catch(NegativeMoneyException ex) {
            fail("Shouldn't throw NegativeMoneyException");
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
    public void depositTransferTest() {
        Client c = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "0000", "male");
        Client c2 = new Client("Mohamed", 200.0, new Date(17, 8, 1997), "0000", "male");
        try {
            c.deposit(100);
            assertEquals(300.0, c.getBalance(), 0.000001);
        } catch(NegativeMoneyException ex) {
            fail("Shouldn't throw NegativeMOneyException");
        }
        
        try {
            c.transferTo(c2, 200);
            assertEquals(100, c.getBalance(), 0.0000001);
            assertEquals(400.0, c2.getBalance(), 0.000001);
        } catch(NegativeMoneyException ex) {
            fail("Shouldn't throw an exception");
        } catch(WithdrawAmountException ex) {
            fail("Shouldn't throw an exception");
        }
    }
    
    @Test
    public void withdrawTransferNotEnoughTest() {
        Client c1 = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "000", "male");
        Client c2 = new Client("Mohamed", 200.0, new Date(17, 8, 1997), "000", "male");
        
        //Trying Withdraw and transfering more than the balance
        try {
            c1.withdraw(200.0);
            assertEquals(0.0, c1.getBalance(), 0.000001);
        } catch(NegativeMoneyException ex) {
            fail("Shouldn't throw NegativeMOneyException");
        } catch(WithdrawAmountException ex) {
            fail("Shouldn't throw WithdrawAmountException");
        }
        
        try {
            c1.transferTo(c2, 200);
            fail("Shouldn't continue since no enough money");
        } catch(NegativeMoneyException ex) {
            fail("Shouldn't throw an exception");
        } catch(WithdrawAmountException ex) {
            assertEquals(0, c1.getBalance(), 0.0000001);
            assertEquals(200.0, c2.getBalance(), 0.000001);
        }
    }
    
    @Test
    public void withdrawTransferEnoughTest() {
        Client c1 = new Client("Mahmoud", 400.0, new Date(17, 8, 1997), "000", "male");
        Client c2 = new Client("Mohamed", 200.0, new Date(17, 8, 1997), "000", "male");
        
        //Trying Withdraw and transfering more than the balance
        try {
            c1.withdraw(200.0);
            assertEquals(200.0, c1.getBalance(), 0.000001);
        } catch(NegativeMoneyException ex) {
            fail("Shouldn't throw NegativeMOneyException");
        } catch(WithdrawAmountException ex) {
            fail("Shouldn't throw WithdrawAmountException");
        }
        
        try {
            c1.transferTo(c2, 200);
            assertEquals(0, c1.getBalance(), 0.0000001);
            assertEquals(400.0, c2.getBalance(), 0.000001);
        } catch(NegativeMoneyException ex) {
            fail("Shouldn't throw an exception");
        } catch(WithdrawAmountException ex) {
            fail("Shouldn't throw an exception");
        }
    }
}

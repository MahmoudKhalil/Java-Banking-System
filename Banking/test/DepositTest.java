/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import banking.Client;
import banking.Date;
import banking.NegativeMoneyException;
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
public class DepositTest {
    private Client c;
    public DepositTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        c = new Client("Mahmoud", 1200.0, new Date(17, 8, 1997), "0000", "male");
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void invalidInputTest() {
        try {
            c.deposit(-100);
            fail("Shouldn't continue in deposit");
        } catch(NegativeMoneyException ex) {
            assertEquals(1200.0, c.getBalance(), 0.00000001);
        }
    }
    
    @Test
    public void validSmallInputTest() {
        try {
            c.deposit(200);
            assertEquals(1400.0, c.getBalance(), 0.000000001);
        } catch(NegativeMoneyException ex) {
            fail("deposit shouldn't throw an exception");
        }
        
        try {
            c.deposit(50);
            assertEquals(1450.0, c.getBalance(), 0.000000001);
        } catch(NegativeMoneyException ex) {
            fail("deposit shouldn't throw an exception");
        }
    }
    
    @Test
    public void validLargeInputTest() {
        double oldBalance = c.getBalance();
        try {
            c.deposit(Math.pow(2, 30));
            assertEquals(Math.pow(2, 30) + oldBalance, c.getBalance(), 0.0001);
            oldBalance = c.getBalance();
            c.deposit(Math.pow(2, 5));
            assertEquals(Math.pow(2, 5) + oldBalance, c.getBalance(), 0.0001);
        } catch(NegativeMoneyException ex) {
            fail("Deposit shouldn't throw an exception");
        }
    }
    
    @Test
    public void validAndInvalidInputTest() {
        try {
            c.deposit(100);
            c.deposit(-10);
            fail("deposit shouldn't continue");
        } catch(NegativeMoneyException ex) {
            assertEquals(1300.0, c.getBalance(), 0.000000001);
        }
    }
    
    @Test
    public void multipleDepositTest() {
        try {
            c.deposit(50);
            c.deposit(100);
            c.deposit(200);
            assertEquals(1550.0, c.getBalance(), 0.000000001);
        } catch(NegativeMoneyException ex) {
            fail("deposit shouldn't throw exceptions");
        }
    }
    
    @Test
    public void zeroInputTest() {
        try {
            c.deposit(0);
            assertEquals(1200.0, c.getBalance(), 0.00000001);
        } catch(NegativeMoneyException ex) {
            fail("Shouldn't throw NegativeMoneyException");
        }
    }
}

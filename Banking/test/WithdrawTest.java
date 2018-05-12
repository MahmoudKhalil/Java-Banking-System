/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import banking.Client;
import banking.Date;
import banking.NegativeMoneyException;
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
public class WithdrawTest {
    private Client c;
    
    public WithdrawTest() {
    }
    
    @Before
    public void setUp() {
        c = new Client("Mohamed", 1200.0, new Date(17, 8, 1997), "0000", "male");
    }
    
    @Test
    public void invalidInputTest() {
        try {
            c.withdraw(-100);
            fail("withdraw should throw an exception");
        } catch(NegativeMoneyException ex) {
            assertEquals(1200.0, c.getBalance(), 0.000001);
        } catch(WithdrawAmountException ex) {
            fail("Shouldn't throw withdrawAmountException");
        }
    }
    
    @Test
    public void validNotEnoughMoneyTest() {
        try {
            c.withdraw(2000.0);
            fail("withdraw should throw WithdrawAmountException");
        } catch(NegativeMoneyException ex) {
            fail("Withdraw shouldn't throw NegativeMoneyException");
        } catch(WithdrawAmountException ex) {
            assertEquals(1200.0, c.getBalance(), 0.000001);
        }
    }
    
    @Test
    public void validEnoughMoneyTest() {
        try {
            c.withdraw(100);
            assertEquals(1100.0, c.getBalance(), 0.000001);
            c.withdraw(100);
            c.withdraw(200);
            c.withdraw(300);
            assertEquals(500.0, c.getBalance(), 0.0000001);
        } catch(NegativeMoneyException ex) {
            fail("shouldn't throw NegativeMoneyException");
        } catch(WithdrawAmountException ex) {
            fail("Shouldn't throw WithdrawAmountException");
        }
    }
    
    @Test
    public void zeroInputTest() {
        try {
            c.withdraw(0);
            assertEquals(1200.0, c.getBalance(), 0.000001);
        } catch(NegativeMoneyException ex) {
            fail("shouldn't throw NegativeMoneyException");
        } catch(WithdrawAmountException ex) {
            fail("Shouldn't throw WithdrawAmountException");
        }
    }
}

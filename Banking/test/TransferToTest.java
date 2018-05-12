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
public class TransferToTest {
    private Client c1;
    private Client c2;
    
    public TransferToTest() {
    }
    
    @Before
    public void setUp() {
        c1 = new Client("Mahmoud", 1200.0, new Date(17, 8, 1997), "000", "male");
        c2 = new Client("Mohamed", 1200.0, new Date(17, 8, 1997), "000", "male");
    }
    
    @Test
    public void invalidInputTest() {
        try {
            c1.transferTo(c2, -100);
            fail("Should throw NegativeMoneyException");
        } catch(NegativeMoneyException ex) {
            assertEquals(1200.0, c1.getBalance(), 0.000001);
            assertEquals(1200.0, c2.getBalance(), 0.000001);
        } catch(WithdrawAmountException ex) {
            fail("Shouldn't throw WithdrawAmountException");
        }
    }
    
    @Test
    public void validNotEnoughMoneyTest() {
        try {
            c1.transferTo(c2, 5000);
            fail("Should throw WithdrawAmountException");
        } catch(NegativeMoneyException ex) {
            fail("Shouldn't throw NegativeMoneyException");
        } catch(WithdrawAmountException ex) {
            assertEquals(1200.0, c1.getBalance(), 0.000001);
            assertEquals(1200.0, c2.getBalance(), 0.000001);
        }
    }
    
    @Test
    public void validEnoughMoneyTest() {
        try {
            c1.transferTo(c2, 100);
            assertEquals(1100.0, c1.getBalance(), 0.000001);
            assertEquals(1300.0, c2.getBalance(), 0.000001);
            
            c2.transferTo(c1, 1000);
            assertEquals(2100.0, c1.getBalance(), 0.000001);
            assertEquals(300.0, c2.getBalance(), 0.000001);
        } catch(NegativeMoneyException ex) {
            fail("Shouldn't throw an exception");
        } catch(WithdrawAmountException ex) {
            fail("Shouldn't throw an exception");
        }
    }
    
    @Test
    public void validZeroTransferTest() {
        try {
            c1.transferTo(c2, 0);
            assertEquals(1200.0, c1.getBalance(), 0.000001);
            assertEquals(1200.0, c2.getBalance(), 0.000001);
            
            c2.transferTo(c1, 0);
            assertEquals(1200.0, c1.getBalance(), 0.000001);
            assertEquals(1200.0, c2.getBalance(), 0.000001);
        } catch(NegativeMoneyException ex) {
            fail("Shouldn't throw an exception");
        } catch(WithdrawAmountException ex) {
            fail("Shouldn't throw an exception");
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking;

/**
 *
 * @author mahmoud
 */
public class WithdrawAmountException extends Exception{
    public WithdrawAmountException() {
        super("Amount is more than balance");
    }
}

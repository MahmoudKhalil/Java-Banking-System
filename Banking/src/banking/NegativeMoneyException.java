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
public class NegativeMoneyException extends Exception {
    public NegativeMoneyException() {
        super("Can't process negative money");
    }
}

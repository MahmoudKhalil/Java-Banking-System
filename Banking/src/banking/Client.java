/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class Client {
    
    String username;
    double balance;
    Date dateOfBirth;
    String phoneNumber;
    String gender;
    
    public Client(String username, double balance, Date dateOfBirth, String phoneNumber, String gender)
    {
        this.username= username;
        this.balance = balance;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
       
    }
    
}

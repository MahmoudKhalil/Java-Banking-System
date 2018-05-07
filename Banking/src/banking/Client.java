/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking;

/**
 *
 * @author ASUS
 */
public class Client {
    
    String name;
    double balance;
    Date dateOfBirth;
    String phoneNumber;
    String gender;
    
    public Client(String name, double balance, Date dateOfBirth, String phoneNumber, String gender)
    {
        this.name= name;
        this.balance = balance;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
       
    }
    
    @Override
    public String toString(){
        return this.name;
    }
    
    public String Print(){
        return ("Name: "+this.name+"\n"+ "Balance: "+this.balance+"\n"+ "Date Of Birth: "+ this.dateOfBirth+"\n"+ "Phone Number: "+ this.phoneNumber+"\n" + "Gender: " +this.gender +"\n");
                
                
    }
}

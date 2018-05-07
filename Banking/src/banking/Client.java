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
    
    public String getName() {
        return name;
    }
    
    public double getBalance() {
        return balance;
    }
    
    public Date getDate() {
        return dateOfBirth;
    }
    
    public String getNumber() {
        return phoneNumber;
    }
    
    public String getGender() {
        return gender;
    }
    
    @Override
    public String toString(){
        return this.name;
    }
    
    public String Print(){
        return ("Name: "+this.name+"\n"+ "Balance: "+this.balance+"\n"+ "Date Of Birth: "+ this.dateOfBirth+"\n"+ "Phone Number: "+ this.phoneNumber+"\n" + "Gender: " +this.gender +"\n");
                
                
    }
    
    public void deposit(double amount) {
        if(amount > 0) {
            balance += amount;
        }
    }
    
    public void withdraw(double amount) throws WithdrawAmountException{
        if(amount > 0) {
            if(amount > balance) {
                throw new WithdrawAmountException();
            }
            
            balance -= amount;
        }
    }
    
    public static void transferTo(Client client1, Client client2, double amount) throws WithdrawAmountException{
        client1.withdraw(amount);
        client2.deposit(amount);
    }
}

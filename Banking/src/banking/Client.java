/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banking;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author ASUS
 */
public class Client implements Serializable{
    
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
    
    public void deposit(double amount) throws NegativeMoneyException {
        if(amount < 0) {
            throw new NegativeMoneyException();
        }
        
        balance += amount;
    }
    
    public void withdraw(double amount) throws WithdrawAmountException, NegativeMoneyException {
        if(amount < 0) {
            throw new NegativeMoneyException();
        }
        
        if(amount > balance) {
            throw new WithdrawAmountException();
        }

        balance -= amount;
    }
    
    public void transferTo(Client client2, double amount) throws NegativeMoneyException, WithdrawAmountException {
        if(amount < 0) {
            throw new NegativeMoneyException();
        }
        
        if(amount > this.balance) {
            throw new WithdrawAmountException();
        }
        
        this.balance -= amount;
        client2.balance += amount;
    }
    
    public void save(FileOutputStream fos, ObjectOutputStream oos) throws IOException {
        oos.writeObject(name);
        oos.writeDouble(balance);
        oos.writeObject(dateOfBirth);
        oos.writeObject(phoneNumber);
        oos.writeObject(gender);
    }
    
    public static Client load(FileInputStream fis, ObjectInputStream ois) throws IOException, ClassNotFoundException {
        String clientName = (String)ois.readObject();
        double clientBalance = ois.readDouble();
        Date clientDate = (Date)ois.readObject();
        String clientPhoneNumber = (String)ois.readObject();
        String clientGender = (String) ois.readObject();
        
        return new Client(clientName, clientBalance, clientDate, clientPhoneNumber, clientGender);
    }
}

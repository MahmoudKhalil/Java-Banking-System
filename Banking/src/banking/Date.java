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
public class Date {
    int day,month,year;
    public Date(int day, int month, int year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    @Override
    public String toString()
    {
        return this.day+"/"+this.month+"/"+this.year;
    }
}

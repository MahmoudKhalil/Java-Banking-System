/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import banking.Client;
import banking.Date;
import banking.MainWindow;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sikuli.script.Screen;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import static org.sikuli.script.ImagePath.find;
import org.sikuli.script.Key;
import org.sikuli.script.*;
import static org.sikuli.script.Do.popup;
import org.sikuli.script.Region.*;
/**
 *
 * @author Dead Lock
 */
public class GuiTest {
    public static Screen s;
    
            String[] user = {"Mohamed Mostafa Amin", "Mahmoud Ahmed Khalil","Aly Mohamed Aly","Yasin Ahmed Yasser","Wael Mohamed Ibrahim"};
           String[] telephone = {"01118402892","01114212319", "01115839284","01278429503","01039458602"};
           int[] day = {5,3,2,20,17};
           int[] month = {20,19, 7,5,3};
           int[] year = {1997,1994,1995,1992,1990};
           String[] balances = {"1700","15000" ,"50000" ,"25000", "10000"};
    public void GUIThread() {
         

    }
    
@BeforeClass
public static void setUpClass() {
            
         s = new Screen();
             
           
            MainWindow w = new MainWindow(0);
            w.setVisible(true);
       
        

}

    
    @Test
    public void adding5validClients()
    {
         try {   
            popup("Let's attempt to add 5 users first",2);
            for (int i = 0; i < 1; i++) {
                s.click("screenshots/addclientmain.png");

                s.type((user[i]) + Key.TAB + telephone[i] + Key.TAB + day[i]+ Key.TAB + month[i] + Key.TAB + year[i]);
                s.click("screenshots/malebutton.png");
                s.type(Key.TAB + balances[i]);
             
                s.click("screenshots/savebutton.png");
                  Assert.assertNotNull("Adding User Not successful", s.exists("screenshots/addingUserSuccessful.png"));
                  s.click("screenshots/ok.png");
                popup(5-(i+1)+" left",1);
            }
            
         
        }
         catch (Exception e) {
            e.printStackTrace();
        }
         
       
    }
    
    //@Test
    public void addingClientWithNegativeBalance()
    {
      
        try{
             popup("Adding Client with negative Balance",3);
         s.click("screenshots/addclientmain.png");

                s.type((user[3]) + Key.TAB + telephone[3] + Key.TAB + day[2]+ Key.TAB + month[2] + Key.TAB + year[3]);
                s.click("screenshots/malebutton.png");
                s.type(Key.TAB + -10000);
             
                s.click("screenshots/savebutton.png");
                s.click("screenshots/ok.png");
                          Assert.assertNull("Adding User Not successful", s.exists("screenshots/addingUserSuccessful.png"));
                       
                 
              
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void addingClientWithStringBalance()
    {
        try{
            popup("Adding client with String Balance",3);
         s.click("screenshots/addclientmain.png");

                s.type((user[3]) + Key.TAB + telephone[3] + Key.TAB + day[2]+ Key.TAB + month[2] + Key.TAB + year[3]);
                s.click("screenshots/malebutton.png");
                s.type(Key.TAB + "stringbalance");
                   Assert.assertNull("Adding User Not successful", s.exists("screenshots/addingUserSuccessful.png"));
                s.click("screenshots/savebutton.png");
               s.click("screenshots/cancel.png");
      Assert.assertNull("Adding User Not successful", s.exists("screenshots/addingUserSuccessful.png"));
                 
              
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    
   
    @Test 
    public void testWithdraw()
    {
          try {
             popup("Testing Withdraw",3);
            s.click("screenshots/withdrawmain.png");
            Thread.sleep(1000);
            
            s.type("3000");
            s.click("screenshots/withdraw2.png");
             Assert.assertNotNull("Withdraw not successful", s.exists("screenshots/withdrawSuccessful.png"));
                  s.click("screenshots/ok.png");
                   
                     
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
                 
    }
    
    @Test 
    public void testDeposit()
    {
          try {
                popup("Testing Deposit",3);
        
           
            s.click("screenshots/depositmain.png");
            
            s.type("2000"); 
            s.click("screenshots/deposit2.png");
            
            Assert.assertNotNull("Deposit not successful", s.exists("screenshots/depositMessageSuccessful.png"));
            s.click("screenshots/ok.png");
            
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
            
             
        
    }
    
    @Test 
    public void testTransfer()
    {
          try {
                popup("Testing Transfer",3);
        
           
            s.click("screenshots/transfer.png");
            
            s.click("screenshots/dropbox2.png");
            s.type("Mohamed");  
            s.type(Key.TAB);
            
            s.type("2000"); 
            s.click("screenshots/transfer2.png");
            
            Assert.assertNotNull("Transfer not successful", s.exists("screenshots/transferMessageSuccessful.png"));
            s.click("screenshots/ok.png");
              
          }
          catch(Exception e)
          {
              e.printStackTrace();
          }
            
        
    }
    
    @Test
    public void testSave()
    {    
        try{
            popup("Testing Save",3);
        s.click("screenshots/file.png");
        s.click("screenshots/save.png");
        Assert.assertNotNull("Save failed","screenshots/saveSuccessful");
    
        s.click("screenshots/ok.png");
         
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    @Test public void testLoad()
    {
          try{
              
        popup("Testing Load",3);
        s.click("screenshots/file.png");
        s.click("screenshots/load.png");
        Assert.assertNotNull("Load failed","screenshots/loadSuccessful");
     
        s.click("screenshots/ok.png");
          
        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }
 
}

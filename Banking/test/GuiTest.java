/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import banking.MainWindow;
import org.junit.Test;
import static org.junit.Assert.*;
import org.sikuli.script.Screen;
import org.junit.Assert;

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
            String[] user = {"Mahmoud Ahmed khalil", "Mohamed Mostafa Amin","Aly Mohamed Aly","Yasin Ahmed Yasser","Wael Mohamed Ibrahim"};
           String[] telephone = {"01118402892","01114212319", "01115839284","01278429503","01039458602"};
           int[] day = {5,3,2,20,17};
           int[] month = {20,19, 7,5,3};
           int[] year = {1997,1994,1995,1992,1990};
           String[] balances = {"1700","15000" ,"50000" ,"25000", "10000"};
    public void GUIThread() {

    }

    @Test   
    public void testGUI() {
        try {

            Screen s = new Screen();
            MainWindow w = new MainWindow(0);
            w.setVisible(true);
           
            popup("Beginning testing",2);
            popup("Let's attempt to add 5 users first",2);
            for (int i = 0; i < 5; i++) {
                s.click("screenshots/addclientmain.png");

                s.type((user[i]) + Key.TAB + telephone[i] + Key.TAB + day[i]+ Key.TAB + month[i] + Key.TAB + year[i]);
                s.click("screenshots/malebutton.png");
                s.type(Key.TAB + balances[i]);
             
                s.click("screenshots/savebutton.PNG");
                popup(5-(i+1)+" left",1);
            }
            
            
            /**Withdraw**
             ***********/
             popup("Testing Withdraw",2);
            s.click("screenshots/withdrawmain.png");
            Thread.sleep(2000);
            
            s.type("3000");
            s.click("screenshots/withdraw2.png");
            
              /**Deposit**
             ***********/
             popup("Testing Deposit",2);
        
           
            s.click("screenshots/depositmain.png");
            
            s.type("2000"); 
            s.click("screenshots/deposit2.png");
 
           
                    
                    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

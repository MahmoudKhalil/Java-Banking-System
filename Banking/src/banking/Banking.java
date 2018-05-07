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
public class Banking {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MainWindow main = new MainWindow(0);
        main.setVisible(true);
    }
    
    public static void EndSecondaryFrame(java.awt.Frame parent,java.awt.Frame child,int selectedIndex){
        child.dispose();
        MainWindow mw = new MainWindow(selectedIndex);
        mw.setLocation(parent.getLocation());
        parent.dispose();
        mw.setVisible(true);
        
      
    }
    
}

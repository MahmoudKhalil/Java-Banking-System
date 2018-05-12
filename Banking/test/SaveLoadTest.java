/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import banking.Client;
import banking.Date;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mahmoud
 */
public class SaveLoadTest {
    
    private Client c;
    
    public SaveLoadTest() {
    }
    
    @Test
    public void saveLoad() {
        Client c = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "50", "male");
        try {
            FileOutputStream fos = new FileOutputStream("Accounts.data", false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            c.save(fos, oos);
            assertTrue(true);
            
            FileInputStream fis = new FileInputStream("Accounts.data");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Client c2 = Client.load(fis, ois);
            assertNotNull(c);
            
            assertEquals(c2.getName(), c.getName());
            assertEquals(c2.getBalance(), c.getBalance(), 0.0000001);
            assertEquals(c2.getNumber(), c.getNumber());
            assertEquals(c2.getGender(), c.getGender());
        } catch(IOException ex) {
            ex.printStackTrace();
        } catch(ClassNotFoundException ex) {
            fail("saveLoadTest shouldn't throw a ClassNotFoundException");
        }
    }
}

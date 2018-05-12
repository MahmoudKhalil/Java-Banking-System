/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import banking.Client;
import banking.Date;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class SaveTest {
    
    private Client c;
    
    public SaveTest() {
    }
    
    @Before
    public void setUp() {
        c = new Client("Mahmoud", 1200.0, new Date(17, 8, 1997), "00", "male");
    }
    
    @Test
    public void saveTest() {
        Client c = new Client("Mahmoud", 200.0, new Date(17, 8, 1997), "0000", "male");
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream("Accounts.data", false);
            oos = new ObjectOutputStream(fos);
            c.save(fos, oos);
            oos.close();
            fos.close();
            assertTrue(true);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}

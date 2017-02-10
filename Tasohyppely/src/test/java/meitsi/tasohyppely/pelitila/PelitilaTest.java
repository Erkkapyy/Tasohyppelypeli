/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.pelitila;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import meitsi.tasohyppely.main.PeliPaneeli;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author pyykonee
 */
public class PelitilaTest {
    private PelitilaManager manager;
    private Robot rob;
    public PelitilaTest() {
    }
    
    @Before
    public void setUp() {
        JFrame ikkuna = new JFrame("Pelin nimi TBA");
        ikkuna.setContentPane(new PeliPaneeli());
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setResizable(false);
        ikkuna.pack();
        ikkuna.setVisible(true);
        manager = new PelitilaManager();
    }
    
    @Test
    public void tilaAlussaNolla() {
        assertEquals(manager.getTilaNyt(), 0);
    }
    
    @Test
    public void aloitaToimii() {
        try {
            rob.keyPress(KeyEvent.VK_ENTER);
            rob.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void lopetaToimii() {
        try {
            rob.keyPress(KeyEvent.VK_UP);
            rob.keyRelease(KeyEvent.VK_UP);
            rob.keyPress(KeyEvent.VK_ENTER);
            rob.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void asetuksetToimii() {
        try {
            rob.keyPress(KeyEvent.VK_DOWN);
            rob.keyRelease(KeyEvent.VK_DOWN);
            rob.keyPress(KeyEvent.VK_ENTER);
            rob.keyRelease(KeyEvent.VK_ENTER);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

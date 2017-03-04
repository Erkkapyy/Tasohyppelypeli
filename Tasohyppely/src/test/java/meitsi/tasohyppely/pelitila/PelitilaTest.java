/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.pelitila;

import java.awt.Graphics2D;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import meitsi.tasohyppely.entity.Pelaaja;
import meitsi.tasohyppely.main.PeliPaneeli;
import static meitsi.tasohyppely.main.PeliPaneeli.HEIGHT;
import static meitsi.tasohyppely.main.PeliPaneeli.WIDTH;
import meitsi.tasohyppely.tilemap.TileMap;
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
            assertEquals(1, manager.getTilaNyt());
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
    
    @Test
    public void voittoTilaEiErroreita() {
        manager.setTila(2);
    }
    
    @Test
    public void pelaaminenToimii() {
        try {
            rob.keyPress(KeyEvent.VK_ENTER);
            rob.keyRelease(KeyEvent.VK_ENTER);
            rob.delay(1000);
            rob.keyPress(KeyEvent.VK_RIGHT);
            rob.delay(1000);
            rob.keyPress(KeyEvent.VK_X);
            rob.delay(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void piirotEiErroreita() {
        BufferedImage kuva = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = (Graphics2D) kuva.getGraphics();
        manager.setTila(0);
        manager.update();
        manager.draw(g);
        manager.setTila(1);
        manager.update();
        manager.draw(g);
        manager.setTila(2);
        manager.update();
        manager.draw(g);
        
    }
    
}

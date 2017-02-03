/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.pelitila;

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
    public PelitilaTest() {
    }
    
    @Before
    public void setUp() {
        manager = new PelitilaManager();
    }
    
    @Test
    public void tilaAlussaNolla() {
        assertEquals(manager.getTilaNyt(), 0);
    }
    
}

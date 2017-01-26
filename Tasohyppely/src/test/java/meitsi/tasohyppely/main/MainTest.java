/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.main;

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
public class MainTest {
    
    
    
    @Before
    public void setUp() {
        
    }
    
    
    @Test
    public void PeliPaneelinKokoOikeaLuodessa() {
        PeliPaneeli paneeli = new PeliPaneeli();
        assertEquals("480x640", paneeli.getResoluutio());
    }
}

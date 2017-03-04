/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.entity;

import java.awt.Graphics2D;
import javax.swing.JFrame;
import meitsi.tasohyppely.main.PeliPaneeli;
import meitsi.tasohyppely.pelitila.PelitilaManager;
import meitsi.tasohyppely.pelitila.Taso1Tila;
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
public class MapObjectTest {

    MapObject object;
    PelitilaManager pm;
    Taso1Tila taso1;
    TileMap tileMap;
    Graphics2D g;

    @Before
    public void setUp() {
        tileMap = new TileMap(30);
        pm = new PelitilaManager();
        taso1 = new Taso1Tila(pm);
        taso1.init();
        object = new MapObject(taso1.getTileMap()) {
        };
    }

    @Test
    public void calculateCornersEiErroreita() {
        object.calculateCorners(0, 0);
    }

    @Test
    public void checkTileMapCollisionEiErroreita() {
        object.checkTileMapCollision();
    }
    
    @Test
    public void pelaajaUpdateEiErroreita() {
        Pelaaja pelaaja = new Pelaaja(tileMap);
        pelaaja.update();
    }
    
    @Test
    public void collisionToimii() {
        boolean osuvatko = false;
        Pelaaja pelaaja1 = new Pelaaja(tileMap);
        Pelaaja pelaaja2 = new Pelaaja(tileMap);
        pelaaja1.setPosition(0, 0);
        pelaaja2.setPosition(10, 0);
        pelaaja1.setRight(true);
        pelaaja2.setLeft(true);
        for(int i=0; i<7; i++) {
            pelaaja1.update();
            pelaaja2.update();
            if(pelaaja1.intersects(pelaaja2)) {
                osuvatko = true;
            }
        }
        assertEquals(true, osuvatko);
    }
    
    @Test
    public void liikkuminenToimii() {
        Pelaaja pelaaja = new Pelaaja(tileMap);
        pelaaja.setRight(true);
        pelaaja.update();
        pelaaja.update();
        pelaaja.update();
        pelaaja.update();
        assertEquals(3, pelaaja.getx());
    }
    
    @Test
    public void painovoimaToimii() {
        Pelaaja pelaaja = new Pelaaja(tileMap);
        double haluttuKorkeus = 200;
        double nopeus = 4.8;
        int actual = 0;
        pelaaja.setJumping(true);
        pelaaja.setPosition(200,200);
        for(int i=0; i<64; i++) {
            pelaaja.update();
            nopeus -= 0.15;
                if(nopeus<-4) {
                    nopeus = -4;
                }
                haluttuKorkeus -= nopeus;
            actual = pelaaja.gety();
            assertEquals(haluttuKorkeus, actual, 0.99);
        }
    }
}

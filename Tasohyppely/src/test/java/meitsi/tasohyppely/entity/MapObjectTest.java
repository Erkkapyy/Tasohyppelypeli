/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import meitsi.tasohyppely.entity.viholliset.*;
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
        pelaaja.setRight(false);
        pelaaja.update();
        pelaaja.update();
        pelaaja.update();
        pelaaja.update();
        pelaaja.setLeft(true);
        pelaaja.update();
        pelaaja.update();
        pelaaja.update();
        pelaaja.update();
        pelaaja.update();
        assertEquals(0, pelaaja.getx());
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
    
    @Test
    public void iskunottoMekaniikkaToimii() {
        Pelaaja pelaaja = new Pelaaja(tileMap);
        Vihu1 vihollinen = new Vihu1(tileMap);
        ArrayList<Vihollinen> vihut = new ArrayList();
        vihut.add(vihollinen);
        vihollinen.setPosition(3, 0);
        pelaaja.setRight(true);
        pelaaja.update();
        pelaaja.update();
        pelaaja.update();
        pelaaja.update();
        pelaaja.checkAttack(vihut);
        assertEquals(2, pelaaja.getHp());
        
    }
    
    @Test
    public void vihollisenIskeminenToimii() {
        Pelaaja pelaaja = new Pelaaja(tileMap);
        Vihu1 vihollinen = new Vihu1(tileMap);
        ArrayList<Vihollinen> vihut = new ArrayList();
        vihut.add(vihollinen);
        vihollinen.setPosition(1, 0);
        pelaaja.setRight(true);
        pelaaja.setPunching();
        pelaaja.checkAttack(vihut);
        assertEquals(true, vihollinen.isDead());
    }
    
    @Test
    public void mapistaPutoamisenHallintaToimii() {
        Pelaaja pelaaja = new Pelaaja(tileMap);
        pelaaja.setPosition(-41, 0);
        pelaaja.update();
        assertEquals(1, pelaaja.getLives());
        assertEquals(100, pelaaja.getx());
        assertEquals(100, pelaaja.gety());
    }
    
    @Test
    public void vihollisenLiikkuminenToimii() {
        Vihu1 vihollinen = new Vihu1(tileMap);
        for(int i =0; i<7; i++) {
        vihollinen.update();
        }
        assertEquals(2, vihollinen.getx());
    }
}

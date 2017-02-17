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
    
    
}

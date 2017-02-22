/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.pelitila;

import java.awt.*;
import java.awt.event.KeyEvent;
import meitsi.tasohyppely.tilemap.*;
import meitsi.tasohyppely.entity.*;
import meitsi.tasohyppely.main.PeliPaneeli;

/**
 * Pelin ensimmäinen pelattava taso.
 */
public class Taso1Tila extends Pelitila {

    private TileMap tileMap;
    private Tausta tausta;
    private Pelaaja pelaaja;

    /**
     * Konstruktori.
     * @param pm PelitilaManager johon taso kuuluu
     */
    public Taso1Tila(PelitilaManager pm) {
        this.pm = pm;
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset2.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0, 0);
        tileMap.setTween(1);
        tausta = new Tausta("/Backgrounds/waterfallbg1.gif", 0.1);
        pelaaja = new Pelaaja(tileMap);
        pelaaja.setPosition(100, 100);
    }

    @Override
    public void update() {
        pelaaja.update();
        tileMap.setPosition(PeliPaneeli.WIDTH / 2 - pelaaja.getx(), PeliPaneeli.HEIGHT / 2 - pelaaja.gety());
    }

    @Override
    public void draw(Graphics2D g) {
        tausta.draw(g);
        tileMap.draw(g);
        pelaaja.draw(g);
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_LEFT) {
            pelaaja.setLeft(true);
        }
        if (k == KeyEvent.VK_RIGHT) {
            pelaaja.setRight(true);
        }
        if (k == KeyEvent.VK_UP) {
            pelaaja.setUp(true);
        }
        if (k == KeyEvent.VK_DOWN) {
            pelaaja.setDown(true);
        }
        if (k == KeyEvent.VK_X) {
            pelaaja.setJumping(true);
        }
        if (k == KeyEvent.VK_Z) {
            pelaaja.setPunching();
        }
    }

    @Override
    public void keyReleased(int k) {
        if (k == KeyEvent.VK_LEFT) {
            pelaaja.setLeft(false);
        }
        if (k == KeyEvent.VK_RIGHT) {
            pelaaja.setRight(false);
        }
        if (k == KeyEvent.VK_UP) {
            pelaaja.setUp(false);
        }
        if (k == KeyEvent.VK_DOWN) {
            pelaaja.setDown(false);
        }
        if (k == KeyEvent.VK_X) {
            pelaaja.setJumping(false);
        }

    }

    public TileMap getTileMap() {
        return tileMap;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.pelitila;

import java.awt.*;
import meitsi.tasohyppely.tilemap.*;
import meitsi.tasohyppely.entity.*;

/**
 * Pelin ensimmäinen pelattava taso.
 */
public class Taso1Tila extends Pelitila {

    private TileMap tileMap;
    private Tausta tausta;

    public Taso1Tila(PelitilaManager pm) {
        this.pm = pm;
        init();
    }

    @Override
    public void init() {
        tileMap = new TileMap(30);
        tileMap.loadTiles("/Tilesets/grasstileset.gif");
        tileMap.loadMap("/Maps/level1-1.map");
        tileMap.setPosition(0, 0);
        tausta = new Tausta("/Backgrounds/grassbg1.gif", 0.1);
    }

    @Override
    public void update() {

    }

    @Override
    public void draw(Graphics2D g) {
        tausta.draw(g);
        tileMap.draw(g);
    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

    public TileMap getTileMap() {
        return tileMap;
    }

}

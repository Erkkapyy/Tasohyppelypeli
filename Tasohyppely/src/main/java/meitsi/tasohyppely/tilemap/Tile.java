/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.tilemap;

import java.awt.image.BufferedImage;

/**
 *
 * @author pyykonee
 */
public class Tile {
    
    private BufferedImage kuva;
    private int type;
    
    public static final int NORMAL = 0;
    public static final int BLOCKED = 1;
    
    public Tile(BufferedImage kuva, int type) {
        this.kuva = kuva;
        this.type = type;
    }
    
    public BufferedImage getImage() {
        return kuva;
    }
    
    public int getType() {
        return type;
    }
}

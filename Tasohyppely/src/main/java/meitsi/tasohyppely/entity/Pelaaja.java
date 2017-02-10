/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.entity;

import meitsi.tasohyppely.tilemap.*;
import java.util.ArrayList;
import java.awt.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Pelaaja extends MapObject{
    private int hp;
    private int maxhp;
    private boolean dead;
    private boolean flinching;
    private long flinchTime;
    private boolean punching;
    private int punchDamage;
    private int punchRange;
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {
      2, 8, 1, 2, 5  
    };
    private static int IDLE = 0;
    private static int WALKING = 1;
    private static int JUMPING = 2;
    private static int FALLING = 3;
    private static int PUNCHING = 4;
    
    public Pelaaja(TileMap tm) {
        super(tm);
    }
}

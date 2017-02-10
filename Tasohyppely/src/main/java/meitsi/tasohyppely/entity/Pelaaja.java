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

public class Pelaaja extends MapObject {

    private int hp;
    private int maxhp;
    private boolean dead;
    private boolean isFlinching;
    private long flinchTime;
    private boolean isPunching;
    private int punchDamage;
    private int punchRange;
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {
        2, 8, 1, 2, 5
    };
    private static int idle = 0;
    private static int walking = 1;
    private static int jumping = 2;
    private static int falling = 3;
    private static int punching = 4;

    public Pelaaja(TileMap tm) {
        super(tm);
    }
}

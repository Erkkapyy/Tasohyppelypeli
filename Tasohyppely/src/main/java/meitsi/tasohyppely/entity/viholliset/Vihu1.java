/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.entity.viholliset;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import meitsi.tasohyppely.entity.*;
import meitsi.tasohyppely.tilemap.TileMap;

public class Vihu1 extends Vihollinen {

    private BufferedImage[] sprites;

    /**
     * Konstruktori.
     * @param tm tilemap johon vihollinen asetetaan.
     */
    public Vihu1(TileMap tm) {
        super(tm);
        moveSpeed = 0.3;
        maxSpeed = 0.3;
        fallSpeed = 0.2;
        maxFallSpeed = 10.0;
        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;
        health = maxHealth = 2;
        damage = 1;

        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Enemies/slugger.gif"));
            sprites = new BufferedImage[3];
            for (int i = 0; i < sprites.length; i++) {
                sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        animation = new Animation();
        animation.setFrames(sprites);
        animation.setDelay(300);
        right = true;
        facingRight = true;
    }

    /**
     * Asettaa muuttujat valmiiksi seuraavaa liikettä varten.
     */
    private void getNextPosition() {
        if (left) {
            dx -= moveSpeed;
            if (dx < -maxSpeed) {
                dx = -maxSpeed;
            }
        } else if (right) {
            dx += moveSpeed;
            if (dx > maxSpeed) {
                dx = maxSpeed;
            }
        } else {
            if (dx > 0) {
                dx -= stopSpeed;
                if (dx < 0) {
                    dx = 0;
                }
            } else if (dx < 0) {
                dx += stopSpeed;
                if (dx > 0) {
                    dx = 0;
                }
            }
        }
        if (isJumping && !isFalling) {
            dy = jumpStart;
            isFalling = true;
        }
        if (isFalling) {
            dy += fallSpeed;
            if (dy > 0) {
                isJumping = false;
            }
            if (dy < 0 && !isJumping) {
                dy += stopJumpSpeed;
            }
            if (dy > maxFallSpeed) {
                dy = maxFallSpeed;
            }
        }
    }

    /**
     * Päivittää vihollisen sijainnin ja tarkistaa flinchauksen.
     */
    @Override
    public void update() {
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
        if (flinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed > 400) {
                flinching = false;
            }
        }

        if (right && dx == 0) {
            right = false;
            left = true;
            facingRight = false;
        } else if (left && dx == 0) {
            right = true;
            left = false;
            facingRight = true;
        }
        animation.update();
    }

    /**
     * Piirtää vihollisen.
     *
     * @param g piirtomuuttuja
     */
    public void draw(java.awt.Graphics2D g) {
        setMapPosition();
        super.draw(g);
    }

}

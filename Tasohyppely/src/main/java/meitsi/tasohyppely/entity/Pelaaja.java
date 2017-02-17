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

/**
 * Luokka pelaajan liikutettavaa objektia varten.
 */
public class Pelaaja extends MapObject {

    private int hp;
    private int maxHp;
    private boolean dead;
    private boolean isFlinching;
    private long flinchTimer;
    private boolean isPunching;
    private int punchDamage;
    private int punchRange;
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = {
        2, 8, 1, 2, 4
    };
    private static int idle = 0;
    private static int walking = 1;
    private static int jumping = 2;
    private static int falling = 3;
    private static int punching = 4;

    /**
     * Luokan konstruktori.
     *
     * @param tm Parametrinä saatu TileMap
     */
    public Pelaaja(TileMap tm) {
        super(tm);

        width = 30;
        height = 30;
        cwidth = 20;
        cheight = 20;
        moveSpeed = 0.3;
        maxSpeed = 1.6;
        stopSpeed = 0.4;
        fallSpeed = 0.15;
        maxFallSpeed = 4.0;
        jumpStart = -4.8;
        stopJumpSpeed = 0.3;
        facingRight = true;
        hp = maxHp = 5;
        punchDamage = 8;
        punchRange = 40;
        try {
            BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/Sprites/Player/playersprites.gif"));
            sprites = new ArrayList<BufferedImage[]>();
            for (int i = 0; i < 5; i++) {
                BufferedImage[] bi = new BufferedImage[numFrames[i]];
                for (int j = 0; j < numFrames[i]; j++) {
                    if (i != 4) {
                        bi[j] = spritesheet.getSubimage(j * width, i * height, width, height);
                    } else {
                        bi[j] = spritesheet.getSubimage(j * width * 2, i * height, width * 2, height);
                    }
                }
                sprites.add(bi);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        animation = new Animation();
        currentAction = idle;
        animation.setFrames(sprites.get(idle));
        animation.setDelay(400);
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public void setPunching() {
        isPunching = true;
    }

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
     * Päivittää pelaajan.
     */
    public void update() {
        getNextPosition();
        checkTileMapCollision();
        setPosition(xtemp, ytemp);
        if (currentAction == punching) {
            if (animation.hasPlayedOnce()) {
                isPunching = false;
            }
        }
        if (isPunching) {
            if (currentAction != punching) {
                currentAction = punching;
                animation.setFrames(sprites.get(punching));
                animation.setDelay(50);
                width = 60;
            }
        } else if (dy > 0) {
            if (currentAction != falling) {
                currentAction = falling;
                animation.setFrames(sprites.get(falling));
                animation.setDelay(100);
                width = 30;
            }
        } else if (dy < 0) {
            if (currentAction != jumping) {
                currentAction = jumping;
                animation.setFrames(sprites.get(jumping));
                animation.setDelay(-1);
                width = 30;
            }
        } else if (left || right) {
            if (currentAction != walking) {
                currentAction = walking;
                animation.setFrames(sprites.get(walking));
                animation.setDelay(40);
                width = 30;
            }
        } else {
            if (currentAction != idle) {
                currentAction = idle;
                animation.setFrames(sprites.get(idle));
                animation.setDelay(400);
                width = 30;
            }
        }
        animation.update();
        if (currentAction != punching) {
            if (right) {
                facingRight = true;
            }
            if (left) {
                facingRight = false;
            }
        }
    }

    /**
     * Piirtää pelaajan.
     * @param g piirtomuuttuja
     */
    public void draw(Graphics2D g) {
        setMapPosition();
        if (isFlinching) {
            long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
            if (elapsed / 100 % 2 == 0) {
                return;
            }
        }
        if (facingRight) {
            g.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);
        } else {
            g.drawImage(animation.getImage(), (int) (x + xmap - width / 2 + width), (int) (y + ymap - height / 2), -width, height, null);
        }
    }
}

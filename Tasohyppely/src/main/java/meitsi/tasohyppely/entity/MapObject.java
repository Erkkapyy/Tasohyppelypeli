/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.entity;

import java.awt.*;
import meitsi.tasohyppely.main.PeliPaneeli;
import meitsi.tasohyppely.tilemap.*;

/**
 *
 * Abstrakti luokka objekteille, jotka esiintyvät pelissä ja jotka piirretään
 * näytölle.
 */
public abstract class MapObject {

    protected TileMap tileMap;
    protected int tileSize;
    protected double xmap;
    protected double ymap;
    protected double x;
    protected double y;
    protected double dx;
    protected double dy;
    protected int width;
    protected int height;
    protected int cwidth;
    protected int cheight;
    protected int currRow;
    protected int currCol;
    protected double xdest;
    protected double ydest;
    protected double xtemp;
    protected double ytemp;
    protected boolean topLeft;
    protected boolean topRight;
    protected boolean bottomLeft;
    protected boolean bottomRight;
    protected Animation animation;
    protected int currentAction;
    protected int previousAction;
    protected boolean facingRight;
    protected boolean left;
    protected boolean right;
    protected boolean up;
    protected boolean down;
    protected boolean isJumping;
    protected boolean isFalling;
    protected double moveSpeed;
    protected double maxSpeed;
    protected double stopSpeed;
    protected double fallSpeed;
    protected double maxFallSpeed;
    protected double jumpStart;
    protected double stopJumpSpeed;

    /**
     * Konstruktori.
     * @param tm TileMap johon objekti viittaa
     */
    public MapObject(TileMap tm) {
        tileMap = tm;
        tileSize = tm.getTileSize();
    }
    /**
     * Kertoo leikkaavatko annettun MapObjectin neliö ja toinen neliö.
     * @param o annettu MapObject
     * @return palauttaa booleanin siitä
     */
    public boolean intersects(MapObject o) {
        Rectangle r1 = getRectangle();
        Rectangle r2 = o.getRectangle();
        return r1.intersects(r2);
    }

    /**
     * Antaa MapObjectin koon neliönä.
     * @return palauttaa neliön
     */
    public Rectangle getRectangle() {
        return new Rectangle((int) x - cwidth, (int) y - cheight, cwidth, cheight);
    }

    /**
     * Laskee MapObjectin neljä eri kulmaa, jotta sen törmääminen voidaan laskea järkevästi.
     * @param x annettu x koordinaatti
     * @param y annettu y koordinaatti
     */
    public void calculateCorners(double x, double y) {
        int leftTile = (int) (x - cwidth / 2) / tileSize;
        int rightTile = (int) (x + cwidth / 2 - 1) / tileSize;
        int topTile = (int) (y - cheight / 2) / tileSize;
        int bottomTile = (int) (y + cheight / 2 - 1) / tileSize;
        if (topTile < 0 || bottomTile >= tileMap.getNumRows() || leftTile < 0 || rightTile >= tileMap.getNumCols()) {
            topLeft = topRight = bottomLeft = bottomRight = false;
            return;
        }
        int tl = tileMap.getType(topTile, leftTile);
        int tr = tileMap.getType(topTile, rightTile);
        int bl = tileMap.getType(bottomTile, leftTile);
        int br = tileMap.getType(bottomTile, rightTile);
        topLeft = tl == Tile.BLOCKED;
        topRight = tr == Tile.BLOCKED;
        bottomLeft = bl == Tile.BLOCKED;
        bottomRight = br == Tile.BLOCKED;

    }

    /**
     * Tarkistaa törmääkö objekti mihinkään.
     */
    public void checkTileMapCollision() {
        currCol = (int) x / tileSize;
        currRow = (int) y / tileSize;
        xdest = x + dx;
        ydest = y + dy;

        xtemp = x;
        ytemp = y;

        calculateCorners(x, ydest);
        if (dy < 0) {
            if (topLeft || topRight) {
                dy = 0;
                ytemp = currRow * tileSize + cheight / 2;
            } else {
                ytemp += dy;
            }
        }
        if (dy > 0) {
            if (bottomLeft || bottomRight) {
                dy = 0;
                isFalling = false;
                ytemp = (currRow + 1) * tileSize - cheight / 2;
            } else {
                ytemp += dy;
            }
        }

        calculateCorners(xdest, y);
        if (dx < 0) {
            if (topLeft || bottomLeft) {
                dx = 0;
                xtemp = currCol * tileSize + cwidth / 2;
            } else {
                xtemp += dx;
            }
        }
        if (dx > 0) {
            if (topRight || bottomRight) {
                dx = 0;
                xtemp = (currCol + 1) * tileSize - cwidth / 2;
            } else {
                xtemp += dx;
            }
        }

        if (!isFalling) {
            calculateCorners(x, ydest + 1);
            if (!bottomLeft && !bottomRight) {
                isFalling = true;
            }
        }

    }

    public int getx() {
        return (int) x;
    }

    public int gety() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getCWidth() {
        return cwidth;
    }

    public int getCHeight() {
        return cheight;
    }

    public void setPosition(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void setMapPosition() {
        xmap = tileMap.getx();
        ymap = tileMap.gety();
    }

    public void setLeft(boolean b) {
        left = b;
    }

    public void setRight(boolean b) {
        right = b;
    }

    public void setUp(boolean b) {
        up = b;
    }

    public void setDown(boolean b) {
        down = b;
    }

    public void setJumping(boolean b) {
        isJumping = b;
    }

    /**
     * Kertoo onko MapObject ruudulla.
     * @return palauttaa booleanin siitä
     */
    public boolean notOnScreen() {
        return x + xmap + width < 0 || x + xmap - width > PeliPaneeli.WIDTH || y + ymap + height < 0 || y + ymap - height > PeliPaneeli.HEIGHT;
    }
}

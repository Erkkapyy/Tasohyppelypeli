/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.tilemap;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;
import meitsi.tasohyppely.main.PeliPaneeli;

/**
 * Luokka joka lataa tason ja halutut objektit, ja vastaa niiden piirtämisestä.
 */
public class TileMap {

    private double x;
    private double y;
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;
    private double tween;
    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;
    private BufferedImage tileset;
    private int numTilesAcross;
    private Tile[][] tiles;
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    /**
    * Konstruktori.
    * @param tileSize määrittää tiilien koon
    */
    public TileMap(int tileSize) {
        this.tileSize = tileSize;
        numRowsToDraw = PeliPaneeli.HEIGHT + 480 / tileSize + 2;
        numColsToDraw = PeliPaneeli.WIDTH + 960 / tileSize + 2;
        tween = 0.07;
    }

    /**
    * Lataa parametrinä annetusta tiedostosta tilet.
    * @param s tiedostopolku
    */
    public void loadTiles(String s) {

        try {
            tileset = ImageIO.read(getClass().getResourceAsStream(s));
            numTilesAcross = tileset.getWidth() / tileSize;
            tiles = new Tile[2][numTilesAcross];
            BufferedImage subimage;
            for (int col = 0; col < numTilesAcross; col++) {
                subimage = tileset.getSubimage(col * tileSize, 0, tileSize, tileSize);
                tiles[0][col] = new Tile(subimage, Tile.NORMAL);
                subimage = tileset.getSubimage(col * tileSize, tileSize, tileSize, tileSize);
                tiles[1][col] = new Tile(subimage, Tile.BLOCKED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
    * Lataa parametrinä annetusta tiedostosta tason.
    * @param s tiedostopolku
    */
    public void loadMap(String s) {

        try {

            InputStream in = getClass().getResourceAsStream(s);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            numCols = Integer.parseInt(br.readLine());
            numRows = Integer.parseInt(br.readLine());
            map = new int[numRows][numCols];
            width = numCols * tileSize;
            height = numRows * tileSize;
            xmin = PeliPaneeli.WIDTH - width;
            xmax = 0;
            ymin = PeliPaneeli.HEIGHT - height;
            ymax = 0;
            String delims = "\\s+";
            for (int row = 0; row < numRows; row++) {
                String line = br.readLine();
                String[] tokens = line.split(delims);
                for (int col = 0; col < numCols; col++) {
                    map[row][col] = Integer.parseInt(tokens[col]);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTileSize() {
        return tileSize;
    }

    public double getx() {
        return x;
    }

    public double gety() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getType(int row, int col) {
        int rc = map[row][col];
        int r = rc / numTilesAcross;
        int c = rc % numTilesAcross;
        return tiles[r][c].getType();
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void setPosition(double x, double y) {

        this.x += (x - this.x) * tween;
        this.y += (y - this.y) * tween;

        fixBounds();
        colOffset = (int) -this.x / tileSize;
        colOffset = (int) -this.y / tileSize;
    }

    /**
    *  Varmistaa ettei mennä piirrettävän alueen ulkopuolelle.
    */
    private void fixBounds() {
        if (x < xmin) {
            x = xmin;
        }
        if (y < ymin) {
            y = ymin;
        }
        if (x > xmax) {
            x = xmax;
        }
        if (y > ymax) {
            y = ymax;
        }
    }

    /**
    * Piirtää tilet. 
    * @param g piirtomuuttuja
    */
    public void draw(Graphics2D g) {
        for (int row = rowOffset; row < rowOffset + numRowsToDraw; row++) {
            if (row >= numRows) {
                break;
            }
            for (int col = colOffset; col < colOffset + numColsToDraw; col++) {
                if (col >= numCols) {
                    break;
                }
                if (map[row][col] == 0) {
                    continue;
                }
                int rc = map[row][col];
                int r = rc / numTilesAcross;
                int c = rc % numTilesAcross;
                g.drawImage(tiles[r][c].getImage(), (int) x + col * tileSize, (int) y + row * tileSize, null);
            }
        }
    }

    public void setTween(double d) {
        tween = d;
    }

}

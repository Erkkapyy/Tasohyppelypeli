/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.tilemap;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.*;
import meitsi.tasohyppely.main.PeliPaneeli;

/**
 *
 * @author pyykonee
 */
public class Tausta {

    private BufferedImage kuva;

    private double x;
    private double y;
    private double dx;
    private double dy;

    private double moveScale;

    public Tausta(String s, double ms) {
        try {
            kuva = ImageIO.read(
                    getClass().getResourceAsStream(s)
            );
            moveScale = ms;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPosition(double x, double y) {
        this.x = (x * moveScale) % PeliPaneeli.WIDTH;
        this.y = (y * moveScale) % PeliPaneeli.HEIGHT;
    }

    public void setVector(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public void update() {
        this.x = (x * moveScale) % PeliPaneeli.WIDTH;
        this.y = (y * moveScale) % PeliPaneeli.HEIGHT;
        x += dx;
        y += dy;
    }

    public void draw(Graphics2D g) {
        g.drawImage(kuva, (int) x, (int) y, null);
        if (x < 0) {
            g.drawImage(kuva, (int) x + PeliPaneeli.WIDTH, (int) y, null);
        }
        if (x > 0) {
            g.drawImage(kuva, (int) x - PeliPaneeli.WIDTH, (int) y, null);
        }
    }
}

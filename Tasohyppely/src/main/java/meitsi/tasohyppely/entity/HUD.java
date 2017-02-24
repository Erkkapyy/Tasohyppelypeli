/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class HUD {

    private Pelaaja pelaaja;
    private BufferedImage heart;
    private BufferedImage life;

    /**
    * Konstruktori.
    * @param p pelaaja jonka hp/lives muuttujia tarkastellaan
    */
    public HUD(Pelaaja p) {
        pelaaja = p;
        try {
            BufferedImage kuva = ImageIO.read(getClass().getResourceAsStream("/HUD/Hud.gif"));
            heart = kuva.getSubimage(0, 0, 13, 12);
            life = kuva.getSubimage(0, 12, 12, 11);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Piirtää hudin.
     *
     * @param g piirtomuuttuja
     */
    public void draw(Graphics2D g) {
        for (int i = 0; i < pelaaja.getHp(); i++) {
            g.drawImage(heart, 5 + i * 15, 5, null);
        }
        for (int i = 0; i < pelaaja.getLives(); i++) {
            g.drawImage(life, 5 + i * 15, 20, null);
        }

    }
}

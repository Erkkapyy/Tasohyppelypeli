/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.pelitila;

import java.awt.*;
import meitsi.tasohyppely.tilemap.Tausta;


public class VoittoTila extends Pelitila {

    private Tausta tausta;
    private Color titleColor;
    private Font titleFont;

    /**
     * Konstruktori.
     * @param pm parametrina saatu PelitilaManager
     */
    public VoittoTila(PelitilaManager pm) {
        this.pm = pm;

        try {
            tausta = new Tausta("/Backgrounds/waterfallbg2.gif", 1);
            titleColor = new Color(65, 0, 135);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
    }

    @Override
    public void draw(Graphics2D g) {
        tausta.draw(g);
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Voitit Pelin", 80, 70);
    }

    @Override
    public void keyPressed(int k) {
    }

    @Override
    public void keyReleased(int k) {
    }

}

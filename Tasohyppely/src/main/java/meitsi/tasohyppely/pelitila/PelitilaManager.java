/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.pelitila;

import java.util.ArrayList;

/**
 * Eri pelitilojen hallintaa varten luotu luokka.
 */
public class PelitilaManager {

    private int tilaNyt;
    private ArrayList<Pelitila> pelitilat;

    public static final int MENUTILA = 0;
    public static final int TASO1TILA = 1;

    public PelitilaManager() {
        pelitilat = new ArrayList<Pelitila>();

        tilaNyt = MENUTILA;
        pelitilat.add(new MenuTila(this));
        pelitilat.add(new Taso1Tila(this));
    }

    public void setTila(int tila) {
        tilaNyt = tila;
        pelitilat.get(tilaNyt).init();
    }

    public void update() {
        pelitilat.get(tilaNyt).update();
    }

    public void draw(java.awt.Graphics2D g) {
        pelitilat.get(tilaNyt).draw(g);
    }

    public void keyPressed(int k) {
        pelitilat.get(tilaNyt).keyPressed(k);
    }

    public void keyReleased(int k) {
        pelitilat.get(tilaNyt).keyReleased(k);
    }

    public int getTilaNyt() {
        return this.tilaNyt;
    }
}

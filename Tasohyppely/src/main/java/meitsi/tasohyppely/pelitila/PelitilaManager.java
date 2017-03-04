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
    public static final int VOITTOTILA = 2;

    /**
     * Konstruktori.
     */
    public PelitilaManager() {
        pelitilat = new ArrayList<Pelitila>();

        tilaNyt = MENUTILA;
        pelitilat.add(new MenuTila(this));
        pelitilat.add(new Taso1Tila(this));
        pelitilat.add(new VoittoTila(this));
    }

    public void setTila(int tila) {
        tilaNyt = tila;
        pelitilat.get(tilaNyt).init();
    }

    /**
     * Kutsuu tämänhetkistä pelitilaa päivittämään itsensä.
     */
    public void update() {
        pelitilat.get(tilaNyt).update();
    }

    /**
     * Kutsuu tämänhetkistä pelitilaa piirtämään itsensä.
     * @param g piirtomuuttuja
     */
    public void draw(java.awt.Graphics2D g) {
        pelitilat.get(tilaNyt).draw(g);
    }

    /**
     * Kutsuu tämänhetkistä pelitilaa tarkistamaan mikä näppäin on painettu.
     * @param k keyPressed muuttuja
     */
    public void keyPressed(int k) {
        pelitilat.get(tilaNyt).keyPressed(k);
    }

    /**
     * Kutsuu tämänhetkistä pelitilaa tarkistamaan mikä näppäin on vapautettu.
     * @param k keyReleased muuttuja
     */
    public void keyReleased(int k) {
        pelitilat.get(tilaNyt).keyReleased(k);
    }

    public int getTilaNyt() {
        return this.tilaNyt;
    }
}

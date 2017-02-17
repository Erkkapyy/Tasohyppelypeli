/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.pelitila;

/**
 * Abstrakti luokka menua ja pelin eri tasoja varten.
 */
public abstract class Pelitila {

    protected PelitilaManager pm;
    
    /**
     * Käynnistää pelitilan.
     */
    public abstract void init();

    /**
     * Päivittää pelitilan.
     */
    public abstract void update();

    /**
     * Piirtää pelitilan.
     * @param g piirtomuuttuja
     */
    public abstract void draw(java.awt.Graphics2D g);

    /**
     * Kertoo mitä näppäintä on painettu.
     * @param k keyPressed muuttuja
     */
    public abstract void keyPressed(int k);

    /**
     * Kertoo mikä näppäin on vapautettu.
     * @param k keyReleased muuttuja
     */
    public abstract void keyReleased(int k);
}

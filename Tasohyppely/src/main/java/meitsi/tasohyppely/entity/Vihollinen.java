/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.entity;

import meitsi.tasohyppely.tilemap.TileMap;

/**
 * Luokka vihollisobjekteja varten.
 */
public class Vihollinen extends MapObject{
    
    protected int health;
    protected int maxHealth;
    protected boolean dead;
    protected int damage;
    protected boolean flinching;
    protected long flinchTimer;
    
    public Vihollinen(TileMap tm) {
        super(tm);
    }
    
    public boolean isDead() {
        return dead;
    }
    
    public int getDamage() {
        return damage;
    }
    
    public void hit(int damage) {
        if(dead||flinching) {
            return;
        }
        health -= damage;
        if(health<0) {
            health = 0;
        }
        if(health==0) {
            dead = true;
        }
        flinching = true;
        flinchTimer = System.nanoTime();
    }
    
    public void update() {
        
    }
    
}

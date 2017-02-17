/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package meitsi.tasohyppely.pelitila;

import java.awt.*;
import java.awt.event.KeyEvent;
import meitsi.tasohyppely.tilemap.Tausta;

/**
 * Pelin alkuvalikon määrittävä luokka.
 */
public class MenuTila extends Pelitila {

    private Tausta tausta;
    private int currentChoice = 0;
    private String[] options = {
        "Aloita", "Asetukset", "Lopeta"
    };

    private Color titleColor;
    private Font titleFont;
    private Font font;
    
    /**
     * Konstruktori.
     * @param pm PelitilaManager johon Menutila kuuluu
     */
    public MenuTila(PelitilaManager pm) {
        this.pm = pm;

        try {
            tausta = new Tausta("/Backgrounds/menubg.gif", 1);
            tausta.setVector(-0.1, 0);
            titleColor = new Color(128, 0, 0);
            titleFont = new Font("Century Gothic", Font.PLAIN, 28);
            font = new Font("Arial", Font.PLAIN, 12);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init() {
    }

    @Override
    public void update() {
        tausta.update();
    }

    @Override
    public void draw(Graphics2D g) {
        tausta.draw(g);
        g.setColor(titleColor);
        g.setFont(titleFont);
        g.drawString("Tasohyppely", 80, 70);
        g.setFont(font);
        for (int i = 0; i < options.length; i++) {
            if (i == currentChoice) {
                g.setColor(Color.CYAN);
            } else {
                g.setColor(Color.WHITE);
            }
            g.drawString(options[i], 145, 140 + i * 15);
        }
    }

    private void select() {
        if (currentChoice == 0) {
            pm.setTila(PelitilaManager.TASO1TILA);
        }
        if (currentChoice == 1) {
            System.out.println("Asetukset");
        }
        if (currentChoice == 2) {
            System.exit(0);
        }

    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_ENTER) {
            select();
        }
        if (k == KeyEvent.VK_UP) {
            currentChoice--;
            if (currentChoice == -1) {
                currentChoice = options.length - 1;
            }
        }
        if (k == KeyEvent.VK_DOWN) {
            currentChoice++;
            if (currentChoice == options.length) {
                currentChoice = 0;
            }
        }
    }

    @Override
    public void keyReleased(int k) {
    }
    
    public void setCurrentChoice(int choice) {
        currentChoice = choice;
    }
    


}

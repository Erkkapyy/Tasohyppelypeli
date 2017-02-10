package meitsi.tasohyppely.main;

import javax.swing.JFrame;
/**
 * Koko pelin main-luokka.
 */
public class Peli {
    
    public static void main(String[] args) {
        JFrame ikkuna = new JFrame("Pelin nimi TBA");
        ikkuna.setContentPane(new PeliPaneeli());
        ikkuna.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ikkuna.setResizable(false);
        ikkuna.pack();
        ikkuna.setVisible(true);
        
    }
    
}

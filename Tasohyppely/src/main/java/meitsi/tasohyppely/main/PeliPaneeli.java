
package meitsi.tasohyppely.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JPanel;

public class PeliPaneeli extends JPanel implements Runnable, KeyListener{

    public static final int HEIGHT = 480;
    public static final int WIDTH = 640;
    
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000/FPS;
    
    private BufferedImage kuva;
    private Graphics2D g;
    
    public PeliPaneeli() {
        super();
        setPreferredSize(new Dimension(WIDTH,HEIGHT));
        setFocusable(true);
        requestFocus();
    }
    
    @Override
    public void addNotify() {
        super.addNotify();
        if(thread==null) {
            thread = new Thread(this);
            addKeyListener(this);
        }
    }
    
    public void init() {
        kuva = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) g;
        running = true;
    }
    
    private void update() {
        
    }
    
    private void draw() {
        
    }
    
    private void drawToScreen() {
        
    }

    @Override
    public void run() {
        init();
        while(running) {
            update();
            draw();
            drawToScreen();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyReleased(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

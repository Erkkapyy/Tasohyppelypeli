package meitsi.tasohyppely.main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.event.*;
import javax.swing.JPanel;
import meitsi.tasohyppely.pelitila.PelitilaManager;

/**
 * Luokka joka luo peli-ikkunan, käynnistää threadin ja kutsuu PelitilaManageria piirtääkseen siihen(piirtokutsu lähetetään yhä edelleen TileMapille ja Taustalle).
 */
public class PeliPaneeli extends JPanel implements Runnable, KeyListener {

    public static final int HEIGHT = 240;
    public static final int WIDTH = 320;

    private Thread thread;
    private boolean running;
    private int fps = 60;
    private long targetTime = 1000 / fps;

    private BufferedImage kuva;
    private Graphics2D g;

    private PelitilaManager pm;

    public PeliPaneeli() {
        super();
        setPreferredSize(new Dimension(WIDTH + 960, HEIGHT + 480));
        setFocusable(true);
        requestFocus();
    }

    @Override
    public void addNotify() {
        super.addNotify();
        if (thread == null) {
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    private void init() {
        kuva = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        g = (Graphics2D) kuva.getGraphics();
        running = true;
        pm = new PelitilaManager();
    }

    public void run() {
        init();

        long start;
        long elapsed;
        long wait;

        while (running) {

            start = System.nanoTime();
            update();
            draw();
            drawToScreen();

            elapsed = System.nanoTime() - start;

            wait = targetTime - elapsed / 1000000;

            try {
                Thread.sleep(wait);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        pm.update();
    }

    private void draw() {
        pm.draw(g);
    }

    private void drawToScreen() {
        Graphics g2 = getGraphics();
        g2.drawImage(kuva, 0, 0, WIDTH + 960, HEIGHT + 480, null);
        g2.dispose();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        pm.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        pm.keyReleased(e.getKeyCode());
    }

    public String getResoluutio() {
        return HEIGHT + "x" + WIDTH;
    }

    public int getPelitilaManagerTila() {
        return this.pm.getTilaNyt();

    }
}

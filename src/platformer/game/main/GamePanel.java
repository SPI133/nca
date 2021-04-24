package platformer.game.main;

import platformer.game.state.GameStateManager;
import platformer.resources.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements Runnable, KeyListener {

    public final static int WIDTH = 900;
    public final static int HEIGHT = 550;

    private boolean isRunning;
    private long targetTime;
    private GameStateManager manager;

    GamePanel() {

        this.isRunning = false;
        int FPS = 60;
        this.targetTime = 1000 / FPS;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        addKeyListener(this);
        setFocusable(true);

        new Images();

        start();
    }

    private void start() {
        this.isRunning = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        long start, elapsed, wait;
        this.manager = new GameStateManager();

        while (this.isRunning) {
            start = System.nanoTime();
            tick();
            repaint();

            elapsed = System.nanoTime() - start;
            wait = this.targetTime - elapsed / 1000000;

            if (wait < 0) {
                wait = 5;
            }

            try {
                Thread.sleep(wait);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void tick() {
        this.manager.tick();
    }

    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.clearRect(0, 0, WIDTH, HEIGHT);
        this.manager.draw(g);
    }

    @Override
    public void keyTyped(final KeyEvent e) {

    }

    @Override
    public void keyPressed(final KeyEvent e) {
        this.manager.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(final KeyEvent e) {
        this.manager.keyReleased(e.getKeyCode());
    }
}

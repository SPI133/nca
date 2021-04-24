package platformer.game.state;

import platformer.game.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MenuState extends GameState {

    private String[] options;
    private int currentSelection;

    MenuState(final GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {
        this.options = new String[]{"ΑΥΤΑΜΑΡΕΣΟΥΝ", "ΟΥΟΧΙ"};
        this.currentSelection = 0;
    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(final Graphics g) {
        g.setColor(new Color(49, 16, 189));
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

        for (int i = 0; i < this.options.length; i++) {
            if (i == this.currentSelection) {
                g.setColor(Color.GREEN);
            } else {
                g.setColor(Color.BLACK);
            }

            g.setFont(new Font("Arial", Font.BOLD, 72));

            g.drawString(this.options[i],
                    GamePanel.WIDTH / 2 - 50 - options[i].length() * 20,
                    250 + i * 100);
        }
    }

    @Override
    public void keyPressed(final int k) {
        if (k == KeyEvent.VK_DOWN) {
            this.currentSelection++;
            if (this.currentSelection >= this.options.length) {
                this.currentSelection = 0;
            }
        } else if (k == KeyEvent.VK_UP) {
            this.currentSelection--;
            if (this.currentSelection < 0) {
                this.currentSelection = this.options.length - 1;
            }
        } else if (k == KeyEvent.VK_ENTER) {
            switch (this.currentSelection) {
                case 0:
                    this.manager.push(new LevelState(this.manager, GameState.FIRST_LEVEL));
                    break;
                case 1:
                    System.exit(0);
                    break;
            }
        }
    }

    @Override
    public void keyReleased(final int k) {

    }
}

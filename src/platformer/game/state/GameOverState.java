package platformer.game.state;

import platformer.game.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class GameOverState extends GameState{

    private final String DEATH_MSG = "F";

    GameOverState(final GameStateManager gsm) {
        super(gsm);
    }

    @Override
    public void init() {

    }

    @Override
    public void tick() {

    }

    @Override
    public void draw(final Graphics g) {
        g.setColor(new Color(5, 10, 10));
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 72));
        g.drawString(DEATH_MSG,GamePanel.WIDTH/4, GamePanel.HEIGHT/2);
    }

    @Override
    public void keyPressed(final int k) {
        if(k== KeyEvent.VK_ENTER){
            manager.pop();
        }
    }

    @Override
    public void keyReleased(final int k) {

    }
}

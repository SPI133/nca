package platformer.game.state;

import platformer.game.main.GamePanel;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LevelCompleteState extends GameState {

    private final String WIN_MSG = "ΑΥΤΑ ΕΠΙΘΥΜΩ";

    LevelCompleteState(GameStateManager gsm) {
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
        g.setColor(new Color(17, 51, 33));
        g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 72));
        g.drawString(WIN_MSG,GamePanel.WIDTH/20, GamePanel.HEIGHT/2);
    }

    @Override
    public void keyPressed(final int k) {
        if(k== KeyEvent.VK_ENTER){
            manager.pop();
        }
    }

    @Override
    public void keyReleased(int k) {

    }
}

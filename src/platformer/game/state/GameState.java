package platformer.game.state;

import java.awt.*;

public abstract class GameState {

    static final String FIRST_LEVEL = "/map1.map";

    private String[] levels;
    private int level;
    GameStateManager manager;
    public static double xOffset, yOffset;

    GameState(GameStateManager gsm) {
        this.manager = gsm;
        xOffset = 0;
        yOffset = 0;
        this.level = 0;
        this.levels = new String[]{FIRST_LEVEL};
        init();
    }

    public abstract void init();

    public abstract void tick();

    public abstract void draw(final Graphics g);

    public abstract void keyPressed(final int k);

    public abstract void keyReleased(final int k);

    public void gameOver() {
        manager.pop();
        manager.push(new GameOverState(this.manager));
    }

    public void levelComplete() {
        this.level++;
        this.manager.pop();
        if (this.level < this.levels.length) {
            String nextLevel = this.levels[level];
            this.manager.push(new LevelState(this.manager, nextLevel));
        } else {
            this.manager.push(new LevelCompleteState(this.manager));
        }
    }
}

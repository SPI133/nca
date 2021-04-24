package platformer.game.state;

import platformer.game.entities.Player;
import platformer.mapping.Map;

import java.awt.*;

public class LevelState extends GameState {

    private static final String MAPS_PATH = "res/Maps";

    private Player player;
    private Map map;
    private String level;

    LevelState(final GameStateManager gsm, final String level) {
        super(gsm);
        this.level = level;
        setMap();
    }

    private void setMap() {
        final String path = MAPS_PATH + level;
        this.map = new Map(path);
    }

    @Override
    public void init() {
        this.player = new Player(80, 80, this);
        xOffset = -240;
        yOffset = -400;
    }

    @Override
    public void tick() {
        this.player.tick(this.map.getBlocks(),
                this.map.getMovingBlocks(),
                this.map.getEnemies());
        this.map.tick();
    }

    @Override
    public void draw(final Graphics g) {
        this.map.draw(g);
        this.player.draw(g);
    }

    @Override
    public void keyPressed(final int k) {
        this.player.keyPressed(k);
    }

    @Override
    public void keyReleased(final int k) {
        this.player.keyReleased(k);
    }
}

package platformer.game.objects;

import platformer.game.state.GameState;
import platformer.resources.Images;

import java.awt.*;

public class Block extends Rectangle {

    public static final int SIZE = 110;

    private int id;

    public Block(final int x, final int y, final int id) {
        setBounds(x, y, SIZE, SIZE);
        this.id = id;
    }

    public void tick() {

    }

    public void draw(final Graphics g) {
        if (this.id != 0) {
            g.drawImage(
                    Images.getBlockImage(id - 1),
                    x - (int) GameState.xOffset,
                    y - (int) GameState.yOffset,
                    width, height, null);
        }
    }

    public int getId() {
        return this.id;
    }
}

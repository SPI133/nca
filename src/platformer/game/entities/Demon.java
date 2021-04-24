package platformer.game.entities;

import platformer.game.objects.Block;
import platformer.game.objects.MovingBlock;
import platformer.game.state.GameState;
import platformer.resources.Images;

import java.awt.*;
import java.util.ArrayList;

public class Demon extends WalkingEnemy {

    private static final double MAX_FALL_SPEED = 5;
    private static final double MOVE_SPEED = 1.5;

    private double currentFallSpeed;
    private boolean right, left, falling, topCollision;

    public Demon(int x, int y, int width, int height) {
        super(x*Block.SIZE, y*Block.SIZE, width, height);
        this.id = 1;
        this.move = 1;
        this.right = true;
        this.left = false;
        this.currentFallSpeed = 0.1;
        this.topCollision = false;
    }

    @Override
    public void tick(final Block[][] b,
                     final ArrayList<MovingBlock> mb,
                     final ArrayList<Enemy> e) {
        if(!getAlive()){
            return;
        }
        super.tick(b,mb,e);

        this.topCollision = false;

        if (this.right) {
            this.x += MOVE_SPEED;
        }

        if (this.left) {
            this.x -= MOVE_SPEED;
        }

        if (this.falling) {
            this.y += this.currentFallSpeed;

            if (this.currentFallSpeed < MAX_FALL_SPEED) {
                this.currentFallSpeed += 0.1;
            }
        }

        if (!this.falling) {
            this.currentFallSpeed = 0.1;
        }
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void setTopCollision(boolean topCollision) {
        this.topCollision = topCollision;
    }

    public boolean getTopCollision() {
        return this.topCollision;
    }

    @Override
    public void draw(Graphics g) {
        if (getId() != 0 && getAlive()) {
            g.drawImage(Images.getEnemyImage(
                    id - 1),
                    x - (int) GameState.xOffset,
                    y - (int) GameState.yOffset,
                    width, height, null);
        }
    }
}

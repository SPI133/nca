package platformer.game.entities;


import platformer.game.objects.Block;
import platformer.game.objects.DeathBlock;
import platformer.game.objects.MovingBlock;
import platformer.game.objects.VictoryBlock;
import platformer.game.physics.Collision;

import java.awt.*;
import java.util.ArrayList;

public abstract class Entity {

    int x, y;
    int width, height;

    public void tick(final Block[][] b,
                     final ArrayList<MovingBlock> mb,
                     final ArrayList<Enemy> e) {
        for (Block[] blocks : b) {
            for (Block block : blocks) {
                switch (block.getId()) {
                    case 1:
                        Collision.blockType1Collision(block, this);
                        break;
                    case 2:
                        Collision.deathBlockType1Collision(new DeathBlock(block), this);
                        break;
                    case 4:
                        Collision.blockType2Collision(new VictoryBlock(block), this);
                }
            }
        }

        for (MovingBlock block : mb) {
            Collision.movingBlockType1Collision(block, this);
        }

        for (Enemy enemy : e) {
            if (enemy.getAlive()) {
                Collision.enemyEntityCollision(enemy, this);
            }
        }
    }

    public abstract void draw(Graphics g);

    public int getX() {
        return this.x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}

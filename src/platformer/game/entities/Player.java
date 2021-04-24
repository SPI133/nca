package platformer.game.entities;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import platformer.game.main.GamePanel;
import platformer.game.objects.Block;
import platformer.game.objects.MovingBlock;
import platformer.game.state.GameState;
import platformer.resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Player extends Entity {

    private static final double MAX_JUMP_SPEED = 5;
    private static final double MAX_FALL_SPEED = 5;
    private static final double MOVE_SPEED = 2.5;

    private double currentJumpSpeed, currentFallSpeed;
    private boolean right, left, jumping, falling, topCollision;
    private GameState state;
    private MediaPlayer mediaPlayer;

    public Player(final int width, final int height, GameState gs) {
        this.right = false;
        this.left = false;
        this.x = GamePanel.WIDTH / 2;
        this.y = GamePanel.HEIGHT / 2;
        this.width = width;
        this.height = height;
        this.currentJumpSpeed = MAX_JUMP_SPEED;
        this.currentFallSpeed = 0.1;
        this.topCollision = false;
        this.state = gs;
    }

    @Override
    public void tick(final Block[][] b,
                     final ArrayList<MovingBlock> mb,
                     final ArrayList<Enemy> e) {
        super.tick(b, mb, e);

        this.topCollision = false;

        if (this.right) {
            GameState.xOffset += MOVE_SPEED;
        }

        if (this.left) {
            GameState.xOffset -= MOVE_SPEED;
        }

        if (this.jumping) {
            GameState.yOffset -= this.currentJumpSpeed;
            this.currentJumpSpeed -= 0.1;

            if (currentJumpSpeed <= 0) {
                this.currentJumpSpeed = MAX_JUMP_SPEED;
                this.jumping = false;
                this.falling = true;
            }
        }

        if (this.falling) {
            GameState.yOffset += this.currentFallSpeed;

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

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public void setTopCollision(boolean topCollision) {
        this.topCollision = topCollision;
    }

    public boolean getJumping() {
        return this.jumping;
    }

    public boolean getTopCollision() {
        return this.topCollision;
    }

    public void gameOver() {
        this.state.gameOver();
        //playSound("death.m4a");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void completeLevel() {
        this.state.levelComplete();
        //playSound("win.m4a");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void playSound(final String sound){
        try {
            URL url = this.getClass().getResource(sound);
            final Media media = new Media(url.toString());
            mediaPlayer = new MediaPlayer(media);
            mediaPlayer.play();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Images.getCake(),
                x, y, width, height, null);
    }

    public void keyPressed(final int k) {
        if (k == KeyEvent.VK_RIGHT) {
            this.right = true;
        } else if (k == KeyEvent.VK_LEFT) {
            this.left = true;
        } else if (k == KeyEvent.VK_UP && !this.jumping && !this.falling) {
            this.jumping = true;
        }
    }

    public void keyReleased(final int k) {
        if (k == KeyEvent.VK_RIGHT) {
            this.right = false;
        } else if (k == KeyEvent.VK_LEFT) {
            this.left = false;
        }
    }
}

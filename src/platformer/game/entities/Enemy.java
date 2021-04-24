package platformer.game.entities;

public abstract class Enemy extends Entity {
    int id;
    private boolean alive;

    Enemy(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.alive = true;
    }

    public void death() {
        this.alive = false;
    }

    boolean getAlive() {
        return this.alive;
    }

    public int getId() {
        return id;
    }
}

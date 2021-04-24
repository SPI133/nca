package platformer.game.entities;

abstract class WalkingEnemy extends Enemy{

    int move;

    WalkingEnemy(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
}

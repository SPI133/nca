package platformer.game.objects;

public class MovingBlock extends Block {

    //TODO:Write code for vertical bounds
    private int leftBound, rightBound, upBound, downBound;
    private int move;

    public MovingBlock(int x, int y, int id, int leftBound, int rightBound) {
        super(x, y, id);
        this.move = 1;
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    public void tick() {
        if (this.x + this.width >= this.rightBound && this.move > 0) {
            this.move *= -1;
        }

        if (this.x <= this.leftBound && this.move < 0) {
            this.move *= -1;
        }

        this.x += this.move;
    }

    public int getMove() {
        return this.move;
    }
}

package platformer.game.objects;

public class DeathBlock extends Block {

    public DeathBlock(Block block) {
        super((int) block.getX(), (int) block.getY(), block.getId());
    }
}

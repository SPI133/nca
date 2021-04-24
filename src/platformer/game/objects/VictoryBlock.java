package platformer.game.objects;

public class VictoryBlock extends Block{
    public VictoryBlock(Block block) {
        super((int) block.getX(), (int) block.getY(), block.getId());
    }
}

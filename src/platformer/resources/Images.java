package platformer.resources;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Images {

    private static final int BLOCKS = 4;
    private static final int ENEMIES = 1;
    private static final String BLOCKS_PATH = "res/Blocks";
    private static final String ENTITIES_PATH = "res/Entities";

    private static BufferedImage cake;
    private static BufferedImage[] blocks;
    private static BufferedImage[] enemies;

    public Images() {
        blocks = new BufferedImage[BLOCKS];
        enemies = new BufferedImage[ENEMIES];

        try {
            cake = ImageIO.read(new File(ENTITIES_PATH + "/nca.png"));

            blocks[0] = ImageIO.read(new File(BLOCKS_PATH + "/block.png"));
            blocks[1] = ImageIO.read(new File(BLOCKS_PATH + "/lava.png"));
            blocks[2] = ImageIO.read(new File(BLOCKS_PATH + "/lava2.png"));
            blocks[3] = ImageIO.read(new File(BLOCKS_PATH + "/finish.png"));

            enemies[0] = ImageIO.read(new File(ENTITIES_PATH + "/demon.png"));
            //enemies[0] = ImageIO.read(new File(ENTITIES_PATH + "/ghost.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Image getBlockImage(final int i) {
        return blocks[i];
    }

    public static Image getCake() {
        return cake;
    }

    public static Image getEnemyImage(final int i) {
        return enemies[i];
    }
}

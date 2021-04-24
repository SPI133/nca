package platformer.mapping;

import platformer.game.entities.Demon;
import platformer.game.entities.Enemy;
import platformer.game.main.GamePanel;
import platformer.game.objects.Block;
import platformer.game.objects.MovingBlock;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class Map {

    private final String path;

    private Block[][] blocks;
    private ArrayList<MovingBlock> movingBlocks;
    private ArrayList<Enemy> enemies;

    public Map(final String path) {
        this.path = path;
        loadMap();
    }

    private void loadMap() {
        final File file = new File(this.path);
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {
            if (br != null) {
                loadBlocks(br);
                loadMovingBlocks(br);
                loadEnemies(br);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadBlocks(BufferedReader br) throws IOException {
        int width = Integer.parseInt(br.readLine());
        int height = Integer.parseInt(br.readLine());
        this.blocks = new Block[width][height];

        String line;
        String[] tokens;

        for (int y = 0; y < height; y++) {
            line = br.readLine();
            tokens = line.split("\\s+");

            for (int x = 0; x < width; x++) {
                this.blocks[x][y] = new Block(
                        x * Block.SIZE,
                        y * Block.SIZE,
                        Integer.parseInt(tokens[x]));
            }
        }
    }

    private void loadMovingBlocks(BufferedReader br) throws IOException {
        br.readLine();
        String line;
        String[] tokens;
        int length;

        line = br.readLine();
        length = Integer.parseInt(line);
        this.movingBlocks = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            line = br.readLine();
            tokens = line.split("\\s+");

            MovingBlock movingBlock = new MovingBlock(
                    Integer.parseInt(tokens[0]) * Block.SIZE,
                    Integer.parseInt(tokens[1]) * Block.SIZE,
                    Integer.parseInt(tokens[2]),
                    Integer.parseInt(tokens[3]) * Block.SIZE,
                    Integer.parseInt(tokens[4]) * Block.SIZE);
            this.movingBlocks.add(movingBlock);
        }
    }

    private void loadEnemies(BufferedReader br) throws IOException {
        br.readLine();
        String line;
        String[] tokens;
        int length;

        line = br.readLine();
        length = Integer.parseInt(line);
        this.enemies = new ArrayList<>();

        for (int i = 0; i < length; i++) {
            line = br.readLine();
            tokens = line.split("\\s+");

            switch (tokens[0]) {
                case "1":
                    Enemy enemy = new Demon(
                            Integer.parseInt(tokens[1]),
                            Integer.parseInt(tokens[2]),
                            70, 70);

                    this.enemies.add(enemy);
                    break;
                case "2":
                    break;
            }
        }
    }

    public void tick() {
        for (Block[] blocks : this.blocks) {
            for (Block block : blocks) {
                block.tick();
            }
        }

        for (MovingBlock block : this.movingBlocks) {
            block.tick();
        }

        for (Enemy enemy : this.enemies) {
            enemy.tick(this.blocks, this.movingBlocks,this.enemies);
        }
    }

    public void draw(Graphics g) {
        g.setColor(new Color(57, 46, 48));
        g.fillRect(0,0,GamePanel.WIDTH + 10,GamePanel.HEIGHT + 10);
        for (Block[] blocks : this.blocks) {
            for (Block block : blocks) {
                block.draw(g);
            }
        }

        for (MovingBlock block : this.movingBlocks) {
            block.draw(g);
        }

        for (Enemy enemy : this.enemies) {
            enemy.draw(g);
        }
    }

    public Block[][] getBlocks() {
        return this.blocks;
    }

    public ArrayList<MovingBlock> getMovingBlocks() {
        return this.movingBlocks;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }
}

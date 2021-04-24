package platformer.game.physics;

import platformer.game.entities.Demon;
import platformer.game.entities.Enemy;
import platformer.game.entities.Entity;
import platformer.game.entities.Player;
import platformer.game.objects.Block;
import platformer.game.objects.DeathBlock;
import platformer.game.objects.MovingBlock;
import platformer.game.objects.VictoryBlock;
import platformer.game.state.GameState;

import java.awt.*;

public class Collision {

    private static double move;

    private static boolean entityBlock(final Point p, final Block b) {
        return b.contains(p) && b.getId() != 0;
    }

    public static void blockType1Collision(final Block block,
                                           final Entity entity) {
        int entityX = entity.getX();
        int entityY = entity.getY();
        int entityWidth = entity.getWidth();
        int entityHeight = entity.getHeight();

        if (entity instanceof Player) {
            playerBlockType1Collision(block, (Player) entity,
                    entityX, entityY, entityWidth, entityHeight);
        } else if (entity instanceof Enemy) {
            switch (((Enemy) entity).getId()) {
                case 1:
                    demonBlockType1Collision(block, (Demon) entity,
                            entityX, entityY, entityWidth, entityHeight);
                    break;
                case 2:
                    break;
            }
        }
    }

    private static void playerBlockType1Collision(
            final Block block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {
        playerBlockType1RightCollision(block, player,
                entityX, entityY, entityWidth, entityHeight);
        playerBlockType1LeftCollision(block, player,
                entityX, entityY, entityHeight);
        playerBlockType1TopCollision(block, player,
                entityX, entityY, entityWidth);
        playerBlockType1BottomCollision(block, player,
                entityX, entityY, entityWidth, entityHeight);
    }

    private static void playerBlockType1RightCollision(
            final Block block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {
        if (entityBlock(
                new Point(
                        entityX + entityWidth + (int) GameState.xOffset,
                        entityY + (int) GameState.yOffset + 2),
                block)

                || entityBlock(
                new Point(
                        entityX + entityWidth + (int) GameState.xOffset,
                        entityY + entityHeight + (int) GameState.yOffset - 1),
                block)) {
            player.setRight(false);
            if(entityX + entityWidth + (int) GameState.xOffset>block.getX()) {
                GameState.xOffset-=move;
            }
        }
    }

    private static void playerBlockType1LeftCollision(
            final Block block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityHeight) {
        if (entityBlock(
                new Point(
                        entityX + (int) GameState.xOffset - 1,
                        entityY + (int) GameState.yOffset + 2),
                block)

                || entityBlock(
                new Point(
                        entityX + (int) GameState.xOffset - 1,
                        entityY + entityHeight + (int) GameState.yOffset - 1),
                block)) {
            player.setLeft(false);
            if(entityX + (int) GameState.xOffset<block.getX()+block.getWidth()){
                GameState.xOffset+=Math.abs(move);
            }
        }
    }

    private static void playerBlockType1TopCollision(
            final Block block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityWidth) {
        if (entityBlock(
                new Point(
                        entityX + (int) GameState.xOffset + 1,
                        entityY + (int) GameState.yOffset),
                block)

                || entityBlock(
                new Point(
                        entityX + entityWidth + (int) GameState.xOffset - 2,
                        entityY + (int) GameState.yOffset),
                block)) {
            player.setJumping(false);
            player.setFalling(false);
        }
    }

    private static void playerBlockType1BottomCollision(
            final Block block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {
        if (entityBlock(
                new Point(
                        entityX + (int) GameState.xOffset + 2,
                        entityY + entityHeight + (int) GameState.yOffset + 1),
                block)

                || entityBlock(
                new Point(
                        entityX + entityWidth + (int) GameState.xOffset - 2,
                        entityY + entityHeight + (int) GameState.yOffset + 1),
                block)) {
            player.setY((int) (block.getY() - entityHeight - GameState.yOffset));
            player.setFalling(false);
            player.setTopCollision(true);
        } else {
            if (!player.getTopCollision() && !player.getJumping()) {
                player.setFalling(true);
            }
        }
    }



    public static void blockType2Collision(final VictoryBlock block,
                                           final Entity entity) {
        int entityX = entity.getX();
        int entityY = entity.getY();
        int entityWidth = entity.getWidth();
        int entityHeight = entity.getHeight();

        if (entity instanceof Player) {
            playerBlockType2Collision(block, (Player) entity,
                    entityX, entityY, entityWidth, entityHeight);
        }
    }

    private static void playerBlockType2Collision(
            final VictoryBlock block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {
        playerBlockType2BottomCollision(block, player,
                entityX, entityY, entityWidth, entityHeight);
    }

    private static void playerBlockType2BottomCollision(
            final VictoryBlock block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {
        if (entityBlock(
                new Point(
                        entityX + (int) GameState.xOffset + 2,
                        entityY + entityHeight + (int) GameState.yOffset + 1),
                block)

                || entityBlock(
                new Point(
                        entityX + entityWidth + (int) GameState.xOffset - 2,
                        entityY + entityHeight + (int) GameState.yOffset + 1),
                block)) {
            player.completeLevel();
        }
    }



    public static void movingBlockType1Collision(final MovingBlock block,
                                                 final Entity entity) {
        int entityX = entity.getX();
        int entityY = entity.getY();
        int entityWidth = entity.getWidth();
        int entityHeight = entity.getHeight();

        if (entity instanceof Player) {
            playerMovingBlockType1Collision(block, (Player) entity,
                    entityX, entityY, entityWidth, entityHeight);
        }
    }

    private static void playerMovingBlockType1Collision(
            final MovingBlock block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {
        playerMovingBlockType1RightCollision(block, player,
                entityX, entityY, entityWidth, entityHeight);
        playerMovingBlockType1LeftCollision(block, player,
                entityX, entityY, entityHeight);
        playerMovingBlockType1TopCollision(block, player,
                entityX, entityY, entityWidth);
        playerMovingBlockType1BottomCollision(block, player,
                entityX, entityY, entityWidth, entityHeight);
    }

    private static void playerMovingBlockType1RightCollision(
            final MovingBlock block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {
        playerBlockType1RightCollision(block, player,
                entityX, entityY, entityWidth, entityHeight);
        if (entityBlock(
                new Point(
                        entityX + (int) GameState.xOffset + 1,
                        entityY + (int) GameState.yOffset),
                block)

                || entityBlock(
                new Point(
                        entityX + entityWidth + (int) GameState.xOffset - 2,
                        entityY + (int) GameState.yOffset),
                block)) {
            final double move = block.getMove();
            if (move < 0) {
                GameState.xOffset += move;
            }
        }
    }

    private static void playerMovingBlockType1LeftCollision(
            final MovingBlock block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityHeight) {
        playerBlockType1LeftCollision(block, player,
                entityX, entityY, entityHeight);
        if (entityBlock(
                new Point(
                        entityX + (int) GameState.xOffset - 1,
                        entityY + (int) GameState.yOffset + 2),
                block)

                || entityBlock(
                new Point(
                        entityX + (int) GameState.xOffset - 1,
                        entityY + entityHeight + (int) GameState.yOffset - 1),
                block)) {
            final double move = block.getMove();
            if (move > 0) {
                GameState.xOffset += move;
            }
        }
    }

    private static void playerMovingBlockType1TopCollision(
            final MovingBlock block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityWidth) {

        playerBlockType1TopCollision(block, player,
                entityX, entityY, entityWidth);
    }

    private static void playerMovingBlockType1BottomCollision(
            final MovingBlock block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {

        playerBlockType1BottomCollision(block, player, entityX, entityY, entityWidth, entityHeight);
        if (entityBlock(
                new Point(
                        entityX + (int) GameState.xOffset + 2,
                        entityY + entityHeight + (int) GameState.yOffset + 1),
                block)

                || entityBlock(
                new Point(
                        entityX + entityWidth + (int) GameState.xOffset - 2,
                        entityY + entityHeight + (int) GameState.yOffset + 1),
                block)) {
            move=block.getMove();
            GameState.xOffset += move;
        }
    }


    public static void deathBlockType1Collision(final DeathBlock block,
                                                final Entity entity) {
        int entityX = entity.getX();
        int entityY = entity.getY();
        int entityWidth = entity.getWidth();
        int entityHeight = entity.getHeight();

        if (entity instanceof Player) {
            playerDeathBlockType1Collision(block, (Player) entity,
                    entityX, entityY, entityWidth, entityHeight);
        }
    }

    private static void playerDeathBlockType1Collision(
            final DeathBlock block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {
        playerDeathBlockType1BottomCollision(block, player,
                entityX, entityY, entityWidth, entityHeight);
    }

    private static void playerDeathBlockType1BottomCollision(
            final DeathBlock block,
            final Player player,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {

        if (entityBlock(
                new Point(
                        entityX + (int) GameState.xOffset + 2,
                        entityY + entityHeight + (int) GameState.yOffset + 1),
                block)

                || entityBlock(
                new Point(
                        entityX + entityWidth + (int) GameState.xOffset - 2,
                        entityY + entityHeight + (int) GameState.yOffset + 1),
                block)) {
            player.gameOver();
        }
    }



    private static void demonBlockType1Collision(
            final Block block,
            final Demon demon,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {
        demonBlockType1RightCollision(block, demon,
                entityX, entityY, entityWidth, entityHeight);
        demonBlockType1LeftCollision(block, demon,
                entityX, entityY, entityHeight);
        demonBlockType1TopCollision(block, demon,
                entityX, entityY, entityWidth);
        demonBlockType1BottomCollision(block, demon,
                entityX, entityY, entityWidth, entityHeight);
    }

    private static void demonBlockType1RightCollision(
            final Block block,
            final Demon demon,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {
        if (entityBlock(
                new Point(
                        entityX + entityWidth,
                        entityY),
                block)

                || entityBlock(
                new Point(
                        entityX + entityWidth,
                        entityY + entityHeight - 1),
                block)) {
            demon.setRight(false);
            demon.setLeft(true);
        }
    }

    private static void demonBlockType1LeftCollision(
            final Block block,
            final Demon demon,
            final int entityX,
            final int entityY,
            final int entityHeight) {
        if (entityBlock(
                new Point(
                        entityX + -1,
                        entityY + +2),
                block)

                || entityBlock(
                new Point(
                        entityX + -1,
                        entityY + entityHeight - 1),
                block)) {
            demon.setLeft(false);
            demon.setRight(true);
        }
    }

    private static void demonBlockType1TopCollision(
            final Block block,
            final Demon demon,
            final int entityX,
            final int entityY,
            final int entityWidth) {
        if (entityBlock(
                new Point(
                        entityX + 1,
                        entityY),
                block)

                || entityBlock(
                new Point(
                        entityX + entityWidth - 2,
                        entityY),
                block)) {
            demon.setFalling(false);
        }
    }

    private static void demonBlockType1BottomCollision(
            final Block block,
            final Demon demon,
            final int entityX,
            final int entityY,
            final int entityWidth,
            final int entityHeight) {
        if (entityBlock(
                new Point(
                        entityX,
                        entityY + entityHeight + 1),
                block)

                || entityBlock(
                new Point(
                        entityX + entityWidth - 2,
                        entityY + entityHeight + +1),
                block)) {
            demon.setY((int) (block.getY() - entityHeight));
            demon.setFalling(false);
            demon.setTopCollision(true);
        } else {
            if (!demon.getTopCollision()) {
                demon.setFalling(true);
            }
        }
    }



    private static boolean playerEnemy(final Point p, final Rectangle r) {
        return r.contains(p);
    }

    public static void enemyEntityCollision(final Enemy enemy,
                                            final Entity entity) {
        if (entity instanceof Player) {
            switch (enemy.getId()) {
                case 1:
                    playerDemonCollision((Player) entity, enemy);
                    break;
                case 2:
                    break;
            }
        }
    }



    private static void playerDemonCollision(final Player player,
                                             final Enemy enemy) {
        int playerX = player.getX();
        int playerY = player.getY();
        int playerWidth = player.getWidth();
        int playerHeight = player.getHeight();

        int enemyX = enemy.getX();
        int enemyY = enemy.getY() + 2;
        int enemyWidth = enemy.getWidth();
        int enemyHeight = enemy.getHeight();

        Rectangle enemyR = new Rectangle(
                enemyX, enemyY, enemyWidth, enemyHeight);

        playerDemonRightCollision(
                player,
                playerX, playerY, playerWidth, playerHeight,
                enemyR);
        playerDemonLeftCollision(
                player,
                playerX, playerY, playerHeight,
                enemyR);
        playerDemonTopCollision(
                player,
                playerX, playerY, playerWidth,
                enemyR);
        playerDemonBottomCollision(
                player,
                playerX, playerY, playerWidth, playerHeight,
                enemy,
                enemyR);
    }

    private static void playerDemonRightCollision(
            final Player player,
            final int playerX,
            final int playerY,
            final int playerWidth,
            final int playerHeight,
            final Rectangle enemyR) {
        if (playerEnemy(
                new Point(playerX + playerWidth + (int) GameState.xOffset,
                        playerY + (int) GameState.yOffset + 2),
                enemyR)

                || playerEnemy(
                new Point(
                        playerX + playerWidth + (int) GameState.xOffset,
                        playerY + playerHeight + (int) GameState.yOffset - 1),
                enemyR))
            player.gameOver();
    }

    private static void playerDemonLeftCollision(
            final Player player,
            final int playerX,
            final int playerY,
            final int playerHeight,
            final Rectangle enemyR) {
        if (playerEnemy(
                new Point(
                        playerX + (int) GameState.xOffset - 1,
                        playerY + (int) GameState.yOffset + 2),
                enemyR)

                || playerEnemy(
                new Point(
                        playerX + (int) GameState.xOffset - 1,
                        playerY + playerHeight + (int) GameState.yOffset - 1),
                enemyR)) {
            player.gameOver();
        }
    }

    private static void playerDemonTopCollision(
            final Player player,
            final int playerX,
            final int playerY,
            final int playerWidth,
            final Rectangle enemyR) {

        if (playerEnemy(
                new Point(
                        playerX + (int) GameState.xOffset + 1,
                        playerY + (int) GameState.yOffset),
                enemyR)

                || playerEnemy(
                new Point(
                        playerX + playerWidth + (int) GameState.xOffset - 2,
                        playerY + (int) GameState.yOffset),
                enemyR)) {
            player.gameOver();
        }
    }

    private static void playerDemonBottomCollision(
            final Player player,
            final int playerX,
            final int playerY,
            final int playerWidth,
            final int playerHeight,
            final Enemy enemy,
            final Rectangle enemyR) {
        if (playerEnemy(
                new Point(
                        playerX + (int) GameState.xOffset + 2,
                        playerY + playerHeight + (int) GameState.yOffset + 1),
                enemyR)

                || playerEnemy(
                new Point(
                        playerX + playerWidth + (int) GameState.xOffset - 2,
                        playerY + playerHeight + (int) GameState.yOffset + 1),
                enemyR)) {
            player.setJumping(true);
            enemy.death();
        }
    }
}

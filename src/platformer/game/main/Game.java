package platformer.game.main;

import javafx.embed.swing.JFXPanel;

import javax.swing.*;
import java.awt.*;

public class Game {

    static{
        JFXPanel fxPanel = new JFXPanel();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Gkeik");
        ImageIcon img = new ImageIcon("res/Entities/nca.png");
        frame.setIconImage(img.getImage());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(new GamePanel(), BorderLayout.CENTER);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}

package platformer.game.state;

import java.awt.*;
import java.util.Stack;

public class GameStateManager {

    private Stack<GameState> states;

    public GameStateManager() {
        this.states = new Stack<>();
        this.states.push(new MenuState(this));
    }

    void pop(){
        this.states.pop();
    }

    void push(final GameState state){
        this.states.push(state);
    }

    public void draw(final Graphics g) {
        this.states.peek().draw(g);
    }

    public void tick() {
        this.states.peek().tick();
    }

    public void keyPressed(final int k) {
        this.states.peek().keyPressed(k);
    }

    public void keyReleased(final int k) {
        this.states.peek().keyReleased(k);
    }
}

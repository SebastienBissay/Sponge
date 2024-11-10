package sponge.grid;


import sponge.Bubble;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final List<Bubble> bubbles = new ArrayList<>();

    public Cell() {
    }

    public void addBubble(Bubble bubble) {
        bubbles.add(bubble);
    }

    public List<Bubble> getBubbles() {
        return bubbles;
    }
}

package sponge.grid;


import sponge.Bubble;

import static processing.core.PApplet.*;
import static sponge.parameters.Parameters.HEIGHT;
import static sponge.parameters.Parameters.WIDTH;

public class Grid {
    private final Cell[][] cells;
    private final float cellWidth, cellHeight;

    public Grid(float cellWidth, float cellHeight) {
        this.cellWidth = cellWidth;
        this.cellHeight = cellHeight;
        int nCellX = ceil(WIDTH / cellWidth);
        int nCellY = ceil(HEIGHT / cellHeight);
        cells = new Cell[nCellX][nCellY];
        for (int i = 0; i < nCellX; i++) {
            for (int j = 0; j < nCellY; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    public void add(Bubble bubble) {
        for (int i = max(0, floor((bubble.getPosition().x - bubble.getRadius()) / cellWidth));
             i < min(cells.length, ceil((bubble.getPosition().x + bubble.getRadius()) / cellWidth));
             i++) {
            for (int j = max(0, floor((bubble.getPosition().y - bubble.getRadius()) / cellHeight));
                 j < min(cells[0].length, ceil((bubble.getPosition().y + bubble.getRadius()) / cellHeight));
                 j++) {
                cells[i][j].addBubble(bubble);
                bubble.addCell(cells[i][j]);
            }
        }
    }
}

package sponge;

import processing.core.PApplet;
import processing.core.PVector;
import sponge.grid.Cell;

import java.util.ArrayList;
import java.util.List;

import static processing.core.PApplet.sq;
import static sponge.parameters.Parameters.*;

public class Bubble {
    private static int idIncrement = 0;


    private final float radius;
    private final int id;
    private final List<Cell> cells = new ArrayList<>();
    boolean isMoving = false;
    private PVector position;
    private PVector velocity = new PVector(0, 0);

    public Bubble(PVector position, float radius) {
        this.position = position;
        this.radius = radius;
        id = idIncrement++;
    }

    public void render(PApplet pApplet) {
        pApplet.noFill();
        pApplet.stroke(STROKE_COLOR.red(), STROKE_COLOR.green(), STROKE_COLOR.blue(), STROKE_COLOR.alpha());
        pApplet.circle(position.x, position.y, 2 * radius);
    }

    public void process() {
        for (Cell c : cells) {
            for (Bubble b : c.getBubbles()) {
                if (b.id > id) {
                    PVector v = PVector.sub(position, b.position);
                    if (v.magSq() < sq(radius + b.radius)) {
                        v.setMag(radius + b.radius - v.mag());
                        velocity.add(v);
                        b.velocity.sub(v);
                        isMoving = true;
                        b.isMoving = true;
                    }
                }
            }
        }
    }

    public void move() {
        velocity.mult(CIRCLE_VELOCITY);
        position.add(velocity.limit(CIRCLE_MAX_SPEED));
        velocity = new PVector(0, 0);
        isMoving = false;
    }

    public PVector getPosition() {
        return position;
    }

    public float getRadius() {
        return radius;
    }

    public void addCell(Cell cell) {
        this.cells.add(cell);
    }
}

package sponge;

import processing.core.PApplet;
import processing.core.PVector;
import sponge.grid.Grid;

import java.util.ArrayList;
import java.util.List;

import static sponge.parameters.Parameters.*;
import static sponge.save.SaveUtil.saveSketch;

public class Sponge extends PApplet {

    private List<Bubble> bubbles = new ArrayList<>();

    public static void main(String[] args) {
        PApplet.main(Sponge.class);
    }

    @Override
    public void settings() {
        size(WIDTH, HEIGHT);
        randomSeed(SEED);
        noiseSeed(floor(random(MAX_INT)));
    }

    @Override
    public void setup() {
        for (int k = 0; k < NUMBER_OF_CIRCLES; k++) {
            float angle = random(TWO_PI);
            float r = sqrt(random(1)) * INITIAL_RADIUS;
            PVector p = new PVector(width / 2f + r * cos(angle), height / 2f + r * sin(angle));
            bubbles.add(new Bubble(p, 1 + CIRCLE_MAX_RADIUS * pow(noise(angle / (TWO_PI * NOISE_SCALE),
                    r * NOISE_SCALE), NOISE_POWER)));
        }
    }

    @Override
    public void draw() {
        background(BACKGROUND_COLOR.red(), BACKGROUND_COLOR.green(), BACKGROUND_COLOR.blue());

        Grid grid = new Grid(CELL_WIDTH, CELL_HEIGHT);
        for (Bubble b : bubbles) {
            grid.add(b);
        }
        for (Bubble b : bubbles) {
            b.process();
        }
        boolean stop = true;
        for (Bubble b : bubbles) {
            if (b.isMoving) {
                stop = false;
            }
            b.move();
            b.render(this);
        }

        if (stop) {
            noLoop();
            saveSketch(this);
        }
    }
}

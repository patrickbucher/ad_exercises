package ch.hslu.ad.sw05.ex01;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Ball extends Thread implements Runnable {
    private Ellipse2D circle;
    private Color color;
    private int x;
    private int y;
    private int r;
    private DrawingArea area;
    private volatile boolean done = false;
    private int fallSpeed;

    public Ball(int x, int y, int r, Color c, DrawingArea panel) {
        this.circle = new Ellipse2D.Float(x, y, r, r);
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = c;
        this.area = panel;
        this.fallSpeed = area.getRandomNumber(5, 20);
    }

    public Color getColor() {
        return color;
    }

    public Ellipse2D getCircle() {
        return circle;
    }

    public boolean isDone() {
        return done;
    }

    @Override
    public void run() {
        while (!done) {
            area.repaint();
            y += fallSpeed;
            if (y >= area.getHeight()) {
                done = true;
            }
            circle = new Ellipse2D.Float(x, y, r, r);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                done = true;
            }
        }
    }
}

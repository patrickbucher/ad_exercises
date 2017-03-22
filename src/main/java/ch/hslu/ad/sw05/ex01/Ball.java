package ch.hslu.ad.sw05.ex01;

import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Ball extends Thread implements Runnable {

    private static final int FALL_INTERVAL = 5;
    private static final int WHITEN_INTERVAL = 10;
    private static final float FALL_SPEED_INC = 0.05f;

    private Ellipse2D circle;
    private Color color;
    private int x;
    private int y;
    private int r;
    private DrawingArea area;

    private boolean atTheBottom = false;
    private boolean whited = false;
    private boolean interrupted = false;

    private float fallSpeed;

    public Ball(int x, int y, int r, Color c, DrawingArea panel) {
        this.circle = new Ellipse2D.Float(x, y, r, r);
        this.x = x;
        this.y = y;
        this.r = r;
        this.color = c;
        this.area = panel;
        this.fallSpeed = 1.0f;
    }

    public Color getColor() {
        return color;
    }

    public Ellipse2D getCircle() {
        return circle;
    }

    public boolean isDone() {
        return atTheBottom && whited & !interrupted;
    }

    @Override
    public void run() {
        while (!atTheBottom && !interrupted) {
            area.repaint();
            if (y >= area.getHeight() - r) {
                atTheBottom = true;
            } else {
                y += fallSpeed;
                fallSpeed += FALL_SPEED_INC;
            }
            circle = new Ellipse2D.Float(x, y, r, r);
            try {
                Thread.sleep(FALL_INTERVAL);
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }
        while (!interrupted && !whited) {
            area.repaint();
            color = whitenColor(color, 1);
            if (color.equals(Color.WHITE)) {
                whited = true;
            }
            try {
                Thread.sleep(WHITEN_INTERVAL);
            } catch (InterruptedException e) {
                interrupted = true;
            }
        }
    }

    private Color whitenColor(Color color, int step) {
        int red = increase(color.getRed(), 255, step);
        int green = increase(color.getGreen(), 255, step);
        int blue = increase(color.getBlue(), 255, step);
        return new Color(red, green, blue);
    }

    private int increase(int number, int max, int step) {
        if (number < max) {
            return number + step;
        } else {
            return number;
        }
    }
}

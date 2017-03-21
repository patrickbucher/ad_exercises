package ch.hslu.ad.sw05.ex01;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;

// Swing UI serialization is only done in special mental institutions.
@SuppressWarnings("serial")
public class DrawingArea extends JPanel {

    private List<Ball> balls = new ArrayList<>();

    private Dimension areaDimension = new Dimension(600, 400);

    private Random rnd = new Random(System.currentTimeMillis());

    public DrawingArea() {
        setMinimumSize(areaDimension);
        setSize(areaDimension);
        setBackground(Color.white);
        setPreferredSize(areaDimension);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                createBall(e.getX(), e.getY());
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }
        });
    }

    public void createBall(int x, int y) {
        int radius = getRandomNumber(20, 50);
        Color color = getRandomColor();
        Ball ball = new Ball(x, y, radius, color, this);
        balls.add(ball);
        ball.start();
        System.out.println("created a ball, now " + balls.size() + " balls");
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        Iterator<Ball> ballIterator = balls.iterator();
        while (ballIterator.hasNext()) {
            Ball ball = ballIterator.next();
            if (!ball.isDone()) {
                g2d.setColor(ball.getColor());
                g2d.fill(ball.getCircle());
            } else {
                ballIterator.remove();
                System.out.println("removed a ball, still " + balls.size() + " balls");
            }
        }
    }

    public int getHeight() {
        return (int) areaDimension.getHeight();
    }

    public int getRandomNumber(int min, int max) {
        return rnd.nextInt(max - min + 1) + min;
    }

    private Color getRandomColor() {
        int r = getRandomNumber(0, 255);
        int g = getRandomNumber(0, 255);
        int b = getRandomNumber(0, 255);
        return new Color(r, g, b);
    }

}

package ch.hslu.ad.sw05.ex01;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;

// Swing UI serialization is only done in special mental institutions.
@SuppressWarnings("serial")
public class Playground extends JFrame {

    private DrawingArea area = new DrawingArea();

    public static void main(String args[]) {
        new Playground();
    }

    public Playground() {
        super("Unterschiedlich schnell fallende Bälle unterschiedlicher Farben und Grössen");
        getRootPane().setLayout(new GridBagLayout());
        getRootPane().add(area, new GridBagConstraints());
        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}

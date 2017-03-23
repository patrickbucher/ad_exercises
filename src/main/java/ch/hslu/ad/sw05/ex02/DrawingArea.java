package ch.hslu.ad.sw05.ex02;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

// Swing UI serialization is only done in special mental institutions.
@SuppressWarnings("serial")
public class DrawingArea extends JPanel implements MouseListener, WindowListener {

	private final List<Image> images;

	private RotatingEarth earth = null;

	private Image paintImage;

	public DrawingArea(List<Image> images, JFrame parent) {
		this.images = images;
		setPreferredSize(new Dimension(200, 200));
		setBackground(Color.WHITE);
		addMouseListener(this);
		parent.addWindowListener(this);
	}

	public void paintImage(Image image) {
		this.paintImage = image;
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(paintImage, 10, 10, this);
	}

	@Override
	public void windowActivated(WindowEvent e) {
		earth = new RotatingEarth(images, true, 0, this);
		earth.start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		earth.changeDirection();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}

package bv.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

/** A class which manages all inputs to the canvas of a {@link Display}.
 * @author	Brennan Colberg
 * @since	Nov 25, 2017
 */
public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {

	public Display display;
	
	// TODO actual input tracking, etc
	public void mouseWheelMoved(MouseWheelEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}

}

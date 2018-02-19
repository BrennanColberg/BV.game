/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
package bv.gameFramework.core;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;

import bv.math.CVector;

/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	public static Display display = null;
	public static boolean live = false;
	
	protected static Long lastMousePress = 0L;
	protected static Long lastMouseRelease = 0L;
	protected static CVector lastMousePressPosition = new CVector(0,0);
	protected static CVector lastMousePressAdjustedPosition = new CVector(0,0);
	protected static CVector mousePosition = new CVector(0,0);
	protected static CVector mouseAdjustedPosition = new CVector(0,0);
	protected static boolean recentMousePress = false;
	
	public static Long getLastMousePress() { return lastMousePress; }
	public static Long getLastMouseRelease() { return lastMouseRelease; }
	public static boolean isMousePressed() { return lastMouseRelease < lastMousePress; }
	public static boolean wasMousePressedRecently() { return recentMousePress; }
	
	public static CVector getLastMousePressPosition() { return lastMousePressPosition; }
	public static CVector getLastMousePressAdjustedPosition() { return lastMousePressAdjustedPosition; }
	public static CVector getMousePosition() { return mousePosition; }
	public static CVector getMouseAdjustedPosition() { return mouseAdjustedPosition; }
	
	protected static HashMap<Integer,Long> lastKeyPress = new HashMap<Integer,Long>();
	protected static HashMap<Integer,Long> lastKeyRelease = new HashMap<Integer,Long>();
	protected static HashMap<Integer,Boolean> recentKeyPress = new HashMap<Integer,Boolean>();
	
	public static Long getLastKeyPress(int index) { if (lastKeyPress.containsKey(index)) return lastKeyPress.get(index); else { lastKeyPress.put(index, 0L); return 0L; } }
	public static Long getLastKeyRelease(int index) { if (lastKeyRelease.containsKey(index)) return lastKeyRelease.get(index); else { lastKeyRelease.put(index, 0L); return 0L; } }
	public static boolean isKeyPressed(int index) { return getLastKeyRelease(index) < getLastKeyPress(index); }
	public static boolean wasKeyPressedRecently(int index) { if (recentKeyPress.containsKey(index)) return recentKeyPress.get(index); else { recentKeyPress.put(index, isKeyPressed(index)); return isKeyPressed(index); } }
	
	@Deprecated public void mouseWheelMoved(MouseWheelEvent e) {}
	@Deprecated public void mouseMoved(MouseEvent e) {}
	@Deprecated public void mousePressed(MouseEvent e) { lastMousePress = e.getWhen(); lastMousePressPosition = new CVector(e.getX(), e.getY()); lastMousePressAdjustedPosition = Core.renderEngine.renderer.normalize(lastMousePressPosition); }
	@Deprecated public void mouseReleased(MouseEvent e) { lastMouseRelease = e.getWhen(); }
	@Deprecated public void keyTyped(KeyEvent e) {}
	@Deprecated public void keyPressed(KeyEvent e) { lastKeyPress.put(e.getKeyCode(), e.getWhen()); }
	@Deprecated public void keyReleased(KeyEvent e) { lastKeyRelease.put(e.getKeyCode(), e.getWhen()); }
	
	public static void tick() {
		Point mousePoint = null;
		if (display != null) mousePoint = display.getFrame().getMousePosition();
		if (mousePoint != null) {
			mousePosition = new CVector(mousePoint);
			mouseAdjustedPosition = Core.renderEngine.renderer.normalize(mousePosition.plus(new CVector(0,-24)));
		}
	}
	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

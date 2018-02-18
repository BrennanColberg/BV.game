/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
package bv.gameFramework.core;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.HashMap;

import bv.gameFramework.state.Tickable;
import bv.gui.Input;
import bv.math.CVector;

/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
public class InputEngine extends Input implements Tickable {
	
	public static boolean live = false;
	
	protected Long lastMousePress = 0L;
	protected Long lastMouseRelease = 0L;
	protected CVector lastMousePressPosition = new CVector(0,0);
	protected CVector lastMousePressAdjustedPosition = new CVector(0,0);
	protected CVector mousePosition = new CVector(0,0);
	protected CVector mouseAdjustedPosition = new CVector(0,0);
	protected boolean recentMousePress = false;
	
	public Long getLastMousePress() { return lastMousePress; }
	public Long getLastMouseRelease() { return lastMouseRelease; }
	public boolean isMousePressed() { return lastMouseRelease < lastMousePress; }
	public boolean wasMousePressedRecently() { return recentMousePress; }
	
	public CVector getLastMousePressPosition() { return lastMousePressPosition; }
	public CVector getLastMousePressAdjustedPosition() { return lastMousePressAdjustedPosition; }
	public CVector getMousePosition() { return mousePosition; }
	public CVector getMouseAdjustedPosition() { return mouseAdjustedPosition; }
	
	protected HashMap<Integer,Long> lastKeyPress = new HashMap<Integer,Long>();
	protected HashMap<Integer,Long> lastKeyRelease = new HashMap<Integer,Long>();
	protected HashMap<Integer,Boolean> recentKeyPress = new HashMap<Integer,Boolean>();
	
	public Long getLastKeyPress(int index) { if (lastKeyPress.containsKey(index)) return lastKeyPress.get(index); else { lastKeyPress.put(index, 0L); return 0L; } }
	public Long getLastKeyRelease(int index) { if (lastKeyRelease.containsKey(index)) return lastKeyRelease.get(index); else { lastKeyRelease.put(index, 0L); return 0L; } }
	public boolean isKeyPressed(int index) { return getLastKeyRelease(index) < getLastKeyPress(index); }
	public boolean wasKeyPressedRecently(int index) { if (recentKeyPress.containsKey(index)) return recentKeyPress.get(index); else { recentKeyPress.put(index, isKeyPressed(index)); return isKeyPressed(index); } }
	
	@Deprecated public void mouseWheelMoved(MouseWheelEvent e) {}
//	@Deprecated public void mouseMoved(MouseEvent e) { mousePosition = new CVector(e.getX(), e.getY()); mouseAdjustedPosition = Core.renderEngine.renderer.normalize(mousePosition); }
	@Deprecated public void mousePressed(MouseEvent e) { lastMousePress = e.getWhen(); lastMousePressPosition = new CVector(e.getX(), e.getY()); lastMousePressAdjustedPosition = Core.renderEngine.renderer.normalize(lastMousePressPosition); }
	@Deprecated public void mouseReleased(MouseEvent e) { lastMouseRelease = e.getWhen(); }
//	@Deprecated public void keyTyped(KeyEvent e) {}
	@Deprecated public void keyPressed(KeyEvent e) { lastKeyPress.put(e.getKeyCode(), e.getWhen()); }
	@Deprecated public void keyReleased(KeyEvent e) { lastKeyRelease.put(e.getKeyCode(), e.getWhen()); }
	
	public void tick() {
		Point mousePoint = display.getFrame().getMousePosition();
		if (mousePoint != null) {
			this.mousePosition = new CVector(mousePoint);
			this.mouseAdjustedPosition = Core.renderEngine.renderer.normalize(this.mousePosition.plus(new CVector(0,-24)));
		}
	}
	
}

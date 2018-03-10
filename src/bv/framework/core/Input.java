package bv.framework.core;

import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.HashMap;

import bv.framework.math.CVector;
import bv.framework.state.GameState;

/** A static class used to collect and categorize all input (from both the keyboard and mouse).
 * @author Brennan D. Colberg - {@link http://github.com/BrennanColberg} */
public class Input implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {
	
	/* VARIABLES */
	
	public static Display display = null;
	
	// click time
	
	/** The last time the left mouse button was pressed, in milliseconds, in {@link Long} form. */
	protected static Long lastLeftClickPress = 0L;
	/** The last time the right mouse button was pressed, in milliseconds, in {@link Long} form. */
	protected static Long lastRightClickPress = 0L;
	/** The last time the left mouse button was released, in milliseconds, in {@link Long} form. */
	protected static Long lastLeftClickRelease = 0L;
	/** The last time the right mouse button was released, in milliseconds, in {@link Long} form. */
	protected static Long lastRightClickRelease = 0L;
	
	// click position
	
	/** The last position at which the left mouse button was pressed, relative to the {@link Display}'s top left corner, in {@link CVector} form. */
	protected static CVector lastClickPosition = new CVector(0,0);
	/** The last position at which the right mouse button was pressed, relative to the {@link Display}'s top left corner, in {@link CVector} form. */
	protected static CVector lastRightClickPosition = new CVector(0,0);
	/** The last position at which the left mouse button was pressed, relative to the {@link Display}'s current center and scaled appropriately to the current {@link GameState}, in {@link CVector} form. */
	protected static CVector lastClickAdjustedPosition = new CVector(0,0);
	/** The last position at which the right mouse button was pressed, relative to the {@link Display}'s current center and scaled appropriately to the current {@link GameState}, in {@link CVector} form. */
	protected static CVector lastRightClickAdjustedPosition = new CVector(0,0);
	
	// mouse position
	
	/** The current position of the mouse pointer, relative to the {@link Display}'s top left corner, in {@link CVector} form. */
	protected static CVector mousePosition = new CVector(0,0);
	/** The current position of the mouse pointer, relative to the {@link Display}'s current center and scaled appropriately to the current {@link GameState}, in {@link CVector} form. */
	protected static CVector mouseAdjustedPosition = new CVector(0,0);

	// key hashmap data
	
	/** A {@link HashMap} that stores the time, in milliseconds, each key was last pressed (in {@link Long} form, accessed by {@link KeyEvent}.VK_[KEY] {@link Integer}s). */
	protected static HashMap<Integer,Long> lastKeyPress = new HashMap<Integer,Long>();
	/** A {@link HashMap} that stores the time, in milliseconds, each key was last released (in {@link Long} form, accessed by {@link KeyEvent}.VK_[KEY] {@link Integer}s). */
	protected static HashMap<Integer,Long> lastKeyRelease = new HashMap<Integer,Long>();
	
	
	/* GETTERS */
	
	// click time
	
	/** @return the last time the left mouse button was pressed, in milliseconds, in {@link Long} form */
	public static Long getLastClickTime() { return lastLeftClickPress; }
	/** @return the last time the right mouse button was pressed, in milliseconds, in {@link Long} form */
	public static Long getLastRightClickTime() { return lastRightClickPress; }
	/** @return whether or not the left mouse button is currently pressed, in {@link Boolean} form */
	public static boolean isMousePressed() { return lastLeftClickRelease < lastLeftClickPress; }
	/** @return whether or not the right mouse button is currently pressed, in {@link Boolean} form */
	public static boolean isRightMousePressed() { return lastRightClickRelease < lastRightClickPress; }
//	/** @return whether or not the left mouse button was recently pressed, in {@link Boolean} form. Returns true for exactly one tick per press. */
//	public static boolean recentClick() { return recentLeftClick; }
//	/** @return whether or not the right mouse button was recently pressed, in {@link Boolean} form. Returns true for exactly one tick per press. */
//	public static boolean recentRightClick() { return recentRightClick; }
	
	// click position
	
	/** @return the last position at which the left mouse button was pressed, relative to the {@link Display}'s top left corner, in {@link CVector} form. */
	public static CVector getLastClickPosition() { return lastClickPosition; }
	/** @return the last position at which the right mouse button was pressed, relative to the {@link Display}'s top left corner, in {@link CVector} form. */
	public static CVector getLastRightClickPosition() { return lastRightClickPosition; }
	/** @return the last position at which the left mouse button was pressed, relative to the {@link Display}'s current center and scaled appropriately to the current {@link GameState}, in {@link CVector} form. */
	public static CVector getLastClickAdjustedPosition() { return lastClickAdjustedPosition; }
	/** @return the last position at which the right mouse button was pressed, relative to the {@link Display}'s current center and scaled appropriately to the current {@link GameState}, in {@link CVector} form. */
	public static CVector getLastRightClickAdjustedPosition() { return lastRightClickAdjustedPosition; }
	
	// mouse position
	
	/** @return the current position of the mouse pointer, relative to the {@link Display}'s top left corner, in {@link CVector} form. */
	public static CVector getMousePosition() { return mousePosition; }
	/** @return the current position of the mouse pointer, relative to the {@link Display}'s current center and scaled appropriately to the current {@link GameState}, in {@link CVector} form. */
	public static CVector getMouseAdjustedPosition() { return mouseAdjustedPosition; }
	
	// key data
	
	/** @param index the relevant key, in {@link Integer} form (accessible through {@link KeyEvent}.VK_[KEY])
	 * @return the time, in milliseconds, the key was last pressed (in {@link Long} form) */
	public static Long getLastKeyPress(int index) { 
		if (lastKeyPress.containsKey(index)) return lastKeyPress.get(index); 
		else { lastKeyPress.put(index, 0L); return 0L; } 
	}
	/** @param index the relevant key, in {@link Integer} form (accessible through {@link KeyEvent}.VK_[KEY])
	 * @return the time, in milliseconds, the key was last released (in {@link Long} form) */
	public static Long getLastKeyRelease(int index) {
		if (lastKeyRelease.containsKey(index)) return lastKeyRelease.get(index); 
		else { lastKeyRelease.put(index, 0L); return 0L; }
	}
	/** @param index the relevant key, in {@link Integer} form (accessible through {@link KeyEvent}.VK_[KEY])
	 * @return whether or not the key is currently pressed (in {@link Boolean} form) */
	public static boolean isKeyPressed(int index) { return getLastKeyRelease(index) < getLastKeyPress(index); }
	

	/** Deprecated, automatic method. Used to update mouse click time and position for both right and left clicks.
	 * @param e	Automatically generated and inputted MouseEvent; describes the click and is used to gather data */
	@Deprecated public void mousePressed(MouseEvent e) { 
		switch (e.getButton()) {
			case MouseEvent.BUTTON1:
				lastLeftClickPress = e.getWhen();
				lastClickPosition = new CVector(e.getX(), e.getY()); 
				lastClickAdjustedPosition = Core.renderEngine.renderer.normalize(lastClickPosition); 
				break;
			case MouseEvent.BUTTON2:
				lastRightClickPress = e.getWhen();
				lastRightClickPosition = new CVector(e.getX(), e.getY()); 
				lastRightClickAdjustedPosition = Core.renderEngine.renderer.normalize(lastRightClickPosition); 
				break;
			default: break;
		}
	}
	/** Deprecated, automatic method. Used to update mouse button release time and position for both right and left clicks.
	 * @param e	Automatically generated and inputted MouseEvent; describes the release and is used to gather data */
	@Deprecated public void mouseReleased(MouseEvent e) { 
		switch (e.getButton()) {
			case MouseEvent.BUTTON1:
				lastLeftClickRelease = e.getWhen();
				break;
			case MouseEvent.BUTTON2: 
				lastRightClickRelease = e.getWhen(); 
				break;
			default: break;
		}
	}

	/** Deprecated, automatic method. Used to update key press time.
	 * @param e	Automatically generated and inputted KeyEvent; describes the press and is used to gather data */
	@Deprecated public void keyPressed(KeyEvent e) { lastKeyPress.put(e.getKeyCode(), e.getWhen()); }
	/** Deprecated, automatic method. Used to update key release time.
	 * @param e	Automatically generated and inputted KeyEvent; describes the release and is used to gather data */
	@Deprecated public void keyReleased(KeyEvent e) { lastKeyRelease.put(e.getKeyCode(), e.getWhen()); }
	
	/** Deprecated, automatic method. Used to actively update mouse position at a quicker refresh rate then the passive mouseMoved(MouseEvent). */
	@Deprecated public static void tick() {
		Point mousePoint = null;
		if (display != null) mousePoint = display.getFrame().getMousePosition();
		if (mousePoint != null) {
			mousePosition = new CVector(mousePoint);
			mouseAdjustedPosition = Core.renderEngine.renderer.normalize(mousePosition.plus(new CVector(0,-24)));
		}
	}
	
	// unused but necessary implements
	public void mouseWheelMoved(MouseWheelEvent e) {}
	public void mouseMoved(MouseEvent e) {}
	public void keyTyped(KeyEvent e) {}
	public void mouseDragged(MouseEvent e) {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	
}

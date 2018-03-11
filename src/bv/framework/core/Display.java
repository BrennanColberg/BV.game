package bv.framework.core;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import bv.framework.math.CVector;
import bv.framework.math.Rect;

/** A class which consolidates all processes necessary to manage and display a computer window in the context of this framework. 
 * @author Brennan D. Colberg - {@link http://github.com/BrennanColberg} */
public class Display {
	
	/* VARIABLES */
	
	/** The {@link JFrame} that the running program uses. Only relevant for window sizing and position; otherwise, stores a {@link Canvas}. */
	private JFrame frame = new JFrame();
	/** The {@link Canvas} that the running program uses; to this is displayed the program's actual graphics. */
	private Canvas canvas;
	
	/** The size of the current window, in {@link CVector} form. */
	private CVector size;

	
	/* CONSTRUCTORS */
	
	/** The default constructor; used to create the GUI for any running program. 
	 * @param newSize The assigned size of the frame, in pixels, in {@link CVector} form 
	 * @param newCanvas The optional pre-configured {@link Canvas} that will be displayed. If null, a new {@link Canvas} will be initialized. */
 	public Display(CVector newSize, Canvas newCanvas) {
 		
 		// makes null newCanvas inputs into blank canvasses
 		if (newCanvas == null) newCanvas = new Canvas();
 		
 		// initializes and configures frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
 		
		// plugs all inputs into relevant variables
 		this.setSize(newSize);
 		this.setCanvas(newCanvas);
 		
 		// configures Input to work with and focus on this program's display
 		this.loadInput();
 		
 	}
 	/** The simplified constructor; creates a new {@link Canvas}, instead of taking a pre-formatted one.
 	 * @param newSize The assigned size of the frame, in pixels, in {@link CVector} form */
 	public Display(CVector newSize) {
 		this(newSize, null);
 	}
 	
	
	/* GETTERS & SETTERS */
 	
 	/** @return The {@link Canvas} that the running program uses */
 	public Canvas getCanvas() {
 		return this.canvas;
 	}
 	/** @param newCanvas The new {@link Canvas} for the running program to use */
 	public void setCanvas(Canvas newCanvas) {
 		this.canvas = newCanvas;
 		frame.add(canvas);
 	}
 	
 	/** @return The {@link JFrame} that the running program uses. */
 	public JFrame getFrame() {
 		return this.frame;
 	}
	
 	/** @return The size of the current window, in pixels, in {@link CVector} form. */
 	public CVector getSize() {
 		return this.size;
 	}
 	/** @param newSize The new size to which the current window should be set, in {@link CVector} form */
	public void setSize(CVector newSize) {
		
		this.size = new CVector(newSize);
		Dimension newFrameDimension = this.size.toDimension();
		
		frame.setMaximumSize(newFrameDimension);
		frame.setMinimumSize(newFrameDimension);
		frame.setPreferredSize(newFrameDimension);
		
	}
	
	/** The configuration method for {@link Input} to this {@link Display}. 
	 * 	Resets variables in {@link Input} to refer to this {@link Display}, and adds {@link Input} to the {@link Canvas} to which the current program renders. */
	public void loadInput() {
		Input.display = this;
		canvas.addKeyListener(new Input());
		canvas.addMouseListener(new Input());
		canvas.addMouseMotionListener(new Input());
		canvas.addMouseWheelListener(new Input());
	}
	
	/** @return whether or not the display is visible, in {@link Boolean} form */
	public boolean getVisible() {
		return frame.isVisible() && canvas.isVisible();
	}
	/** @param newVisible whether or not the display should now be visible, in {@link Boolean} form */
	public void setVisible(boolean newVisible) {
		canvas.setVisible(newVisible);
		frame.setVisible(newVisible);
		if (newVisible == true) {
			canvas.setFocusable(true);
			frame.setFocusable(true);
			canvas.requestFocus();
		}
	}
	/** Toggles the visibility of the current display.
	 * 	If it is visible, it hides it; if hidden, it is now revealed. */
	public void toggleVisible() {
		this.setVisible(!this.getVisible());
	}
	
	
	/* CALCULATORS */
	
	/** @return the size of the current window, in pixels, in {@link Rect} form (centered on the origin) */
	public Rect rectBounds() {
		return new Rect(new CVector(0,0), new CVector(frame.getSize()));
	}
	
}

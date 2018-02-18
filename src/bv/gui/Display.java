package bv.gui;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Frame;

import javax.swing.JFrame;

import bv.gameFramework.core.InputEngine;
import bv.math.CVector;
import bv.math.Rect;

/** A class which consolidates all processes necessary to manage and display a computer window.
 * @author	Brennan Colberg
 * @since	Nov 25, 2017
 */
public class Display {
	
	
	/// VARIABLES ///
	
	private JFrame frame = new JFrame();
	private Canvas canvas;
	
	private CVector size;
	private Input input;

	
	/// CONSTRUCTORS ///
	
	private Display() {
 		
		// initializes and configures frame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
	}
 	public Display(CVector newSize, Canvas newCanvas, Input newInput) {
 		this();
 		this.setSize(newSize);
 		this.setCanvas(newCanvas);
 		this.setInput(newInput);
 	}
 	public Display(CVector newSize, Input newInput) {
 		this(newSize, new Canvas(), newInput);
 	}
 	public Display(CVector newSize) {
 		this(newSize, new Canvas(), new Input());
 	}
 	
	
	/// GETTERS & SETTERS ///
 	
 	public Canvas getCanvas() {
 		return this.canvas;
 	}
 	public void setCanvas(Canvas newCanvas) {
 		this.canvas = newCanvas;
 		frame.add(canvas);
 	}
 	
 	public Frame getFrame() {
 		return this.frame;
 	}
	
 	public CVector getSize() {
 		return this.size;
 	}
	public void setSize(CVector newSize) {
		
		this.size = new CVector(newSize);
		Dimension newFrameDimension = this.size.toDimension();
		
		frame.setMaximumSize(newFrameDimension);
		frame.setMinimumSize(newFrameDimension);
		frame.setPreferredSize(newFrameDimension);
		
	}
	
	public Input getInput() {
		return this.input;
	}
	public void setInput(Input newInput) {
		this.input = newInput;
		input.display = this;
		InputEngine.live = true;
		canvas.addKeyListener(input);
		canvas.addMouseListener(input);
		canvas.addMouseMotionListener(input);
		canvas.addMouseWheelListener(input);
	}
	
	public boolean getVisible() {
		return frame.isVisible() && canvas.isVisible();
	}
	public void setVisible(boolean newVisible) {
		canvas.setVisible(newVisible);
		frame.setVisible(newVisible);
		if (newVisible == true) {
			canvas.setFocusable(true);
			frame.setFocusable(true);
			canvas.requestFocus();
		}
	}
	public void toggleVisible() {
		this.setVisible(!this.getVisible());
	}
	
	
	/// CALCULATORS ///
	
	public Rect rectBounds() {
		return new Rect(new CVector(0,0), new CVector(frame.getSize()));
	}

}

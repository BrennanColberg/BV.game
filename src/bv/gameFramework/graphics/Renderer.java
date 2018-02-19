package bv.gameFramework.graphics;

import java.awt.Color;
import java.awt.Graphics;

import bv.gameFramework.core.Core;
import bv.math.CVector;
import bv.math.Poly;
import bv.math.Rect;

public class Renderer {
	
	
	/* VARIABLES */

	private Graphics graphics = null;
	
	private Color backgroundColor = Color.lightGray;
	
	
	/* GETTERS & SETTERS */
	
	public Rect getDisplayBounds() {
		return Core.renderEngine.getDisplay().rectBounds();
	}
	public CVector getDisplayCenter() {
		return (CVector) Core.renderEngine.getDisplay().getSize().scaledBy(.5);
	}
	public CVector getDisplaySize() {
		return Core.gameStateManager.currentState.getSize();
	}
	
	public Rect getCameraBounds() {
		return Core.gameStateManager.currentState.rectBounds();
	}
	public CVector getCameraPosition() {
		return Core.gameStateManager.currentState.getPosition();
	}
	public CVector getCameraSize() {
		return Core.gameStateManager.currentState.getSize();
	}
	
	public double getPixelsPerUnit() {
		return Core.gameStateManager.currentState.pixelsPerUnit;
	}
	
	
	// graphics
	@Deprecated
	public Graphics getGraphics() {
		return graphics;
	}
	public void setGraphics(Graphics newGraphics) {
		this.graphics = newGraphics;
	}
	
	// drawing color
	public Color getDrawingColor() {
		return graphics.getColor();
	}
	public void setDrawingColor(Color newDrawingColor) {
		if (newDrawingColor == null)
			return;
		else
			graphics.setColor(newDrawingColor);
	}
	
	// background color
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color newBackgroundColor) {
		if (newBackgroundColor == null)
			return;
		else
			this.backgroundColor = newBackgroundColor;
	}
	
	
	/* ADJUSTING */
	
	public boolean inBounds(Renderable renderee) {
//		return getCameraBounds().contains(renderee.rectBounds().getPosition());
		return true;
	}

	// vector
	
	public CVector adjust(CVector point) {
		CVector result = new CVector(point);
		result.subtract(getCameraPosition());
		result.scale(getPixelsPerUnit());
		result.add(getDisplayCenter());
		return result;
	}
	public CVector normalize(CVector point) {
		CVector result = new CVector(point);
		result.subtract(getDisplayCenter());
		result.scale(1d / getPixelsPerUnit());
		result.add(getCameraPosition());
		return result;
	}
	
	// rect
	
	public Rect adjust(Rect rect) {
		Rect result = rect.clone();
		result.setPosition(adjust(rect.getPosition()));
		result.getSize().scale(getPixelsPerUnit());
		return result;
	}
	public Rect normalize(Rect rect) {
		Rect result = rect.clone();
		result.setPosition(normalize(rect.getPosition()));
		result.getSize().scale(1d / getPixelsPerUnit());
		return result;
	}
	
	// poly
	
	public Poly adjust(Poly poly) {
		Poly result = new Poly(poly);
		result.setPosition(adjust(poly.getPosition()));
		result.scale(getPixelsPerUnit());
		return result;
	}
	public Poly normalize(Poly poly) {
		Poly result = new Poly(poly);
		result.setPosition(normalize(poly.getPosition()));
		result.scale(1 / getPixelsPerUnit());
		return result;
	}

	
	/* DRAWING */
	
	// resets background
	public void updateBackground() {
		Core.renderEngine.getDisplay().getCanvas().setBackground(this.getBackgroundColor());
		graphics.setColor(this.getBackgroundColor());
		graphics.fillRect(0, 0, (int) getDisplaySize().getValue(0), (int) getDisplaySize().getValue(1));
	}
	
	// draws outline of given shape
	public void outline(Renderable renderee, Color color) {
		if (inBounds(renderee)) {
			setDrawingColor(color);
			graphics.drawPolygon(adjust(renderee.polyBounds()).toPolygon());
		}
	}	
	
	// fills area of given shape
	public void fill(Renderable renderee, Color color) {
		if (inBounds(renderee)) {
			setDrawingColor(color);
			graphics.fillPolygon(adjust(renderee.polyBounds()).toPolygon());
		}
	}

	// text
//	public void write(String text, Vector position, Color color, double size) {
//		double screenSize = size * getCameraMagnification();
//		Font screenFont = new Font("Impact", Font.BOLD, (int) screenSize);
//		graphics.setFont(screenFont);
//		Vector screenPosition = adjust(position).add(new Vector(0, graphics.getFontMetrics().getHeight()).multiply(0.25)).subtract(new Vector(graphics.getFontMetrics().stringWidth(text), 0).multiply(0.35));
//		writeRaw(text, screenPosition, color, screenSize);
//	}

}

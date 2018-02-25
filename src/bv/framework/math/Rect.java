package bv.framework.math;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;

import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;

/**
 * A class which represents a geometric rectangle, storing two {@link CVector}
 * objects for size and position. Designed for ease of data storage,
 * manipulation, and general use.
 * 
 * @author Brennan Colberg
 * @since Nov 25, 2017
 */
public class Rect implements Renderable {

	/* VARIABLES */

	private CVector position = new CVector();
	private CVector size = new CVector();
	
	
	/* CONSTRUCTORS */

	public Rect(CVector position, CVector size) {
		this.setPosition(position);
		this.setSize(size);
	}

	public Rect(Rect template) {
		this(template.size, template.position);
	}
	public Rect clone() {
		return new Rect(this);
	}

	
	/* GETTERS & SETTERS */

	public CVector getSize() {
		return this.size;
	}
	public void setSize(CVector newSize) {
		this.size = newSize;
	}

	public CVector getPosition() {
		return this.position;
	}
	public void setPosition(CVector newPosition) {
		this.position = newPosition;
	}

	public CVector getRawCorner(double x, double y) {
		return new CVector(size.values[0] * x, size.values[1] * y);
	}
	public CVector getRawCorner(int index) {
		switch (index) {
			case 0: return getRawCorner(1, 1);
			case 1: return getRawCorner(-1, 1);
			case 2: return getRawCorner(-1, -1);
			case 3: return getRawCorner(1, -1);
		}
		return null;
	}
	public CVector getCorner(double x, double y) {
		return getRawCorner(x, y).plus(this.position);
	}
	public CVector getCorner(int index) {
		return getRawCorner(index).plus(this.position);
	}
	
	
	/* CONVERTERS */
	
	public Rectangle toRectangle() {
		CVector position = this.getCorner(-1, -1);
		return new Rectangle((int) position.values[0], (int) position.values[1], (int) size.values[0], (int) size.values[1]);
	}
	
	
	/* CALCULATORS */

	public Rect rectBounds() {
		return this;
	}

	public Poly polyBounds() {
		Poly result = new Poly(getPosition());
		for (int i = 0; i < 4; i++) result.addPoint(getRawCorner(i));
		return result;
	}

	public boolean contains(CVector vector) { // #TODO hay PROBLEMOS para fixar
//		boolean[] within = new boolean[2];
//		for (int i = 0; i < within.length; i++)
//			within[i] = Math.abs(vector.getValue(i) - position.getValue(i)) <= size.getValue(0) * 0.5;
//		return within[0] && within[1];
		
		return this.toRectangle().contains(vector.toPoint()); // don't do this at home; ACTUALLY CALCULATE LATER
	}
	public boolean contains(Point point) {
		return contains(new CVector(point));
	}

	// if NOT inside NO dimensions, IS inside ALL -> TRUE
	public boolean intersects(Rect rect) {
			 if (this.contains(rect.getCorner(0))) return true;
		else if (this.contains(rect.getCorner(1))) return true;
		else if (this.contains(rect.getCorner(2))) return true;
		else if (this.contains(rect.getCorner(3))) return true;
		else if (rect.contains(this.getCorner(0))) return true;
		else if (rect.contains(this.getCorner(1))) return true;
		else if (rect.contains(this.getCorner(2))) return true;
		else if (rect.contains(this.getCorner(3))) return true;
		else 									   return rect.contains(this.position);
		
		//return this.toRectangle().intersects(rect.toRectangle()); // don't do this at home; ACTUALLY CALCULATE LATER
	}

	public boolean encapsulates(Rect rect) {
			 if (!this.contains(rect.getCorner(0))) return false;
		else if (!this.contains(rect.getCorner(1))) return false;
		else if (!this.contains(rect.getCorner(2))) return false;
		else if (!this.contains(rect.getCorner(3))) return false;
		else										return true;
	}

	public boolean intersects(Poly poly) {
		return polyBounds().intersects(poly);
	}

	public boolean encapsulates(Poly poly) {
		return polyBounds().encapsulates(poly);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see bv.gameFramework.v0.graphics.Renderable#render(bv.gameFramework.v0.
	 * graphics.Renderer)
	 */
	@Override
	public void render(Renderer r) {
		r.outline(this, Color.RED);
	}
}

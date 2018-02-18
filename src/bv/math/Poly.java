package bv.math;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;

/** A class which represents a geometric polygon, stored by a list of {@link PVector} vertices.
 * @author	Brennan Colberg
 * @since	Nov 25, 2017
 */
public class Poly implements Renderable {
	
	/* VARIABLES */
	
	private ArrayList<PVector> points = new ArrayList<PVector>();
	private CVector position;
	
	
	/* CONSTRUCTORS */
	
	public Poly(CVector newPosition, List<PVector> list) {
		this.position = newPosition;
		for (PVector point:list) points.add(point);
	}
	public Poly(CVector newPosition, PVector...newPoints) {	
		this(newPosition, Arrays.asList(newPoints));
	}
	
	public Poly(Poly template) {
		this.position = template.position;
		for (PVector point:template.points) points.add(new PVector(point));
	}
	public Poly clone() { return new Poly(this); }
	
	
	/* OPERATIONS */
	
	public void rotate(double in) {
		for (int i = 0; i < points.size(); i++)
			points.get(i).rotate(in);
	}
	public void scale(double in) {
		for (int i = 0; i < points.size(); i++)
			points.get(i).scale(in);
	}
	public void translate(CVector in) {
		this.position.add(in);
	}
	
	public Poly rotatedBy(double in) {
		Poly result = this.clone();
		result.rotate(in);
		return result;
	}
	public Poly scaledBy(double in) {
		Poly result = this.clone();
		result.scale(in);
		return result;
	}
	public Poly translatedBy(CVector in) {
		Poly result = this.clone();
		result.translate(in);
		return result;
	}

	
	/* GETTERS & SETTERS */
	
	public CVector getPosition() {
		return position;
	}
	public void setPosition(CVector newPosition) {
		this.position = newPosition;
	}
	
	public ArrayList<PVector> getPoints() {
		return points;
	}
	public void setPoints(ArrayList<PVector> newPoints) {
		this.points = newPoints;
	}
	public PVector getPoint(int index) {
		return points.get(index);
	}
	public void setPoint(int index, PVector newPoint) {
		points.set(index, newPoint);
	}
	public void setPoint(int index, CVector newPoint) {
		this.setPoint(index, newPoint.toPVector());
	}
	public void addPoint(PVector newPoint) {
		points.add(newPoint);
	}
	public void addPoint(CVector newPoint) {
		this.addPoint(newPoint.toPVector());
	}
	public void addPoint(int index, PVector newPoint) {
		points.add(index, newPoint);
	}
	public void addPoint(int index, CVector newPoint) {
		this.addPoint(index, newPoint.toPVector());
	}
	public void removePoint(int index) {
		points.remove(index);
	}
	public void removePoint(PVector point) {
		points.remove(point);
	}
	
	
	/* CONVERTERS */
	
	public Polygon toPolygon() {
		Polygon result = new Polygon();
		for (int i = 0; i < points.size(); i++) {
			CVector cartPoint = points.get(i).toCVector().plus(this.position);
			result.addPoint((int) cartPoint.getValue(0), (int) cartPoint.getValue(1)); 
		}
		return result;
	}
	
	
	/* CALCULATORS */
	
	public Rect rectBounds() {
		ArrayList<CVector> points = new ArrayList<CVector>();
		for (PVector pv:this.points) points.add(pv.toCVector());
		
		CVector min = new CVector(Double.MAX_VALUE, Double.MAX_VALUE);
		CVector max = new CVector(Double.MIN_VALUE, Double.MIN_VALUE);
		
		for (CVector cv:points)
		for (int i = 0; i < 2; i++) {
			if (cv.values[i] < min.values[i]) min.values[i] = cv.values[i];
			if (cv.values[i] > max.values[i]) max.values[i] = cv.values[i];
		}
		
		return new Rect(this.position, max.minus(min));		
	}
	
	public void render(Renderer r) {
		r.outline(this, Color.BLUE);
	}

	public Poly polyBounds() {
		return this;
	}
	
	public boolean contains(Point value) {
		return this.toPolygon().contains(value);
	}
	public boolean contains(CVector value) {
		return this.toPolygon().contains(value.toPoint());
	}
	
	public boolean intersects(Poly poly) {
		Polygon thisBounds = this.toPolygon();
		for (int i = 0; i < poly.getPoints().size(); i++)
			if (thisBounds.contains(poly.getPoint(i).toPoint()))
				return true;
		Polygon polyBounds = poly.toPolygon();
		for (int i = 0; i < this.getPoints().size(); i++)
			if (polyBounds.contains(this.getPoint(i).toPoint()))
				return true;
		return false;
	}
	public boolean intersects(Rect rect) {
		return this.intersects(rect.polyBounds());
	}
	
	public boolean encapsulates(Poly poly) {
		Polygon thisBounds = this.toPolygon();
		for (int i = 0; i < poly.getPoints().size(); i++)
			if (!thisBounds.contains(poly.getPoint(i).toPoint()))
				return false;
		return true;
	}
	public boolean encapsulates(Rect rect) {
		return this.encapsulates(rect.polyBounds());
	}
}
	
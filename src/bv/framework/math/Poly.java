package bv.framework.math;

import java.awt.Color;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;

/** A class which represents a geometric polygon, stored by a list of {@link PVector} vertices.
 * @author	Brennan Colberg
 * @since	Nov 25, 2017
 */
public class Poly implements Renderable {
	
	/* VARIABLES */
	
	private ArrayList<PVector> points = new ArrayList<PVector>();
	private CVector offset = new CVector(0, 0);
	
	
	/* CONSTRUCTORS */
	
	public Poly(CVector offset, List<PVector> list) {
		this.offset = offset;
		for (PVector point:list) points.add(point);
	}
	public Poly(CVector offset, PVector...newPoints) {	
		this(offset, Arrays.asList(newPoints));
	}
	
	public Poly(Poly template) {
		this.offset = template.offset;
		for (PVector point:template.points) points.add(new PVector(point));
	}
	public Poly() {
		
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
		this.offset.add(in);
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
	
	public CVector getOffset() {
		return offset;
	}
	public void setOffset(CVector offset) {
		this.offset = offset;
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
	public PVector getAdjustedPoint(int index) {
		PVector point = new PVector(points.get(index));
		point.add(offset);
		return point;
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
			CVector cartPoint = points.get(i).toCVector().plus(this.offset);
			result.addPoint((int) cartPoint.getValue(0), (int) cartPoint.getValue(1)); 
		}
		return result;
	}
	public Polygon toAdjustedPolygon() {
		Polygon result = new Polygon();
		for (int i = 0; i < points.size(); i++) {
			CVector cartPoint = points.get(i).toCVector().plus(this.offset);
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
		
		return new Rect(this.offset, max.minus(min));		
	}
	
	public void render(Renderer r) {
		r.outline(this, Color.BLUE);
	}

	public Poly polyBounds() {
		return this;
	}
	
	public boolean contains(Point value) {
		return this.toAdjustedPolygon().contains(value);
	}
	public boolean contains(CVector value) {
		return this.toAdjustedPolygon().contains(value.toPoint());
	}
	
	public boolean intersects(Poly poly) {
		Polygon thisBounds = this.toAdjustedPolygon();
		for (int i = 0; i < poly.getPoints().size(); i++)
			if (thisBounds.contains(poly.getAdjustedPoint(i).toPoint()))
				return true;
		Polygon polyBounds = poly.toAdjustedPolygon();
		for (int i = 0; i < this.getPoints().size(); i++)
			if (polyBounds.contains(this.getAdjustedPoint(i).toPoint()))
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
	
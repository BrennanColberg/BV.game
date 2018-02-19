/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
package bv.math;

import java.awt.Dimension;
import java.awt.Point;

/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
public class CVector {
	
	
	/* VARIABLES */
	
	public double[] values = new double[2];

	
	/* CONSTRUCTORS */
	
	public CVector() {
		//This is where I am just changing something to see if committing will work
	}
	
	public CVector(double...newValues) {
		this.setValues(newValues);
	}
	
	public CVector(CVector template) {
		this(template.values);
	}
	public CVector clone() {
		return new CVector(this);
	}
	
	
	/* OPERATIONS */
	
	// CVector operations
	
	public void add(CVector in) {
		for (int i = 0; i < 2; i++)
			this.values[i] += in.values[i];
	}
	public void subtract(CVector in) {
		for (int i = 0; i < 2; i++)
			this.values[i] -= in.values[i];
	}
	public void scale(double in) {
		for (int i = 0; i < 2; i++)
			this.values[i] *= in;
	}

	public CVector plus(CVector in) {
		CVector result = new CVector(this);
		result.add(in);
		return result;
	}
	public CVector minus(CVector in) {
		CVector result = new CVector(this);
		result.subtract(in);
		return result;
	}
	public CVector scaledBy(double in) {
		CVector result = new CVector(this);
		result.scale(in);
		return result;
	}
	
	// PVector operations
	
	public void add(PVector in) {
		this.add(in.toCVector());
	}
	public void subtract(PVector in) {
		this.subtract(in.toCVector());
	}

	public CVector plus(PVector in) {
		return this.plus(in.toCVector());
	}
	public CVector minus(PVector in) {
		return this.minus(in.toCVector());
	}
	
	
	/* GETTERS & SETTERS */
	
	public double[] getValues() {
		return this.values;
	}
	public void setValues(double...newValues) {
		if (newValues.length > 0) this.values[0] = newValues[0];
		if (newValues.length > 1) this.values[1] = newValues[1];
	}
	public double getValue(int index) {
		return values[index];
	}
	public void setValue(int index, double newValue) {
		this.values[index] = newValue;
	}
	
	
	/* CONVERTERS */
	
	public CVector(PVector template) {
		values[0] = template.magnitude * Math.cos(template.angle);
		values[1] = template.magnitude * Math.sin(template.angle);
	}
	public PVector toPVector() {
		return new PVector(this);
	}
	
	public CVector(Dimension template) {
		this(2, template.height, template.width);
	}
	public Dimension toDimension() {
		return new Dimension((int) this.values[0], (int) this.values[1]);
	}
	
	public CVector(Point template) {
		this(template.x, template.y);
	}
	public Point toPoint() {
		return new Point((int) this.values[0], (int) this.values[1]);
	}
	
	public String toString() {
		return String.format("(%s, %s)", values[0], values[1]);
	}

}

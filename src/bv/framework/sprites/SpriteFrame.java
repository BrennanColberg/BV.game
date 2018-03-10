package bv.framework.sprites;

import java.awt.Color;
import java.util.ArrayList;

import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;

@SuppressWarnings("serial")
public class SpriteFrame extends ArrayList<Poly> implements Cloneable, Renderable {

	public double scale = 1;
	public double heading = 0;
	public Color color = Color.MAGENTA;
	
	public SpriteFrame(Poly...polies) {
		for (Poly p:polies) {
			this.add(p);
		}
	}
	public SpriteFrame(SpriteFrame template) {
		this(template.toArray(new Poly[]{}));
		this.scale = template.scale;
		this.heading = template.heading;
		this.color = template.color;
	}
	public SpriteFrame clone() {
		return new SpriteFrame(this);
	}
	
	public void render(Renderer r, CVector position, double scale, double heading, Color color) {
		for (int i = 0; i < this.size(); i++) {
			Poly poly = this.get(i).rotatedBy(heading).scaledBy(scale);
			poly.setPosition(position);
			r.fill(poly, color);
		}
	}
	public void render(Renderer r, CVector position, double scale, double heading) {
		this.render(r, position, scale * this.scale, heading + this.heading, this.color);
	}
	public void render(Renderer r, CVector position, Color color) {
		this.render(r, position, 1.0, 0.0, color);
	}
	public void render(Renderer r, CVector position) {
		this.render(r, position, 1.0, 0.0, this.color);
	}
	public void render(Renderer r) {
		this.render(r, new CVector(0,0), 1.0, 0.0, this.color);
	}
	
	public void add(Poly...polies) {
		for (Poly p : polies) super.add(p);
	}
	
	public SpriteFrame scale(double factor) {
		this.scale = factor;
		return this;
	}
	public SpriteFrame scaleNew(double factor) {
		return this.clone().scale(factor);
	}
	
	public Rect rectBounds() {
		return this.polyBounds().rectBounds();
	}
	public Poly polyBounds() {
		return this.get(0);
	}
	
}

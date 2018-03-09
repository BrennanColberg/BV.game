package bv.framework.sprites;

import java.awt.Color;
import java.util.ArrayList;

import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;

@SuppressWarnings("serial")
public class SpriteFrame extends ArrayList<Poly> {

	public double scale = 1;
	public double heading = 0;
	public Color color = Color.MAGENTA;
	
	public SpriteFrame(Poly...polies) {
		for (Poly p:polies) {
			this.add(p);
		}
	}
	
	public void render(Renderer r, CVector position, double scale, double heading, Color color) {
		for (int i = 0; i < this.size(); i++) {
			Poly poly = this.get(i).rotatedBy(heading).scaledBy(scale);
			poly.setPosition(position);
			r.fill(poly, color);
		}
	}
	public void render(Renderer r, CVector position, double scale, double heading) {
		this.render(r, position, scale, heading, this.color);
	}
	public void render(Renderer r, CVector position, Color color) {
		this.render(r, position, this.scale, this.heading, color);
	}
	public void render(Renderer r, CVector position) {
		this.render(r, position, this.scale, this.heading, this.color);
	}
	
}

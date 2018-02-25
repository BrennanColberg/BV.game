package bv.gameFramework.spritesCore;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

import bv.gameFramework.graphics.Renderer;
import bv.math.CVector;
import bv.math.Poly;
import bv.syntax.BColor;
import bv.syntax.BV;

public class Sprite extends ArrayList<Poly> {
	private static final long serialVersionUID = -8587874606082995609L;
	
	public static double DEFAULT_SHADE = 100;
	public static boolean DEFAULT_SHOW_HEALTH = true;

	private HashMap<Integer, Double> shades = new HashMap<Integer, Double>();
	private HashMap<Integer, Boolean> showHealth = new HashMap<Integer, Boolean>();
	
	
	/* GETTERS & SETTERS */
	
	public double shade(int index) {
		if (shades.containsKey(index)) { return shades.get(index); }
		else return DEFAULT_SHADE;
	}
	public Sprite shade(int index, double value) {
		this.shades.put(index, value);
		return this;
	}
	
	public boolean showHealth(int index) {
		if (showHealth.containsKey(index)) { return showHealth.get(index); }
		else return DEFAULT_SHOW_HEALTH;
	}
	public Sprite showHealth(int index, boolean value) {
		this.showHealth.put(index, value);
		return this;
	}
	
	
	/* CONSTRUCTORS */
	
	public Sprite() { }
	public Sprite(Sprite sprite) {
		for (int i = 0; i < sprite.size(); i++) {
			add(new Poly(sprite.get(i)));
			shades = sprite.shades;
		}
	}
	
	
	/* METHODS */
	
	public void render(Renderer r, CVector position, double heading, Color color) {
		for (int i = 0; i < size(); i++) {
			Color drawColor = BColor.shade(color, shade(i));
			Poly poly = new Poly(get(i));
			poly.rotate(heading);
			poly.setPosition(position);
			r.fill(poly, drawColor);
		}
	}
	public void render(Renderer r, CVector position, double heading, double health, Color color) {
		for (int i = 0; i < size(); i++) {
			Color drawColor = BColor.shade(color, shade(i));
			Poly poly = new Poly(get(i));
			poly.rotate(heading);
			poly.setPosition(position);
			r.fill(poly, drawColor);
			if (showHealth(i)) r.fill(poly.scaledBy(1 - BV.clamp(health, 0, 1)), drawColor.darker());
		}
	}

	// scale
	
	public Sprite scale(double scale) {
		for (Poly poly : this) poly.scale(scale);
		return this;
	}
	public Sprite scaleNew(double scale) {
		return new Sprite(this).scale(scale);
	}

}
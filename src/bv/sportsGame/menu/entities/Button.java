package bv.sportsGame.menu.entities;

import java.awt.Color;

import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.physics.Entity;
import bv.framework.spritesCore.Sprite;
import bv.math.CVector;
import bv.math.Poly;
import bv.math.Rect;

public abstract class Button extends Entity implements Renderable {
	
	public Sprite sprite;
	public double heading;
	public Color color;
	
	public Button(CVector position, Sprite sprite, double heading, Color color) {
		this.position = position;
		this.sprite = sprite;
		this.heading = heading;
		this.color = color;
	}
	
	public abstract void clicked();
	
	public void render(Renderer r) {
		this.sprite.render(r, position, heading, color);
	}

	public Rect rectBounds() {
		return polyBounds().rectBounds();
	}

	public Poly polyBounds() {
		Poly result = new Poly(this.sprite.get(0).polyBounds());
		result.setPosition(this.position);
		return result;
	}
	
}

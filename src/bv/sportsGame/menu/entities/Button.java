package bv.sportsGame.menu.entities;

import java.awt.Color;

import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Entity;
import bv.framework.sprites.AnimatedSprite;
import bv.framework.sprites.Sprite;

public abstract class Button extends Entity implements Renderable {
	
	public AnimatedSprite sprite;
	public double heading;
	public Color color;
	
	public Button(CVector position, AnimatedSprite sprite, double heading, Color color) {
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
		result.setOffset(this.position);
		return result;
	}
	
}

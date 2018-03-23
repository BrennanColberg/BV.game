package bv.sportsGame.game.entities;

import java.awt.Color;

import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.PVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Collidable;
import bv.framework.physics.Entity;
import bv.framework.sprites.AnimatedSprite;
import bv.sportsGame.game.entities.classes.BasicClass;

public class CharSelection extends Entity implements Renderable, Collidable {

	public AnimatedSprite sprite;
	public BasicClass classType;
	
	public CharSelection(AnimatedSprite sprite, CVector position, BasicClass classType) {
		this.sprite = sprite;
		this.position = position;
		this.classType = classType;
	}
	
	@Override
	public void onCollision(PVector newVelocity, Entity object) {
		//Do nothing
	}

	@Override
	public Poly trigger() {
		return rectBounds().polyBounds();
	}

	@Override
	public void render(Renderer r) {
		sprite.render(r, position, Math.PI/2, Color.white);
	}

	@Override
	public Rect rectBounds() {
		return new Rect(position, new CVector(1, 1));
	}

	@Override
	public Poly polyBounds() {
		return sprite.polyBounds();
	}

}

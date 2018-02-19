package bv.sportsGame.game.entities;

import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;
import bv.gameFramework.physics.Entity;
import bv.gameFramework.spritesCore.RSprite;
import bv.math.Poly;
import bv.math.Rect;
/**
 * An abstract class for all projectiles to extend
 * 
 * @author Aroosh Kumar
 * @since Monday, February 19, 2018
 * .
 */
public abstract class Projectile extends Entity implements Renderable {
	public RSprite projectileSprite;
	
	public Projectile(RSprite sprite, double speed){
		this.projectileSprite = sprite;
		this.velocity.setAngle(sprite.heading);
		this.velocity.setMagnitude(speed);
		this.position = sprite.position;
	}
	public void updatePhysics() {
		super.updatePhysics();
	}
	public void render(Renderer r){
		projectileSprite.render(r);
	}
	@Override
	public Rect rectBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Poly polyBounds() {
		// TODO Auto-generated method stub
		return null;
	}
}

package bv.sportsGame.game.entities;

import bv.framework.core.Core;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.PVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Collidable;
import bv.framework.physics.Entity;
import bv.framework.sprites.AnimatedSprite;
/**
 * An abstract class for all projectiles to extend
 * 
 * @author Aroosh Kumar
 * @since Monday, February 19, 2018
 * .
 */
public abstract class Projectile extends Entity implements Renderable, Collidable {
	
	public AnimatedSprite projectileSprite;
	public Collidable parent;
	public Rect bounds = new Rect(new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0),-Core.STARTING_SCREEN_SIZE.getValue(1)),
			new CVector(Core.STARTING_SCREEN_SIZE.getValue(0),Core.STARTING_SCREEN_SIZE.getValue(1)));
	
	public Projectile(AnimatedSprite sprite, CVector position, double heading, double speed, Collidable parent){
		this.projectileSprite = sprite;
		this.velocity.setMagnitude(speed);
		this.velocity.setAngle(heading);
		this.position = position;
		this.parent = parent;
	}
	public void updatePhysics() {
		if (!Core.gameStateManager.currentState.inBounds(this.position)){
			die();
		}
		super.updatePhysics();
	}
	public void render(Renderer r){
		projectileSprite.render(r, position);
		
	}
	@Override
	public Rect rectBounds() {
		return projectileSprite.rectBounds();
	}

	@Override
	public Poly polyBounds() {
		return projectileSprite.polyBounds();
	}
	@Override
	public void onCollision(PVector newVelocity, Entity object) {
		if ((!(((Collidable)object) == this.parent)) && !(object instanceof Projectile)){
			velocity = new PVector(newVelocity);
			die();
		}
	}
	public void die() {
		Core.state().objects.remove(Core.state().objects.indexOf(this));
	}
}

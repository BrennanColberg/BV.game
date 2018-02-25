package bv.framework.physics;

import bv.framework.math.PVector;
import bv.framework.math.Poly;

/** 
 * This will be used with game objects (projectiles, players, balls) and handled by
 * the scene in order to handle collisions.
 * 
 * @author	Jonah Austin
 * @since	February 20, 2018
 */

public interface Collidable {
	public abstract void onCollision(PVector newVelocity, Entity object);
	public abstract Poly trigger();
}

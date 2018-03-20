package bv.sportsGame.game.entities.projectiles;

import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.physics.Collidable;
import bv.framework.sprites.SpriteIO;
import bv.sportsGame.game.entities.Projectile;
/**
 * A projectile in the form of a missile
 * @author Aroosh Kumar
 * @since Monday, February 19, 2018
 */
public class Missile extends Projectile {
	public Missile(CVector position, double heading, double scale, double speed, Collidable parent) {
		super(SpriteIO.get("missile"), position, heading, speed, parent);
	}
	
	@Override
	public Poly trigger() {
		Poly poly = polyBounds();
		poly.setOffset(position);
		return poly;
	}	

}

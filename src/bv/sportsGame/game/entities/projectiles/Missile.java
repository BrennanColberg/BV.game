package bv.sportsGame.game.entities.projectiles;

import java.awt.Color;

import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.physics.Collidable;
import bv.framework.sprites.RSprite;
import bv.framework.sprites.SpriteIO;
import bv.sportsGame.game.entities.Projectile;
/**
 * A projectile in the form of a missile
 * @author Aroosh Kumar
 * @since Monday, February 19, 2018
 */
public class Missile extends Projectile {
	public Missile(CVector position, double heading, double scale, double speed, Collidable parent) {
		super(new RSprite(SpriteIO.get("Missile"), position, scale, heading, Color.blue ), speed, parent);
		
	}

	@Override
	public Poly trigger() {
		Poly poly = polyBounds();
		poly.setPosition(position);
		return poly;
	}	

}

package bv.sportsGame.game.entities.projectiles;

import java.awt.Color;

import bv.gameFramework.spritesCore.RSprite;
import bv.gameFramework.spritesCore.SpriteIO;
import bv.math.CVector;
import bv.sportsGame.game.entities.Projectile;
/**
 * A projectile in the form of a missile
 * @author Aroosh Kumar
 * @since Monday, February 19, 2018
 */
public class Missile extends Projectile {
	public Missile(CVector position, double heading, double scale, double speed) {
		super(new RSprite(SpriteIO.get("Missile"), position, scale, heading, Color.blue ), speed);
		
	}	

}

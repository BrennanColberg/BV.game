package bv.sportsGame.game.entities;

import bv.gameFramework.spritesCore.SpriteIO;
import bv.math.CVector;

/**
 * This is the Tank class that inherits from the Basic class.
 * 
 * @author Jonah Austin
 * @since Tuesday February 20, 2018
 * @param none
 * 
 */

public class TankClass extends BasicClass {
	public TankClass(CVector pos, int team) {
		position = pos;
		sprite = SpriteIO.get("dualGunner").scaleNew(75);
		health = 200;
		strength = 15;
		shotSpeed = 250;
		shotCountDown = 0;
		mass = 150;
		maxVelocity = 2.25d;
		accelAmount = 0.008d;
	}
	public TankClass(CVector pos, int team, boolean isPlayer){
		this(pos, team);
		this.isPlayer = isPlayer;
	}
}

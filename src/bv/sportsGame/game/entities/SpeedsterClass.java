package bv.sportsGame.game.entities;

import bv.gameFramework.spritesCore.SpriteIO;
import bv.math.CVector;

/**
 * This is the Speedster class that inherits from the Basic class.
 * 
 * @author Jonah Austin
 * @since Tuesday February 20, 2018
 * @param none
 * 
 */

public class SpeedsterClass extends BasicClass {
	public SpeedsterClass(CVector pos) {
		position = pos;
		sprite = SpriteIO.get("swarmV2").scaleNew(50);
		health = 75;
		strength = 2;
		shotSpeed = 5;
		shotCountDown = 0;
		mass = 75;
		maxVelocity = 7.5d;
		accelAmount = 0.04d;
	}
	public SpeedsterClass(CVector pos, boolean isPlayer){
		this(pos);
		this.isPlayer = isPlayer;
	}
}

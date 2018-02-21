package bv.sportsGame.game.entities;

import bv.gameFramework.spritesCore.SpriteIO;

/**
 * This is the Speedster class that inherits from the Basic class.
 * 
 * @author Jonah Austin
 * @since Tuesday February 20, 2018
 * @param none
 * 
 */

public class SpeedsterClass extends BasicClass {
	public SpeedsterClass() {
		sprite = SpriteIO.get("swarmV2").scale(50);
		health = 75;
		strength = 2;
		shotSpeed = 5;
		shotCountDown = 0;
		weight = 75;
		maxVelocity = 7.5d;
		accelAmount = 0.04d;
	}
	public SpeedsterClass(boolean isPlayer){
		this();
		this.isPlayer = isPlayer;
	}
}

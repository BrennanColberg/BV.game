package bv.sportsGame.game.entities;

import bv.gameFramework.spritesCore.SpriteIO;

public class SpeedsterClass extends BasicClass {
	public SpeedsterClass() {
		sprite = SpriteIO.get("swarmV2").scale(50);
		health = 75;
		strength = 2;
		shotSpeed = 5;
		shotCountDown = 0;
		weight = 75;
		maxVelocity = 7.5d;
		accelAmount = 0.02d;
	}
}

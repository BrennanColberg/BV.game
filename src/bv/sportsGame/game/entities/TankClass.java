package bv.sportsGame.game.entities;

import bv.gameFramework.spritesCore.SpriteIO;

public class TankClass extends BasicClass {
	public TankClass() {
		sprite = SpriteIO.get("dualGunner").scale(75);
		health = 200;
		strength = 15;
		shotSpeed = 250;
		shotCountDown = 0;
		weight = 150;
		maxVelocity = 2.25d;
		accelAmount = 0.008d;
	}
}

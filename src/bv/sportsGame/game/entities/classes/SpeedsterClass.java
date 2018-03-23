package bv.sportsGame.game.entities.classes;

import bv.framework.math.CVector;
import bv.framework.sprites.SpriteIO;

/**
 * This is the Speedster class that inherits from the Basic class.
 * 
 * @author Jonah Austin
 * @since Tuesday February 20, 2018
 * @param none
 * 
 */

public class SpeedsterClass extends BasicClass {
	public SpeedsterClass(CVector pos, Team team) {
		position = pos;
		this.team = team;
		sprite = SpriteIO.get("swarmV2").scaleNew(50);
		health = 75;
		strength = 2;
		shotSpeed = 15;
		shotCountDown = 0;
		mass = 75;
		maxVelocity = 6d;
		accelAmount = 0.2d;
		isWASD = false;
	}
	public SpeedsterClass(CVector pos, Team team, boolean isPlayer, boolean isDefending){
		this(pos, team);
		this.isPlayer = isPlayer;
		this.isDefending = isDefending;
	}
}

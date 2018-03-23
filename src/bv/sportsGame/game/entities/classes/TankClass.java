package bv.sportsGame.game.entities.classes;

import bv.framework.math.CVector;
import bv.framework.sprites.SpriteIO;

/**
 * This is the Tank class that inherits from the Basic class.
 * 
 * @author Jonah Austin
 * @since Tuesday February 20, 2018
 * @param none
 * 
 */

public class TankClass extends BasicClass {
	public TankClass(CVector pos, Team team) {
		position = pos;
		this.team = team;
		sprite = SpriteIO.get("dualGunner").scaleNew(75);
		health = 200;
		strength = 15;
		shotSpeed = 250;
		shotCountDown = 0;
		mass = 150;
		maxVelocity = 3.5d;
		accelAmount = 0.12d;
		isWASD = false;
	}
	public TankClass(CVector pos, Team team, boolean isPlayer, boolean isDefending){
		this(pos, team);
		this.isPlayer = isPlayer;
		this.isDefending = isDefending;
	}
}

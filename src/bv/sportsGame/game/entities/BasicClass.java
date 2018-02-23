package bv.sportsGame.game.entities;

import java.awt.Color;
import java.awt.event.KeyEvent;

import bv.gameFramework.core.Core;
import bv.gameFramework.core.Input;
import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;
import bv.gameFramework.physics.Collidable;
import bv.gameFramework.physics.Entity;
import bv.gameFramework.spritesCore.Sprite;
import bv.gameFramework.spritesCore.SpriteIO;
import bv.math.CVector;
import bv.math.PVector;
import bv.math.Poly;
import bv.math.Rect;
import bv.sportsGame.game.entities.projectiles.Missile;

/**
 * This is the Basic class. The other classes (Tank, Speedster) will inherit the 
 * basic functionality from this one.
 * 
 * @author Jonah Austin
 * @since Tuesday February 20, 2018
 * @param none
 * 
 */

public class BasicClass extends Entity implements Renderable, Collidable {

	protected static double dragConst = -0.0001d;
	protected static double recoilConst = -0.1d;
	protected Sprite sprite;
	protected int health;
	protected int strength;
	protected int shotSpeed; //a bit counter-intuitive; high value equals slower shot speed
	protected int shotCountDown;
	protected double maxVelocity;
	protected double accelAmount;
	protected boolean isPlayer;
	
	public BasicClass() {
		sprite = SpriteIO.get("podracer").scale(50);
		health = 125;
		strength = 7;
		shotSpeed = 50;
		shotCountDown = 0;
		mass = 100;
		maxVelocity = 5.0d;
		accelAmount = 0.05d;
		position = new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0),0);
		ifMoveable = true;
	}
	public BasicClass(boolean isPlayer){
		this();
		this.isPlayer = isPlayer;
	}
	
	public double drag() {
		return (mass != 0) ? velocity.getMagnitude() * dragConst * mass : -0.1d;
	}
	
	public double recoil() {
		return (strength != 0) ? recoilConst * strength : -0.1d;
	}
	
	public void updatePhysics() {
		if (isPlayer) playerMovement();
		acceleration.setAngle(velocity.getAngle());
		velocity.clamp(-maxVelocity, maxVelocity);
		super.updatePhysics();
	}
	
	/*I made this a separate method that is called within updatePhysics so that
	 *if we wanted to add bots, then this could be overridden in order to do that.
	 */
	public void playerMovement() {
		CVector target = Input.getMouseAdjustedPosition();
		velocity.setAngle(Math.atan2(target.getValue(1) - position.getValue(1), target.getValue(0) - position.getValue(0)));
		
		if (Input.isKeyPressed(KeyEvent.VK_SPACE) && this.velocity.getMagnitude() < maxVelocity)
			acceleration.addMagnitude(accelAmount);
		else {
			acceleration.addMagnitude(drag());
		}
		
		if (Input.isKeyPressed(KeyEvent.VK_W) && shotCountDown <= 0) {
			Core.gameStateManager.currentState.objects.add(new Missile(this.getPosition(), this.velocity.getAngle(), strength * 2, 10 + this.velocity.getMagnitude())); //size of projectile is equal to its strength
			acceleration.addMagnitude(recoil());
			shotCountDown = shotSpeed;
		}
		
		shotCountDown--;
	}
	
	@Override
	public void render(Renderer r) {
		sprite.render(r, position, this.velocity.getAngle(), Color.black);
	}

	@Override
	public Rect rectBounds() {
		return sprite.get(0).rectBounds();
	}

	@Override
	public Poly polyBounds() {
		return sprite.get(0).polyBounds();
	}
	
	@Override
	public void onCollision(PVector newVelocity, Entity object) {
		velocity = new PVector(newVelocity);
	}
	
	@Override
	public Poly trigger() {
		Poly poly = polyBounds();
		poly.setPosition(position);
		return poly;
	}
}

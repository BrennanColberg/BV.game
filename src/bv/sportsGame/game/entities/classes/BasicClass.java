package bv.sportsGame.game.entities.classes;

import java.awt.Color;
import java.awt.event.KeyEvent;

import bv.framework.core.Core;
import bv.framework.core.Input;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.PVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Collidable;
import bv.framework.physics.Entity;
import bv.framework.sprites.Sprite;
import bv.framework.sprites.SpriteIO;
import bv.framework.syntax.BMath;
import bv.sportsGame.game.entities.projectiles.Missile;

/**
 * This is the Basic class. The other classes (Tank, Speedster) will inherit the 
 * basic functionality from this one.
 * 
 * @author Jonah Austin
 * @since Tuesday February 20, 2018
 * 
 */

public class BasicClass extends Entity implements Renderable, Collidable {

	protected static double dragConst = 0.0005d;
	protected static double recoilConst = 0.15d;
	protected static double velocityCutOff = 0.005d; //This is in an attempt to get rid off the jittering when the player isn't moving
	protected Sprite sprite;
	protected int health;
	protected int strength;
	protected int shotSpeed; //a bit counter-intuitive; high value equals slower shot speed
	protected int shotCountDown;
	public Team team;
	protected double maxVelocity;
	protected double accelAmount;
	protected boolean isPlayer;
	protected boolean isWASD;
	
	public BasicClass() {
		sprite = SpriteIO.get("podracer").scaleNew(50);
		health = 125;
		strength = 7;
		shotSpeed = 50;
		shotCountDown = 0;
		mass = 100;
		maxVelocity = 5.0d;
		accelAmount = 0.15d;
		isWASD = false;
	}
	public BasicClass(CVector pos, int team) {
		position = pos;
		sprite = SpriteIO.get("podracer").scaleNew(50);
		health = 125;
		strength = 7;
		shotSpeed = 50;
		shotCountDown = 0;
		mass = 100;
		maxVelocity = 5.0d;
		accelAmount = 0.15d; //This basically just controls how easily the player is to change direction at this point (WASD controls)
		isWASD = false;
	}
	public BasicClass(CVector pos, int team, boolean isPlayer){
		this(pos, team);
		this.isPlayer = isPlayer;
	}
	
	public Team getTeam() {
		return team;
	}
	
	public PVector drag() {
		return new PVector((mass != 0) ? dragConst * mass : -0.1d, this.velocity.getAngle() + BMath.PI);
	}
	
	public PVector recoil() {
		return new PVector((strength != 0) ? recoilConst * strength : -0.1d, playerAngle() + BMath.PI);
	}
	
	private double mouseAngle() {
		return Math.atan2(Input.getMouseAdjustedPosition().getValue(1) - position.getValue(1), Input.getMouseAdjustedPosition().getValue(0) - position.getValue(0));
	}
	private double playerAngle() {
		return (isPlayer) ? mouseAngle() : this.velocity.getAngle();
	}
	
	public void updatePhysics() {
		if (isPlayer) playerMovement();
		checkDrag();
		velocity.clamp(-maxVelocity, maxVelocity);
		super.updatePhysics();
		shotCountDown--;
	}
	
	/*I made this a separate method that is called within updatePhysics so that
	 *if we wanted to add bots, then this could be overridden in order to do that.
	 */
	public void playerMovement() {
		//Player control between WASD and mouse
		if (isWASD) {
			if (Input.isKeyPressed(KeyEvent.VK_A)) {
				acceleration.add(new CVector(-accelAmount, 0));
			}
			else if (Input.isKeyPressed(KeyEvent.VK_D)) {
				acceleration.add(new CVector(accelAmount, 0));
			}
			if (Input.isKeyPressed(KeyEvent.VK_W)) {
				acceleration.add(new CVector(0, -accelAmount));
			}
			else if (Input.isKeyPressed(KeyEvent.VK_S)) {
				acceleration.add(new CVector(0, accelAmount));
			}
		}
		else {
			if (Input.isKeyPressed(KeyEvent.VK_SPACE) && this.velocity.getMagnitude() < maxVelocity)
				acceleration.add(new PVector(accelAmount, mouseAngle()));
		}
		
		if (Input.isMousePressed() && shotCountDown <= 0) {
			shoot();
		}
	}
	
	//Like shoot, moved to accommodate for implementation of bots in future
	public void checkDrag() {
		if (acceleration.getMagnitude() == 0 && velocity.getMagnitude() > velocityCutOff) {
			acceleration.add(drag());
		}
	}
	
	//Moved in order to accommodate for implementation of bots in future (possibly) as this will be reused
	public void shoot() {
		Core.state().objects.add(new Missile(this.getPosition(), playerAngle(), strength * 2, 10 + this.velocity.getMagnitude(), (Collidable)this)); //size of projectile is equal to its strength
		acceleration.add(recoil());
		shotCountDown = shotSpeed;
	}
	
	@Override
	public void render(Renderer r) {
		sprite.render(r, position, playerAngle(), team != null ? team.color : Color.magenta);
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

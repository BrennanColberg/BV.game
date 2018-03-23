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
import bv.framework.sprites.AnimatedSprite;
import bv.framework.sprites.SpriteIO;
import bv.framework.syntax.BMath;
import bv.framework.syntax.BV;
import bv.sportsGame.game.entities.Ball;
import bv.sportsGame.game.entities.projectiles.Missile;
import bv.sportsGame.gameStates.Game;

/**
 * This is the Basic class. The other classes (Tank, Speedster) will inherit the 
 * basic functionality from this one.
 * 
 * @author Jonah Austin
 * @since Tuesday February 20, 2018
 * 
 */

public class BasicClass extends Entity implements Renderable, Collidable {

	public static BasicClass playerClass = new BasicClass();
	protected static double dragConst = 0.0005d;
	protected static double recoilConst = 0.15d;
	protected static double velocityCutOff = 0.005d; //This is in an attempt to get rid off the jittering when the player isn't moving
	protected AnimatedSprite sprite;
	protected int health;
	protected int strength;
	protected int shotSpeed; //a bit counter-intuitive; high value equals slower shot speed
	protected int shotCountDown;
	protected int specialSpeed; //similar to shotSpeed
	protected int specialCountDown;
	public Team team;
	protected double maxVelocity;
	protected double accelAmount;
	protected boolean isPlayer;
	protected boolean isWASD;
	protected boolean isDefending;
	
	public BasicClass() {
		sprite = SpriteIO.get("podracer").scaleNew(50);
		health = 125;
		strength = 7;
		shotSpeed = 50;
		shotCountDown = 0;
		specialSpeed = 300;
		specialCountDown = 0;
		mass = 100;
		maxVelocity = 5.0d;
		accelAmount = 0.15d;
		isWASD = false;
	}
	public BasicClass(CVector pos, Team team) {
		position = pos;
		this.team = team;
		sprite = SpriteIO.get("podracer").scaleNew(50);
		health = 125;
		strength = 7;
		shotSpeed = 50;
		shotCountDown = 0;
		specialSpeed = 300;
		specialCountDown = 0;
		mass = 100;
		maxVelocity = 5.0d;
		accelAmount = 0.15d; //This basically just controls how easily the player is to change direction at this point (WASD controls)
		isWASD = false;
	}
	public BasicClass(CVector pos, Team team, boolean isPlayer, boolean isDefending){
		this(pos, team);
		this.isPlayer = isPlayer;
		this.isDefending = isDefending;
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

	protected double mouseAngle() {
		return Math.atan2(Input.getMouseAdjustedPosition().getValue(1) - position.getValue(1), Input.getMouseAdjustedPosition().getValue(0) - position.getValue(0));
	}
	protected double playerAngle() {
		return (isPlayer) ? mouseAngle() : this.velocity.getAngle();
	}
	
	//Minor change
	public void updatePhysics() {
		if (isPlayer) playerMovement();
		else aiBehavior();
		checkDrag();
		velocity.clamp(-maxVelocity, maxVelocity);
		super.updatePhysics();
		shotCountDown--;
		specialCountDown--;
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
		
		if (Input.isMousePressed() && shotCountDown <= 0 && Core.state() instanceof Game) {
			shoot();
		}
		
		if (Input.isRightMousePressed() && specialCountDown <= 0 && Core.state() instanceof Game) {
			useSpecial();
		}
	}
	
	//Gets clone of ball from gamestate
	protected Ball findBall() {
		Ball output = null;
		for (Object o : Core.state().objects) {
			if (o instanceof Ball) {
				output = (Ball)o;
			}
		}
		return output;
	}
	
	//Used by AI to move; targets and moves towards the ball
	public void aiBehavior() {
		//Get ball
		Ball ball = findBall();
		
		//Sets the position of the goal according to the opposite x-coordinate and the player's y-coordinate clamped down to just smaller than that of the goal. That way, the bot shoots into the goal and not to the edges where it can miss 
		CVector goalPosBot = new CVector((team == Team.RIGHT) ? -Core.STARTING_SCREEN_SIZE.getValue(0) * 2 : Core.STARTING_SCREEN_SIZE.getValue(0) * 2, BV.clamp(position.getValue(1), -400, 400));
		CVector goalPosBall = new CVector((team == Team.RIGHT) ? Core.STARTING_SCREEN_SIZE.getValue(0) * 2 : -Core.STARTING_SCREEN_SIZE.getValue(0) * 2, BV.clamp(ball.getPosition().getValue(1), -400, 400));
		
		if (!isDefending) {
			move_AttackingBehavior(ball, goalPosBot, goalPosBall);
			shoot_BallBehavior(ball, goalPosBot, goalPosBall);
		}
		else {
			move_DefendingBehavior(ball, goalPosBot, goalPosBall);
			shoot_BallBehavior(ball, goalPosBot, goalPosBall);
		}
	}
	
	//Method estimates the new position of an object (target) if it were to collide with another object going directly towards it at a specific velocity
	protected PVector estimatedPos(Entity target, double velocityMag) {
		//Calculate the estimated position that the ball would be at if the bot moved straight in the direction of the ball
		PVector targetDisp = this.getPosition().minus(target.getPosition()).toPVector(); //Get displacement between the ball and the bot
		double targetTime = targetDisp.getMagnitude() / velocityMag; //Find the time that it would take to reach the ball
		PVector newTargetDisp = target.getVelocity().scaledBy(1/targetTime); //Calculate the displacement of the ball if it were to travel in its current direction for the amount of time that it would take for the bot to reach it (about)
		return target.getPosition().plus(newTargetDisp).toPVector(); //Find the new position of the ball by adding its current position to the displacement of the ball calculated previously
	}
	
	//Behavior for bots where they aggressively attack the ball
	protected void move_AttackingBehavior(Ball ball, CVector goalPosBot, CVector goalPosBall) {
		//Get the estimated position of the ball if the bot were to go straight towards it and collide with it
		PVector estBallPos = estimatedPos(ball, maxVelocity);
		
		//Get the target position for the bot to collide with the ball to get it into the goal
		PVector targetPos = (estBallPos.minus(goalPosBot)).normal().scaledBy(75).plus(ball.getPosition());
		
		//Get the displacement from the targetPos to the currentPos to be able to determine the angle required to point there
		PVector targetDisp = targetPos.minus(this.getPosition());
		this.acceleration.add(new PVector(accelAmount, targetDisp.getAngle()));
	}
	
	//Behavior for bots where they defend the goal
	protected void move_DefendingBehavior(Ball ball, CVector goalPosBot, CVector goalPosBall) {
		CVector ownGoalPos = new CVector(goalPosBall.getValue(0), goalPosBall.getValue(1));
		PVector dispBallToGoal = ball.getPosition().minus(ownGoalPos).toPVector();
		
		if (dispBallToGoal.getMagnitude() >= Core.STARTING_SCREEN_SIZE.getValue(0)*4/3) {
			//Protect the goal
			PVector targetPos = ownGoalPos.plus(dispBallToGoal.scaledBy(0.33)).toPVector();
			PVector dispThisToTarget = targetPos.minus(this.getPosition());
			this.acceleration.add(new PVector(accelAmount, dispThisToTarget.getAngle()));
		}
		else {
			move_AttackingBehavior(ball, goalPosBot, goalPosBall);
		}
	}
	
	//Behavior for bots where they shoot the ball when it would possibly score a goal
	protected void shoot_BallBehavior(Ball ball, CVector goalPosBot, CVector goalPosBall) {
		//Shoots when shot would sink ball into goal
		PVector estBallPos_Shot = estimatedPos(ball, 15);
		PVector posToGoal = goalPosBot.minus(this.getPosition()).toPVector();
		PVector ballToGoal = goalPosBot.minus(estBallPos_Shot).toPVector();
		if (Math.abs(ballToGoal.getAngle() - posToGoal.getAngle()) <= 0.01 && shotCountDown <= 0) {
			shoot(posToGoal.getAngle());
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
		Core.state().objects.add(new Missile(this.getPosition(), playerAngle(), strength * 2, 15, (Collidable)this)); //size of projectile is equal to its strength
		acceleration.add(recoil());
		shotCountDown = shotSpeed;
	}
	
	public void shoot(double angle) {
		Core.state().objects.add(new Missile(this.getPosition(), angle, strength * 2, 15, (Collidable)this)); //size of projectile is equal to its strength
		acceleration.add(recoil());
		shotCountDown = shotSpeed;
	}
	
	public void useSpecial() {
		Core.state().objects.add(new Missile(this.getPosition(), playerAngle(), strength * 8, 15 + this.velocity.getMagnitude(), (Collidable)this)); //size of projectile is equal to its strength
		acceleration.add(recoil().scaledBy(4d));
		specialCountDown = specialSpeed;
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
		if (object instanceof Missile) {
			Missile missile = (Missile)object;
			health -= missile.scale;
		}
	}
	
	@Override
	public Poly trigger() {
		Poly poly = polyBounds().clone(); //poly derived from clone of polyBounds in order to avoid manipulating the actual polyBounds' offset value
		poly.setOffset(position); //Offsets the cloned poly by the position in order to calculate collisions from the player's position and not 0,0
		return poly;
	}
}

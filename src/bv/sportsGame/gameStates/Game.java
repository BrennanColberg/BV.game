/** 
 * @author	Brennan Colberg
 * @since	Jan 17, 2018
 */
package bv.sportsGame.gameStates;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import bv.gameFramework.core.Core;
import bv.gameFramework.core.Input;
import bv.gameFramework.physics.Collidable;
import bv.gameFramework.physics.Entity;
import bv.gameFramework.state.GameState;
import bv.gui.FieldObject;
import bv.math.CVector;
import bv.math.PVector;
import bv.sportsGame.game.entities.Ball;
import bv.sportsGame.game.entities.BasicClass;
import bv.sportsGame.game.entities.PointHighlighter;
import bv.sportsGame.game.entities.projectiles.Missile;

/** 
 * @author	Brennan Colberg
 * @since	Jan 17, 2018
 */
public class Game extends GameState {

	ArrayList<Collidable> collidableObj;
	BasicClass player;
	Ball ball;
	
	public void init() {
		collidableObj = new ArrayList<Collidable>();
		player = new BasicClass(true);
		//player = new TankClass(true);
		//player = new SpeedsterClass(true); //I forgot that this works but bc Speedster inherits from BasicClass (which is the type that this variable was defined as being) this actually works. This is mostly for me bc I had forgotten so leave this in just in case I forget. Sorry. I'll delete this later
		ball = new Ball();
		
		objects.add(new FieldObject());
		objects.add(new PointHighlighter());
		objects.add(player);
		objects.add(ball);
		
		for (Object o : objects) {
			if (o instanceof Collidable) {
				collidableObj.add((Collidable)o);
			}
		}
		
		this.pixelsPerUnit = 0.25;
	}
	
	public void updatePhysics() {
		// Zooms in when the 1 key is pressed and zooms out when the 2 key is pressed
		if (Input.isKeyPressed(KeyEvent.VK_1)){
			if (this.pixelsPerUnit < 1){
				this.pixelsPerUnit += 0.01;
			}
		}
		else if (Input.isKeyPressed(KeyEvent.VK_2)){
			if (this.pixelsPerUnit > 0.25){
				this.pixelsPerUnit -= 0.01;
			}
		}
		velocity.setAngle(Math.atan2(player.getPosition().getValue(1) - position.getValue(1), player.getPosition().getValue(0) - position.getValue(0)));
		
		velocity.setMagnitude(player.getPosition().minus(this.position).toPVector().getMagnitude() / (50 * this.pixelsPerUnit));
		
		// A very hacky way of deleting all projectiles fired. Press backspace to delete the missiles
		if (Input.isKeyPressed(KeyEvent.VK_BACK_SPACE)){
			for (Object o : objects){
				if (o instanceof Missile){
					objects.set(objects.indexOf(o), null);
				}
			}
		}
		
		//TODO: For some reason this line of code locks up all key inputs after some time
//		if (Input.isKeyPressed(KeyEvent.VK_A)){
//			this.velocity.add(new PVector(-10, player.getVelocity().getAngle()));
//		}
		
		checkCollisions();
		
		super.updatePhysics();
	}
	
	private void checkCollisions() {
		//TODO: Collisions between the ball and the player are not being calculated correctly
		for (int i = 0; i < collidableObj.size(); i++) {
			for (int j = i; j < collidableObj.size(); j++) {
				if (i != j && collidableObj.get(i).trigger().intersects(collidableObj.get(j).trigger())) {
					PVector[] velocities = collisionVelocity((Entity)collidableObj.get(i), (Entity)collidableObj.get(j));
					collidableObj.get(i).onCollision(velocities[0], (Entity)collidableObj.get(j));
					collidableObj.get(j).onCollision(velocities[1], (Entity)collidableObj.get(i));
				}
			}
		}
	}
	
	private PVector[] collisionVelocity(Entity object1, Entity object2) {
		//This is the actual math for calculating velocities of objects after a collision. There are quite a few things wrong with this
		//One 'problem' being that the collision angle is calculated as if the two objects were circles
		//In practice this may cause problems or just look weird
		double m1 = object1.mass;
		double m2 = object2.mass;
		//double v1 = BMath.hypot(object1.velocity.toCVector());
		//double v2 = BMath.hypot(object2.velocity.toCVector());
		double v1 = object1.velocity.getMagnitude();
		double v2 = object2.velocity.getMagnitude();
		double theta1 = object1.velocity.getAngle();
		double theta2 = object2.velocity.getAngle();
		double phi = object2.position.minus(object1.position).toPVector().getAngle();

		//Rotate the cartesian plane so that it is a 1D collision problem
		double v1x = v1 * Math.cos(theta1 - phi);
		double v1y = v1 * Math.sin(theta1 - phi);
		
		double v2x = v2 * Math.cos(theta2 - phi);
		double v2y = v2 * Math.sin(theta2 - phi);
		
		//Calculate the new final velocity given the 1D formula:
		//u1x = ((m1 - m2)v1 + 2m2v2)/(m1 + m2)
		double u1x = ((m1 - m2) * v1x + 2 * m2 * v2x) / (m1 + m2);
		double u1y = v1y;
		CVector u1 = new CVector(u1x, u1y);
		PVector pu1 = u1.toPVector();
		pu1.setAngle(pu1.getAngle() + phi);
		
		double u2x = ((m2 - m1) * v2x + 2 * m1 * v1x) / (m2 + m1);
		double u2y = v2y;
		CVector u2 = new CVector(u2x, u2y);
		PVector pu2 = u2.toPVector();
		pu2.setAngle(pu2.getAngle() + phi);
		
		return new PVector[] {pu1, pu2};
	}
	
	public void load() {
		Core.renderEngine.renderer.setBackgroundColor(Color.white);
	}
	
}

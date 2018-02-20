/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
package bv.gameFramework.physics;

import bv.gameFramework.spritesCore.RSprite;
import bv.gameFramework.state.Tickable;
import bv.math.CVector;
import bv.math.PVector;

/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
public class Entity implements Tickable, Physics {
	
	protected CVector position = new CVector(0,0);
	protected PVector velocity = new PVector(0,0);
	protected PVector acceleration = new PVector(0,0);
	public Entity() {}
	public Entity(CVector newPosition) {
		setPosition(newPosition);
	}
	
	/* GETTERS & SETTERS */
	
	public CVector getPosition() {
		return this.position.clone();
	}
	public CVector getRawPosition() {
		return this.position;
	}
	public void setPosition(CVector newPosition) {
		this.position = newPosition.clone();
	}
	public void setRawPosition(CVector newPosition) {
		this.position = newPosition;
	}
	
	public PVector getVelocity() {
		return this.velocity;
	}
	public void setVelocity(PVector newVelocity) {
		this.velocity = newVelocity;
	}
	
	public PVector getAcceleration() {
		return this.acceleration.clone();
	}
	public PVector getRawAcceleration() {
		return this.acceleration;
	}
	public void setAcceleration(PVector newAcceleration) {
		this.acceleration = newAcceleration.clone();
	}
	public void setRawAcceleration(PVector newAcceleration) {
		this.acceleration = newAcceleration;
	}
	public void setAccelerationTarget(CVector location) { 
		PVector directionalVector = location.minus(this.position).toPVector();
		this.acceleration.setAngle(directionalVector.getAngle());
	}
	/* METHODS */
	
	public void updatePhysics() {
		//acceleration.setAngle(velocity.getAngle());
		velocity.add(acceleration);
		position.add(velocity);
		acceleration.clear();
	}
	
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	
}

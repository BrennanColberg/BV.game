/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
package bv.framework.physics;

import bv.framework.core.Core;
import bv.framework.math.CVector;
import bv.framework.math.PVector;
import bv.framework.state.Tickable;

/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
public class Entity implements Tickable, Physics {
	
	// protected static ArrayList<Color> teamColors = new ArrayList<Color>(Arrays.asList(Color.black, Color.magenta, Color.cyan, Color.green, Color.yellow));
	public CVector position = new CVector(0,0);
	public PVector velocity = new PVector(0,0);
	protected PVector acceleration = new PVector(0,0);
	public double mass = 10;
	
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
		velocity.add(acceleration);
		doWallBounce(this.velocity, this.position);
		position.add(velocity);
		acceleration.clear();
	}
	
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	public void doWallBounce(PVector oldVelocity, CVector position){
		CVector cOldVelocity = oldVelocity.toCVector();
		CVector max = Core.gameStateManager.currentState.maxBounds;
		CVector min = Core.gameStateManager.currentState.minBounds;

		if (position.getValue(1) < min.getValue(1) || position.getValue(1) > max.getValue(1))
			this.velocity = (new PVector((new CVector(cOldVelocity.getValue(0), -cOldVelocity.getValue(1)).toPVector().scaledBy(2))));
		else if (position.getValue(0) < min.getValue(0) || position.getValue(0) > max.getValue(0))
			this.velocity = (new PVector((new CVector(-cOldVelocity.getValue(0), cOldVelocity.getValue(1)).toPVector().scaledBy(2))));
	}
}

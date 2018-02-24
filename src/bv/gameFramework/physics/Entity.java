/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
package bv.gameFramework.physics;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;

import bv.gameFramework.state.Tickable;
import bv.math.CVector;
import bv.math.PVector;

/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
public class Entity implements Tickable, Physics {
	
	protected static ArrayList<Color> teamColors = new ArrayList<Color>(Arrays.asList(Color.black, Color.magenta, Color.cyan, Color.green, Color.yellow));
	public CVector position = new CVector(0,0);
	public PVector velocity = new PVector(0,0);
	protected PVector acceleration = new PVector(0,0);
	public double mass = 10;
	
	public Entity() {}
	public Entity(CVector newPosition) {
		setPosition(newPosition);
	}
	
	/* GETTERS & SETTERS */
	
	public static Color getTeamColor(int teamIndex) {
		return teamColors.get(teamIndex + 1); //This is done bc a team of index -1 is supposed to be not really a team. Team's start at index 0 (or index 1 in the color list)
	}
	
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
		position.add(velocity);
		acceleration.clear();
	}
	
	public void tick() {
		// TODO Auto-generated method stub
		
	}
}

package bv.sportsGame.game.entities;

import java.awt.Color;

import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;
import bv.gameFramework.physics.Collidable;
import bv.gameFramework.physics.Entity;
import bv.math.CVector;
import bv.math.PVector;
import bv.math.Poly;
import bv.math.Rect;

public class Ball extends Entity implements Renderable, Collidable {

	private static double dragConst = -0.0001d;
	private int size;
	protected Color ballColor; //Just an option for the future, making the ball the color of the last team to touch it
	
	public Ball() {
		mass = 25;
		size = 35;
	}
	
	public double drag() {
		return (mass != 0) ? velocity.getMagnitude() * dragConst * mass : -0.1d;
	}
	
	public void updatePhysics() {
		//acceleration.addMagnitude(drag());
		acceleration.setAngle(velocity.getAngle());
		super.updatePhysics();
	}
	
	@Override
	public void render(Renderer r) {
		r.fill(this.rectBounds(), Color.white);
	}

	@Override
	public Rect rectBounds() {
		return new Rect(this.position, new CVector(size, size));
	}

	@Override
	public Poly polyBounds() {
		return this.rectBounds().polyBounds();
	}

	@Override
	public void onCollision(PVector newVelocity, Entity object) {
		//Set the final velocity of the object
		//velocity = new PVector(newVelocity);
		
		//System.out.println("x: " + this.rectBounds().getPosition() + " c1: " + this.rectBounds().getCorner(0));
		//System.out.println("Collision with " + object);
	}
	
	@Override
	public Rect trigger() {
		return rectBounds();
	}
}

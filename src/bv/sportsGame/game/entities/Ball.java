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

	private static double dragConst = -0.00001d;
	public int size;
	protected Color ballColor; //Just an option for the future, making the ball the color of the last team to touch it
	
	public Ball() {
		mass = 25;
		size = 50;
	}
	
	public double drag() {
		return (mass != 0) ? velocity.getMagnitude() * dragConst * mass : -0.1d;
	}
	
	public void updatePhysics() {
		polyBounds().setWorldPos(position);
		acceleration.addMagnitude(drag());
		acceleration.setAngle(velocity.getAngle());
		super.updatePhysics();
	}
	
	@Override
	public void render(Renderer r) {
		r.fill(this.rectBounds(), Color.white);
	}

	@Override
	public Rect rectBounds() {
		return new Rect(this.position, new CVector(size / 2, size / 2));
	}

	@Override
	public Poly polyBounds() {
		return this.rectBounds().polyBounds();
	}

	@Override
	public void onCollision(PVector newVelocity, Entity object) {
		//velocity = new PVector(newVelocity);
		System.out.println("Collision with " + object); //To test that collisions actually happen between the ball and the player
	}
	
	@Override
	public Poly trigger() {
		return polyBounds();
	}
}

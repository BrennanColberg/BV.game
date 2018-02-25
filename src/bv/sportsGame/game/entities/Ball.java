package bv.sportsGame.game.entities;

import java.awt.Color;

import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.PVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Collidable;
import bv.framework.physics.Entity;
import bv.sportsGame.game.entities.classes.BasicClass;
import bv.sportsGame.game.entities.classes.Team;

public class Ball extends Entity implements Renderable, Collidable {

	private static double dragConst = -0.0007d;
	public int size;
	protected double maxVelocity;
	protected Team teamLastHit;
	protected Color ballColor; //Just an option for the future, making the ball the color of the last team to touch it
	
	public Ball() {
		mass = 10;
		size = 100;
		maxVelocity = 10.0d;
	}
	
	public double drag() {
		return (mass != 0) ? velocity.getMagnitude() * dragConst * mass : -0.1d;
	}
	
	public void updatePhysics() {
		acceleration.addMagnitude(drag());
		acceleration.setAngle(velocity.getAngle());
		velocity.clamp(-maxVelocity, maxVelocity);
		super.updatePhysics();
	}
	
	//This is to be used when the ball was scored by a team and needs to be reset
	public void reset() {
		position.clear();
		velocity.clear();
		acceleration.clear();
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
		if (object instanceof BasicClass) {
			teamLastHit = ((BasicClass)object).getTeam();
		}
		
		if (object instanceof Goal) {
			reset();
		}
		else {
			velocity = new PVector(newVelocity);
		}
	}
	
	@Override
	public Poly trigger() {
		Poly poly = polyBounds();
		poly.setPosition(position);
		return poly;
	}
}

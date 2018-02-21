package bv.sportsGame.game.entities;

import java.awt.Color;

import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;
import bv.gameFramework.physics.Collidable;
import bv.gameFramework.physics.Entity;
import bv.gameFramework.spritesCore.Sprite;
import bv.gameFramework.spritesCore.SpriteIO;
import bv.math.CVector;
import bv.math.Poly;
import bv.math.Rect;

public class Ball extends Entity implements Renderable, Collidable {

	private static double dragConst = -0.0001d;
	private int size;
	private Color ballColor; //Just an option for the future, making the ball the color of the last team to touch it
	
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
		return new Rect(this.position, new CVector(size,size));
	}

	@Override
	public Poly polyBounds() {
		//return sprite.get(0).polyBounds();
		return null;
	}

	@Override
	public void onCollision(Entity object) {
		//Stuff will happen. This'll get interesting
	}
}

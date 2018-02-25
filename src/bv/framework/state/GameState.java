/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
package bv.framework.state;

import java.util.ArrayList;

import bv.framework.core.Core;
import bv.framework.core.GameStateManager;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.PVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Collidable;
import bv.framework.physics.Entity;
import bv.framework.physics.Physics;
import bv.framework.syntax.BMath;

/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
public abstract class GameState extends Entity implements Renderable, Tickable {
	
	/* VARIABLES */
	
	public double pixelsPerUnit = 1;
	public ArrayList<Object> objects = new ArrayList<Object>();
	public CVector maxBounds = new CVector(Core.STARTING_SCREEN_SIZE.getValue(0) * 2, Core.STARTING_SCREEN_SIZE.getValue(1) * 2);
	public CVector minBounds = new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0) * 2, -Core.STARTING_SCREEN_SIZE.getValue(1) * 2);
	/* CONSTRUCTORS */
	
	public GameState() {
		pixelsPerUnit = 1;
		init();
	}
	
	@Deprecated
	public GameState(double pixelsPerUnit) {
		this.pixelsPerUnit = pixelsPerUnit;
		init();
	}
	
	/** Runs when this {@link GameState} is first created; use this rather than overriding the constructors
	 * 
	 */
	public abstract void init();

	
	/* METHODS */
	
	public void tick() {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Tickable) ((Tickable) objects.get(i)).tick();
		}
	}
	
	public void updatePhysics() {
		super.updatePhysics();
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Physics) ((Physics) objects.get(i)).updatePhysics();
		}
	}

	public void render(Renderer r) {
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Renderable) ((Renderable) objects.get(i)).render(r);

		}
	}
	
	public void calculateCollisions() {
		for (int i = 0; i < objects.size(); i++) if (objects.get(i) instanceof Collidable) {
			Collidable objectI = (Collidable) objects.get(i);
			for (int j = i; j < objects.size(); j++) if (objects.get(j) instanceof Collidable) {
				Collidable objectJ = (Collidable) objects.get(j);
				if (objectI != objectJ) { 
					if (objectI.trigger().intersects(objectJ.trigger())) {
						PVector[] velocities = BMath.collisionVelocity((Entity)objectI, (Entity)objectJ);
						objectI.onCollision(velocities[0], (Entity)objectJ);
						objectJ.onCollision(velocities[1], (Entity)objectI);
					}
				}
			}
		}
	}
	
	/** Runs every time this GameState is newly called into the {@link GameStateManager}'s focus.
	 * 
	 * @return nada
	 * @author	Brennan Colberg
	 * @since	Jan 17, 2018
	 */
	public abstract void load();
	
	/* GETTERS & SETTERS */
	
	public CVector getSize() {
		return (CVector) Core.renderEngine.getDisplay().getSize().scaledBy(1/pixelsPerUnit);
	}
	/* TECHNICAL METHODS */
	
	public Rect rectBounds() {
		Rect result = Core.renderEngine.getDisplay().rectBounds();
		result.setPosition(getPosition());
		result.getSize().scale(1/pixelsPerUnit);
		return result;
	}
	
	public Poly polyBounds() {
		return rectBounds().polyBounds();
	}	
	public boolean inBounds(CVector position) {
		
		return ((position.getValue(0) > minBounds.getValue(0)) &&
				(position.getValue(1) > minBounds.getValue(1)) &&
				(position.getValue(0) < maxBounds.getValue(0)) &&
				(position.getValue(1) < maxBounds.getValue(1)));
	}
}

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
	
	/** Used as a scaling factor; increase to zoom out, decrease to zoom in. */
	public double pixelsPerUnit = 1;
	
	/** This {@link ArrayList} of {@link Object}s is used to store ALL relevant objects to a single GameState.
	 * Any object should be placed here, and will automatically be dealt with:
	 *  {@link Tickable}s will be ticked,
	 *  {@link Renderable}s will be rendered,
	 *  and {@link Physics} objects will be updatePhysicsed. */
	public ArrayList<Object> objects = new ArrayList<Object>();

	public CVector maxBounds = new CVector(Core.STARTING_SCREEN_SIZE.getValue(0) * 2, Core.STARTING_SCREEN_SIZE.getValue(1) * 2);
	public CVector minBounds = new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0) * 2, -Core.STARTING_SCREEN_SIZE.getValue(1) * 2);
	
	
	/* CONSTRUCTORS */
	
	/** Default constructor; simply sets magnification to normal (1 PPU) and calls init(). DO NOT OVERRIDE; instead, put custom code into init() */
	public GameState() {
		this(1);
	}
	
	/** Custom zoom constructor; sets magnification to what you would like (see {@link GameState.pixelsPerUnit}. DO NOT OVERRIDE; instead, put custom code into init()
	 * @param pixelsPerUnit is the custom zoom you would like. */
	public GameState(double pixelsPerUnit) {
		this.pixelsPerUnit = pixelsPerUnit;
		init();
	}
	
	/** Runs when this {@link GameState} is first created; use this for custom code rather than overriding any constructors. 
	 * Completely empty by default; blank canvas for your enjoyment! */
	public abstract void init();

	
	/* METHODS */
	
	/** Passes on the tick() command to ALL {@link Tickable} objects in its objects list. Automatic. */
	public final void tickObjects() {
		this.tick();
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Tickable) ((Tickable) objects.get(i)).tick();
		}
	}
	
	/** Passes on the updatePhysics() command to ALL {@link Physics}-implementing objects in its objects list. Automatic. */
	public final void updateObjectPhysics() {
		this.updatePhysics();
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Physics) ((Physics) objects.get(i)).updatePhysics();
		}
	}

	/** Passes on the render(r) command to ALL {@link Renderable} objects in its objects list. Automatic. */
	public final void renderObjects(Renderer r) {
		this.render(r);
		for (int i = 0; i < objects.size(); i++) {
			if (objects.get(i) instanceof Renderable) ((Renderable) objects.get(i)).render(r);
		}
	}
	public void render(Renderer r) { }
	
	/** Fun math stuff to calculate collisions between two {@link Collidable} objects!
	 * Automatically considers all {@link Collidable} objects, and if any are overlapping calls each's onCollision method. */
	public final void calculateCollisions() {
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
	 * Completely empty, blank canvas for your enjoyment. */
	public abstract void load();
	
	
	/* GETTERS & SETTERS */
	
	/** @return the size of the currently rendered screen, in {@link CVector} form */
	public CVector getSize() {
		return (CVector) Core.renderEngine.getDisplay().getSize().scaledBy(1/pixelsPerUnit);
	}
	
	
	/* TECHNICAL METHODS */
	
	/** @return the currently rendered field, accurate in size and position, in {@link Rect} form */
	public Rect rectBounds() {
		Rect result = Core.renderEngine.getDisplay().rectBounds();
		result.setPosition(getPosition());
		result.getSize().scale(1/pixelsPerUnit);
		return result;
	}
	
	/** @return the currently rendered field, accurate in size and position, in {@link Poly} form of a {@link Rect} */
	public Poly polyBounds() {
		return rectBounds().polyBounds();
	}	
	
	/** @param point the point to test, in {@link CVector} form
	 * 	@return whether or not a given point (in {@link CVector} form) is within the currently rendered screen */
	public boolean inBounds(CVector point) {
		
		return ((point.getValue(0) > minBounds.getValue(0)) &&
				(point.getValue(1) > minBounds.getValue(1)) &&
				(point.getValue(0) < maxBounds.getValue(0)) &&
				(point.getValue(1) < maxBounds.getValue(1)));
	}
}

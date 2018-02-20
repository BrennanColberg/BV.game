/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
package bv.gameFramework.state;

import java.util.ArrayList;

import bv.gameFramework.core.Core;
import bv.gameFramework.core.GameStateManager;
import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;
import bv.gameFramework.physics.Entity;
import bv.gameFramework.physics.Physics;
import bv.math.CVector;
import bv.math.Poly;
import bv.math.Rect;

/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
public abstract class GameState extends Entity implements Renderable, Tickable {
	
	/* VARIABLES */
	
	public double pixelsPerUnit = 1;
	
	public ArrayList<Object> objects = new ArrayList<Object>();

	
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
	
}

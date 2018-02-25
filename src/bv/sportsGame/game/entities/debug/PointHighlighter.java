/** 
 * @author	Brennan Colberg
 * @since	Feb 18, 2018
 */
package bv.sportsGame.game.entities.debug;

import java.awt.Color;

import bv.framework.core.Input;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.physics.Entity;
import bv.math.CVector;
import bv.math.Poly;
import bv.math.Rect;

/** 
 * @author	Brennan Colberg
 * @since	Feb 18, 2018
 */
public class PointHighlighter extends Entity implements Renderable {
	
	public void tick() {
		this.setPosition(Input.getMouseAdjustedPosition());
	}
	
	public void render(Renderer r) {
		r.fill(this.rectBounds(), Color.red);
	}

	public Rect rectBounds() {
		return new Rect(this.position, new CVector(10,10));
	}

	/* (non-Javadoc)
	 * @see bv.gameFramework.v0.graphics.Renderable#polyBounds()
	 */
	@Override
	public Poly polyBounds() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

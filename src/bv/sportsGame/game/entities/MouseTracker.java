/** 
 * @author	Brennan Colberg
 * @since	Feb 18, 2018
 */
package bv.sportsGame.game.entities;

import java.awt.Color;

import bv.gameFramework.core.Core;
import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;
import bv.gameFramework.physics.Entity;
import bv.gameFramework.spritesCore.Sprite;
import bv.gameFramework.spritesCore.SpriteIO;
import bv.math.CVector;
import bv.math.Poly;
import bv.math.Rect;

/** 
 * @author	Brennan Colberg
 * @since	Feb 18, 2018 
 */
public class MouseTracker extends Entity implements Renderable {

	
	public MouseTracker() {
		super();
		getVelocity().setMagnitude(1);
		
//		getAcceleration().setMagnitude(0.1);
	}
	
	public void updatePhysics() {
		CVector targetLocation = Core.input.getMouseAdjustedPosition();
		velocity.setAngle(Math.atan2(targetLocation.getValue(1) - position.getValue(1), targetLocation.getValue(0) - position.getValue(0)));
		velocity.setMagnitude(Core.input.getMouseAdjustedPosition().minus(this.position).toPVector().getMagnitude() / 100);
		super.updatePhysics();
	}

	Sprite sprite = SpriteIO.get("drone").scale(25);
	public void render(Renderer r) {
		sprite.render(r, this.position, this.velocity.getAngle(), Color.black);
	}

	/* (non-Javadoc)
	 * @see bv.gameFramework.v0.graphics.Renderable#rectBounds()
	 */
	@Override
	public Rect rectBounds() {
		return new Rect(this.position, new CVector(30,10));
	}

	/* (non-Javadoc)
	 * @see bv.gameFramework.v0.graphics.Renderable#polyBounds()
	 */
	@Override
	public Poly polyBounds() {
		return rectBounds().polyBounds();
	}
}

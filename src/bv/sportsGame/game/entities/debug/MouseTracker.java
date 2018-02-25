/** 
 * @author	Brennan Colberg
 * @since	Feb 18, 2018
 */
package bv.sportsGame.game.entities.debug;

import java.awt.Color;
import java.awt.event.KeyEvent;

import bv.framework.core.Input;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Entity;
import bv.framework.sprites.Sprite;
import bv.framework.sprites.SpriteIO;

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
		CVector targetLocation = Input.getMouseAdjustedPosition();
		velocity.setAngle(Math.atan2(targetLocation.getValue(1) - position.getValue(1), targetLocation.getValue(0) - position.getValue(0)));
		
		
		if (Input.isKeyPressed(KeyEvent.VK_SPACE)) velocity.setMagnitude(Input.getMouseAdjustedPosition().minus(this.position).toPVector().getMagnitude() / 100);
		
		
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

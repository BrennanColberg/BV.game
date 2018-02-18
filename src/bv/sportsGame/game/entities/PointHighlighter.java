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
public class PointHighlighter extends Entity implements Renderable {
	//Sprite: Podracer
	Sprite sprite = SpriteIO.get("podracer").scale(40);
	
	public void tick() {
		this.setPosition(Core.input.getMouseAdjustedPosition());
	}
	
	public void render(Renderer r) {
		sprite.render(r, position, this.velocity.getAngle(), Color.RED);
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

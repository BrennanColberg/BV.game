/** 
 * @author	Brennan Colberg
 * @since	Jan 20, 2018
 */
package bv.gameFramework.spritesCore;

import java.awt.Color;

import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;
import bv.math.CVector;
import bv.math.Poly;
import bv.math.Rect;

/** 
 * @author	Brennan Colberg
 * @since	Jan 20, 2018
 */
public class RSprite implements Renderable {

	public Sprite sprite;
	public Color color;
	public double scale, heading;
	public CVector position;
	
	public RSprite(Sprite newSprite, CVector newPosition, double newScale, double newHeading, Color newColor) {
		this.sprite = newSprite;
		this.color = newColor;
		this.scale = newScale;
				this.heading = newHeading;
		this.position = newPosition;
	}
	
	/* (non-Javadoc)
	 * @see bv.gameFramework.v0.graphics.Renderable#render(bv.gameFramework.v0.graphics.Renderer)
	 */
	@Override
	public void render(Renderer r) {
		// TODO Auto-generated method stub
		sprite.scaleNew(scale).render(r, position, heading, color);
	}
	/* (non-Javadoc)
	 * @see bv.gameFramework.v0.graphics.Renderable#rectBounds()
	 */
	@Override
	public Rect rectBounds() {
		// TODO Auto-generated method stub
		return sprite.get(0).rectBounds();
	}
	/* (non-Javadoc)
	 * @see bv.gameFramework.v0.graphics.Renderable#polyBounds()
	 */
	@Override
	public Poly polyBounds() {
		// TODO Auto-generated method stub
		return sprite.get(0);
	}

}

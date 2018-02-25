/** 
 * @author	Brennan Colberg
 * @since	Jan 20, 2018
 */
package bv.framework.sprites;

import java.awt.Color;

import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;

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
	
	public Rect rectBounds() {
		return sprite.get(0).rectBounds();
	}
	public Poly polyBounds() {
		return sprite.get(0);
	}

}

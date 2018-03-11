package bv.framework.graphics;

import bv.framework.math.Poly;
import bv.framework.math.Rect;

/** An interface used to designate that a given {@link Object} is able to be rendered. Requires certain returns that are necessary for said rendering.
 * @author Brennan D. Colberg - {@link http://github.com/BrennanColberg} */
public interface Renderable {
	
	/** Called to render an object, either automatically or manually within the render() method of another object.
	 * 	Generally, other acutally functional methods are called here; simply organized and ordered to create a desired effect.
	 * @param r the {@link Renderer} object relevant to the current display, with which rendering is done */
	public void render(Renderer r);

	/** Used for procuring boundaries with which to calculate collision detection.
	 * @return the smallest {@link Rect} that encloses every point of this {@link Renderable} object's visible area */
	public Rect rectBounds();

	/** Used for procuring {@link Poly} objects for simple and efficient drawing to the {@link Display}'s {@link Canvas}.
	 * @return the {@link Poly} that describes the visible detail of this {@link Renderable} object
	 */
	public Poly polyBounds();
	
}

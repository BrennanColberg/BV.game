package bv.framework.graphics;

import bv.math.Poly;
import bv.math.Rect;

public interface Renderable {
	public void render(Renderer r);

	public Rect rectBounds();

	public Poly polyBounds();
}

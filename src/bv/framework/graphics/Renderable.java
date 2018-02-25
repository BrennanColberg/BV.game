package bv.framework.graphics;

import bv.framework.math.Poly;
import bv.framework.math.Rect;

public interface Renderable {
	public void render(Renderer r);

	public Rect rectBounds();

	public Poly polyBounds();
}

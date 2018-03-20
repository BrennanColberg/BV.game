package bv.framework.sprites.text;

import java.awt.Color;

import bv.framework.core.Core;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Entity;
import bv.framework.sprites.TextSprite;

public class RText extends Entity implements Renderable {

	String text;
	public RText(String text) {
		this.text = text;
	}
	
	public void render(Renderer r) {
		TextSprite.spriteFromString(text).render(r, Core.state().rectBounds().getCorner(1, 0), 50, Math.PI/4, Color.black);
	}

	public Rect rectBounds() {
		return null;
	}

	public Poly polyBounds() {
		return null;
	}

}

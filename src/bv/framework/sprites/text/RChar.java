package bv.framework.sprites.text;

import java.awt.Color;

import bv.framework.core.Core;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Entity;
import bv.framework.sprites.TextSprite;

public class RChar extends Entity implements Renderable {
	
	char ch;
	
	public RChar(char ch) {
		this.ch = ch;
		this.position = new CVector(0,0);
	}
	
	public void render(Renderer r) {
		TextSprite.spriteFromCharacter(ch).render(r, Core.state().rectBounds().getCorner(1, 0), 25,0, Color.black);
	}

	public Rect rectBounds() {
		return null;
	}

	public Poly polyBounds() {
		return null;
	}

}

package bv.sportsGame.game.gui;

import bv.framework.core.Core;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;

public class Audience implements Renderable {
	
	@Override
	public void render(Renderer r) {
		//new AudienceObject(new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0)/4, -Core.STARTING_SCREEN_SIZE.getValue(1)/2), 0).render(r);;
	}

	@Override
	public Rect rectBounds() {
		return null;
	}

	@Override
	public Poly polyBounds() {
		return null;
	}

}

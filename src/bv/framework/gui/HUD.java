package bv.framework.gui;

import bv.framework.core.Core;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;

public class HUD implements Renderable {

	private GameTimer gameTimer = new GameTimer(new CVector(0, -Core.STARTING_SCREEN_SIZE.getValue(1) * 2 + 70), 300);
	
	@Override
	public void render(Renderer r) {
		gameTimer.renderDigits(r);
	}

	@Override
	public Rect rectBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Poly polyBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}

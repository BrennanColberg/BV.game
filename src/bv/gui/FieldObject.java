package bv.gui;

import java.awt.Color;

import bv.gameFramework.core.Core;
import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;
import bv.gameFramework.spritesCore.Sprite;
import bv.gameFramework.spritesCore.SpriteIO;
import bv.math.CVector;
import bv.math.Poly;
import bv.math.Rect;

public class FieldObject implements Renderable{
	//This sprite is the box around the goal.
	Sprite goalArea = SpriteIO.get("square").scale(300);
	Sprite center = SpriteIO.get("center").scale(50);
	
	@Override
	public void render(Renderer r) {
		// renders the box around the goal, and the center
		goalArea.render(r, new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0)/2 * 4,0), 0.0, Color.white);
		goalArea.render(r, new CVector(Core.STARTING_SCREEN_SIZE.getValue(0)/ 2 * 4,0), Math.PI, Color.white);
		center.render(r, new CVector(0,0), Math.PI/4, Color.white);
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

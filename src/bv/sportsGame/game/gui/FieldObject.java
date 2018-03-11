package bv.sportsGame.game.gui;

import java.awt.Color;

import bv.framework.core.Core;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.sprites.AnimatedSprite;
import bv.framework.sprites.SpriteIO;

public class FieldObject implements Renderable{
	//This sprite is the box around the goal.
	AnimatedSprite goalArea = SpriteIO.get("square").scale(300);
	AnimatedSprite center = SpriteIO.get("center").scale(50);
	Color fieldColor = new Color(46, 163, 31);
	AnimatedSprite gamefield = SpriteIO.get("gamefield").scale(128);
	
	@Override
	public void render(Renderer r) {
		// renders the box around the goal, and the center
		gamefield.render(r, new CVector(0,0), 0.0, fieldColor);
		goalArea.render(r, new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0) * 2,0), 0.0, Color.white);
		goalArea.render(r, new CVector(Core.STARTING_SCREEN_SIZE.getValue(0)* 2,0), Math.PI, Color.white);
		center.render(r, new CVector(0,0), Math.PI/4, Color.white);
	}
	public void init() {
		
	}

	@Override
	public Rect rectBounds() {
		return new Rect(new CVector(0,0), Core.STARTING_SCREEN_SIZE);
	}

	@Override
	public Poly polyBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}

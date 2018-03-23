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

public class AudienceObject implements Renderable {

	CVector position;
	double audienceRotation;
	AnimatedSprite audienceOne;
	AnimatedSprite audienceTwo;
	AnimatedSprite audienceThree;
	AnimatedSprite audienceFour;
	AnimatedSprite stand;
	
	public AudienceObject(CVector position, double audienceRotation) {
		this.position = position;
		this.audienceRotation = audienceRotation;
		audienceOne = SpriteIO.get("audienceOne").scaleNew(Core.STARTING_SCREEN_SIZE.getValue(0)/2);
		audienceTwo = SpriteIO.get("audienceTwo").scaleNew(Core.STARTING_SCREEN_SIZE.getValue(0)/2);
		audienceThree = SpriteIO.get("audienceThree").scaleNew(Core.STARTING_SCREEN_SIZE.getValue(0)/2);
		audienceFour = SpriteIO.get("audienceFour").scaleNew(Core.STARTING_SCREEN_SIZE.getValue(0)/2);
		stand = SpriteIO.get("stand").scaleNew(Core.STARTING_SCREEN_SIZE.getValue(0)/2);
	}
	
	@Override
	public void render(Renderer r) {
		audienceOne.render(r, position, audienceRotation * Math.PI/2, Color.red);
		audienceTwo.render(r, position, audienceRotation * Math.PI/2, Color.blue);
		audienceThree.render(r, position, audienceRotation * Math.PI/2, Color.green);
		audienceFour.render(r, position, audienceRotation * Math.PI/2, Color.yellow);
		stand.render(r, position, 0, Color.gray);
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

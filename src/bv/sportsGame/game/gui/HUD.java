package bv.sportsGame.game.gui;

import java.awt.Color;

import bv.framework.core.Core;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Entity;
import bv.framework.sprites.Sprite;
import bv.framework.sprites.TextSprite;
import bv.framework.syntax.BColor;
import bv.sportsGame.game.entities.classes.Team;
import bv.sportsGame.gameStates.Game;

public class HUD extends Entity implements Renderable {

	/* VARIABLES */
	private final CVector SCORE_BACK_SIZE = new CVector(125, 17.5);
	private final CVector TIMER_BACK_SIZE = new CVector(75, 25);
	private static final double TIMER_TEXT_SIZE = 30, SCORE_TEXT_SIZE = 20;
	
	public void render(Renderer r) {
		
		Rect window = Core.state().rectBounds();
		double zoomFactor = Core.state().zoomFactor;
		this.position = window.getCorner(0,-.5*  0.90  ); // 90% of the way up the screen, in the middle
		
		Rect scoreBack = new Rect(position, SCORE_BACK_SIZE.scaledBy(1/zoomFactor));
		Rect timerBack = new Rect(position, TIMER_BACK_SIZE.scaledBy(1/zoomFactor));
		
		r.fill(scoreBack.rectBounds(), Color.black);
		r.fill(timerBack.rectBounds(), Color.darkGray);
		
		renderTimer(r);
		renderScore(r);
	}
	
	private void renderTimer(Renderer r) {
		Sprite secondsLeft = TextSprite.spriteFromString("" + Math.round(Game.gameTimer.secondsLeft));
		secondsLeft.render(r, position.plus(new CVector(0, 0)), TIMER_TEXT_SIZE / Core.state().zoomFactor, 0, Color.WHITE);
	}
	
	private void renderScore(Renderer r) {
		
		double zoomFactor = Core.state().zoomFactor;
		
		Sprite rightScore = TextSprite.spriteFromString("" + Team.RIGHT.score);
		Sprite leftScore = TextSprite.spriteFromString("" + Team.LEFT.score);
		
		rightScore.render(r, position.plus(new CVector(100, 0).scaledBy(1/zoomFactor)), SCORE_TEXT_SIZE / zoomFactor, 0, BColor.shade(Team.RIGHT.color, 200));
		leftScore.render(r, position.plus(new CVector(-100, 0).scaledBy(1/zoomFactor)), SCORE_TEXT_SIZE / zoomFactor, 0, BColor.shade(Team.LEFT.color, 200));
	}

	public Rect rectBounds() { return null; }
	public Poly polyBounds() { return null; }

}

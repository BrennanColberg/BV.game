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
import bv.sportsGame.game.entities.classes.Team;

public class HUD extends Entity implements Renderable {

	/* VARIABLES */
	private CVector scoreBackSize = new CVector(125, 17.5);
	private CVector timerBackSize = new CVector(75, 25);
	
	private Sprite teamScoreSprite(Team team, int height) { // TODO move this char splicing method into Number itself
		
		/* turning number into a string, then using that string to find chars from each place */
		String scoreString = "" + team.score;
		// using if operator for first digit; basically, if string is not 2 digits long then display a zero
		char scoreTensChar = scoreString.length() < 2 ? 0 : scoreString.charAt(scoreString.length() - 2);
		char scoreOnesChar = scoreString.charAt(scoreString.length() - 1);
		
		Poly[] digit = new Poly[] {
				TextSprite.fromCharacter(scoreTensChar).getSprite().get(0),
				TextSprite.fromCharacter(scoreOnesChar).getSprite().get(0)
		};
		Double[] width = new Double[] {
				TextSprite.fromCharacter(scoreTensChar).getWidth(),
				TextSprite.fromCharacter(scoreOnesChar).getWidth()
		};
		
		// setting up variables for spriteFrame creation
		Sprite result = new Sprite();
		final double SPACE_DISTANCE = height * 0.25;
		
		// offsets 
		digit[0].setOffset(new CVector(0, -0.5 * (width[0] + SPACE_DISTANCE)));
		digit[1].setOffset(new CVector(0, +0.5 * (width[1] + SPACE_DISTANCE)));
		
		// result formatting
		result.add(digit[0]);
		result.add(digit[1]);
		
		return result;
	}
	
	@SuppressWarnings("unused")
	private void renderScore(Renderer r) {
		
		final int SCORE_SIZE = 10;
		
		Sprite rightScore 	= teamScoreSprite(Team.RIGHT, SCORE_SIZE);
		Sprite leftScore	= teamScoreSprite(Team.LEFT, SCORE_SIZE);
		
		rightScore	.render(r, position.plus(new CVector(400, 0)), Color.white);
		leftScore	.render(r, position.plus(new CVector(-400, 0)), Color.white);
	}
	
	public void render(Renderer r) {
		
		Rect window = Core.state().rectBounds();
		double zoomFactor = Core.state().pixelsPerUnit;
		this.position = window.getCorner(0,-.5*  0.90  ); // 90% of the way up the screen, in the middle
		
		Rect scoreBack = new Rect(position, scoreBackSize.scaledBy(1/zoomFactor));
		Rect timerBack = new Rect(position, timerBackSize.scaledBy(1/zoomFactor));
		
		r.fill(scoreBack.rectBounds(), Color.black);
		r.fill(timerBack.rectBounds(), Color.darkGray);
		// temp disabled because text is screwed up
//		gameTimer.renderDigits(r);
//		renderScore(r);
	}

	public Rect rectBounds() { return null; }
	public Poly polyBounds() { return null; }

}

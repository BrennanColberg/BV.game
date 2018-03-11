package bv.sportsGame.game.gui;

import java.awt.Color;

import bv.framework.core.Core;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Entity;
import bv.framework.physics.Physics;
import bv.framework.sprites.CharSprite;
import bv.framework.sprites.Sprite;
import bv.sportsGame.game.entities.GameTimer;
import bv.sportsGame.game.entities.classes.Team;

public class HUD extends Entity implements Renderable, Physics {

	/* VARIABLES */	public GameTimer gameTimer = new GameTimer(300);
	private Rect scoreBack = new Rect(screenPosition(), new CVector(500, 70));
	private Rect timerBack = new Rect(screenPosition(), new CVector(300, 100));
	
	
	/* CONSTRUCTOR */
	public HUD() {
		gameTimer = new GameTimer(300);
	}
	
	// positioning algorithms
	public CVector screenPosition() {
		return position.plus(new CVector(0, -Core.STARTING_SCREEN_SIZE.getValue(1) * 2 + 125));
	}
	public void setPosition(CVector position) {
		super.setPosition(position);
	}
	
	private Sprite teamScoreSprite(Team team, int height) { // TODO move this char splicing method into Number itself
		
		/* turning number into a string, then using that string to find chars from each place */
		String scoreString = "" + team.score;
		// using if operator for first digit; basically, if string is not 2 digits long then display a zero
		char scoreTensChar = scoreString.length() < 2 ? 0 : scoreString.charAt(scoreString.length() - 2);
		char scoreOnesChar = scoreString.charAt(scoreString.length() - 1);
		
		Poly[] digit = new Poly[] {
				CharSprite.fromCharacter(scoreTensChar).size(height).get(0),
				CharSprite.fromCharacter(scoreOnesChar).size(height).get(0)
		};
		Double[] width = new Double[] {
				CharSprite.fromCharacter(scoreTensChar).width(height),
				CharSprite.fromCharacter(scoreOnesChar).width(height)
		};
		
		// setting up variables for spriteFrame creation
		Sprite result = new Sprite();
		final double SPACE_DISTANCE = height * 0.25;
		
		// offsets 
		digit[0].setPosition(new CVector(0, -0.5 * (width[0] + SPACE_DISTANCE)));
		digit[1].setPosition(new CVector(0, +0.5 * (width[1] + SPACE_DISTANCE)));
		
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
		
		rightScore	.render(r, screenPosition().plus(new CVector(400, 0)), Color.white);
		leftScore	.render(r, screenPosition().plus(new CVector(-400, 0)), Color.white);
	}
	
	public void render(Renderer r) {
		r.fill(scoreBack.rectBounds(), Color.black);
		r.fill(timerBack.rectBounds(), Color.darkGray);
		// temp disabled because text is screwed up
//		gameTimer.renderDigits(r);
//		renderScore(r);
	}
	
	public void updatePhysics() {
		this.setPosition(Core.state().position);
	}

	public Rect rectBounds() { return null; }
	public Poly polyBounds() { return null; }

}

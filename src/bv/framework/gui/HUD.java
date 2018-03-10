package bv.framework.gui;

import java.awt.Color;
import java.util.HashMap;

import bv.framework.core.Core;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.sprites.Sprite;
import bv.sportsGame.game.entities.classes.Team;

public class HUD implements Renderable {

	/* VARIABLES */
	public static HashMap<Team,Integer> scores = new HashMap<Team,Integer>();
	
	private CVector gamePosition = new CVector(0, 0);
	private GameTimer gameTimer = new GameTimer(screenPosition(), 300);
	private Rect scoreBack = new Rect(screenPosition(), new CVector(500, 70));
	private Rect timerBack = new Rect(screenPosition(), new CVector(300, 100));
	
	
	/* CONSTRUCTOR */
	public HUD() {
		scores.clear();
		scores.put(Team.LEFT, 0);
		scores.put(Team.RIGHT, 0);
		
		gamePosition = new CVector(0, 0);
		gameTimer = new GameTimer(screenPosition(), 300);
	}
	
	// point incrementer
	public static void incrementScore(Team team) {
		scores.put(team, scores.get(team) + 1);
	}
	
	// positioning algorithms
	public CVector screenPosition() {
		return new CVector(0, -Core.STARTING_SCREEN_SIZE.getValue(1) * 2 + 125).plus(gamePosition);
	}
	public void updateGamePosition(CVector position) {
		gamePosition = position;
		scoreBack.setPosition(screenPosition());
		timerBack.setPosition(screenPosition());
		gameTimer.setPosition(screenPosition());
	}
	
	private Sprite teamScoreSprite(Team team, int height) { // TODO move this char splicing method into Number itself
		
		/* turning number into a string, then using that string to find chars from each place */
		String scoreString = scores.get(team).toString();
		// using if operator for first digit; basically, if string is not 2 digits long then display a zero
		char scoreTensChar = scoreString.length() < 2 ? 0 : scoreString.charAt(scoreString.length() - 2);
		char scoreOnesChar = scoreString.charAt(scoreString.length() - 1);
		
		Poly[] digit = new Poly[] {
				Number.fromCharacter(scoreTensChar).size(height).get(0),
				Number.fromCharacter(scoreOnesChar).size(height).get(0)
		};
		Double[] width = new Double[] {
				Number.fromCharacter(scoreTensChar).width(height),
				Number.fromCharacter(scoreOnesChar).width(height)
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
		gameTimer.renderDigits(r);
		renderScore(r);
	}

	public Rect rectBounds() { return null; }
	public Poly polyBounds() { return null; }

}

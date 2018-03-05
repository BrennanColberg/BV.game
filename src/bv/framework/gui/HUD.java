package bv.framework.gui;

import java.awt.Color;
import java.util.ArrayList;

import bv.framework.core.Core;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.sprites.Sprite;
import bv.framework.sprites.SpriteIO;

public class HUD implements Renderable {

	public static ArrayList<Integer> scores = new ArrayList<Integer>(); //I'm sure there's a better way to implement this
	
	private CVector gamePosition = new CVector(0, 0);
	private GameTimer gameTimer = new GameTimer(screenPosition(), 300);
	
	private Rect scoreBack = new Rect(scoreBackPos(), new CVector(500, 70));
	private Rect timerBack = new Rect(timerBackPos(), new CVector(300, 100));
	
	public HUD() {
		scores.clear();
		scores.add(0);
		scores.add(0);
		
		gamePosition = new CVector(0, 0);
		gameTimer = new GameTimer(screenPosition(), 300);
	}
	
	private CVector scoreBackPos() {
		return screenPosition().plus(new CVector(-10, 0));
	}
	
	private CVector timerBackPos() {
		return screenPosition().plus(new CVector(-10, 0));
	}
	
	public static void addPoint(int team) {
		scores.set(team, scores.get(team) + 1);
	}
	
	public CVector screenPosition() {
		return new CVector(0, -Core.STARTING_SCREEN_SIZE.getValue(1) * 2 + 100).plus(gamePosition);
	}
	
	public void updateGamePosition(CVector position) {
		gamePosition = position;
		scoreBack.setPosition(scoreBackPos());
		timerBack.setPosition(timerBackPos());
		gameTimer.updatePosition(screenPosition());
	}
	
	private ArrayList<NumberCharacters> teamScore(int team) {
		int points = scores.get(team);
		ArrayList<NumberCharacters> output = new ArrayList<NumberCharacters>();
		if (points > 10) {
			output.add(NumberCharacters.getCharacter(Math.floorDiv(points, 10)));
			output.add(NumberCharacters.getCharacter(points - Math.floorDiv(points,  10) * 10));
		}
		else {
			output.add(NumberCharacters.getCharacter(points));
		}
		
		return output;
	}
	
	private void renderScore(Renderer r) {
		//Team one
		ArrayList<NumberCharacters> teamOne = teamScore(0); //Right
		for (int i = 0; i < teamOne.size(); i++) {
			teamOne.get(i).sprite.scaleNew(0.75).render(r, screenPosition().plus(new CVector(390, 0)), 0, Color.white);
		}
		
		//Team two
		ArrayList<NumberCharacters> teamTwo = teamScore(1); //Left
		for (int i = 0; i < teamOne.size(); i++) {
			teamOne.get(i).sprite.scaleNew(0.75).render(r, screenPosition().plus(new CVector(-410, 0)), 0, Color.white);
		}
	}
	
	@Override
	public void render(Renderer r) {
		r.fill(scoreBack.rectBounds(), Color.black);
		r.fill(timerBack.rectBounds(), Color.blue);
		gameTimer.renderDigits(r);
		renderScore(r);
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

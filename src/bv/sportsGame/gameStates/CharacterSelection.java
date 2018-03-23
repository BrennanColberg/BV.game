package bv.sportsGame.gameStates;

import java.awt.Color;

import bv.framework.core.Core;
import bv.framework.math.CVector;
import bv.framework.sprites.SpriteIO;
import bv.framework.sprites.TextSprite;
import bv.framework.state.GameState;
import bv.sportsGame.game.entities.CharSelection;
import bv.sportsGame.game.entities.CharSelector;
import bv.sportsGame.game.entities.classes.BasicClass;
import bv.sportsGame.game.entities.classes.SpeedsterClass;
import bv.sportsGame.game.entities.classes.TankClass;
import bv.sportsGame.game.entities.classes.Team;

public class CharacterSelection extends GameState {

	CharSelection selectionOne;
	CharSelection selectionTwo;
	CharSelection selectionThree;
	CharSelector selector;
	
	public void init() {
		
		objects.add(selectionOne = new CharSelection(SpriteIO.get("podracer").scaleNew(25), new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0)/3, -Core.STARTING_SCREEN_SIZE.getValue(1)/4), new BasicClass(new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0), 0), Team.LEFT, true, false)));
		objects.add(selectionOne = new CharSelection(SpriteIO.get("swarmV2").scaleNew(25), new CVector(0, -Core.STARTING_SCREEN_SIZE.getValue(1)/4), new SpeedsterClass(new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0), 0), Team.LEFT, true, false)));
		objects.add(selectionOne = new CharSelection(SpriteIO.get("dualGunner").scaleNew(25), new CVector(Core.STARTING_SCREEN_SIZE.getValue(0)/3, -Core.STARTING_SCREEN_SIZE.getValue(1)/4), new TankClass(new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0), 0), Team.LEFT, true, false)));
		objects.add(selector = new CharSelector(SpriteIO.get("ball").scaleNew(0.1), TextSprite.spriteFromCharacter('C').scaleNew(25), new CVector(0, 150)));
		
	}
	
	public void updatePhysics() {
		// updates physics for all contained entities
		super.updatePhysics();
	}

	public void load() {
		Core.renderEngine.renderer.setBackgroundColor(Color.black);
	}

}

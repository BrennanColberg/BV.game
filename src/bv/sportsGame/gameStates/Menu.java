package bv.sportsGame.gameStates;

import java.awt.Color;
import java.awt.event.KeyEvent;

import bv.framework.core.Core;
import bv.framework.core.Input;
import bv.framework.math.CVector;
import bv.framework.sprites.SpriteIO;
import bv.framework.sprites.TextSprite;
import bv.framework.state.GameState;
import bv.framework.syntax.BV;
import bv.sportsGame.game.entities.CharSelection;
import bv.sportsGame.game.entities.CharSelector;
import bv.sportsGame.game.entities.classes.BasicClass;
import bv.sportsGame.game.entities.classes.SpeedsterClass;
import bv.sportsGame.game.entities.classes.TankClass;
import bv.sportsGame.game.entities.classes.Team;
import bv.sportsGame.menu.entities.Button;

public class Menu extends GameState {

	CharSelection selectionOne;
	CharSelection selectionTwo;
	CharSelection selectionThree;
	CharSelector selector;
	
	public void init() {
		
		objects.add(selectionOne = new CharSelection(SpriteIO.get("podracer").scaleNew(25), new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0)/3, -Core.STARTING_SCREEN_SIZE.getValue(1)/4), new BasicClass(new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0), -100), Team.LEFT, true, false)));
		objects.add(selectionOne = new CharSelection(SpriteIO.get("swarmV2").scaleNew(25), new CVector(0, -Core.STARTING_SCREEN_SIZE.getValue(1)/4), new SpeedsterClass(new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0), -100), Team.LEFT, true, false)));
		objects.add(selectionOne = new CharSelection(SpriteIO.get("dualGunner").scaleNew(25), new CVector(Core.STARTING_SCREEN_SIZE.getValue(0)/3, -Core.STARTING_SCREEN_SIZE.getValue(1)/4), new TankClass(new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0), -100), Team.LEFT, true, false)));
		objects.add(selector = new CharSelector(SpriteIO.get("ball").scaleNew(0.1), TextSprite.spriteFromCharacter('C').scaleNew(25), new CVector(0, 150)));
		
		/* objects.add(new Button(new CVector(0,0),SpriteIO.get("drone").scaleNew(100),0,Color.red) {
			public void clicked() {
				Core.gameStateManager.loadGameState(new Game());
			}
			
			public void tick() {
				heading += 0.01;
			}
			
		}); */
	}
	
	public void updatePhysics() {
		// updates physics for all contained entities
		super.updatePhysics();
	}
	
	/* Long lastClick = System.currentTimeMillis();
	public void tick() {
		if (Input.getLastClickTime() > lastClick) {
			lastClick = Input.getLastClickTime();
			for (int i = 0; i < objects.size(); i++) if (objects.get(i) instanceof Button) {
				Button button = (Button) objects.get(i);
				if (button.polyBounds().contains(Input.getLastClickAdjustedPosition()))
					button.clicked();
			}
		}
	} */

	public void load() {
		Core.renderEngine.renderer.setBackgroundColor(Color.gray);
	}

}

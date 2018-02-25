package bv.sportsGame.gameStates;

import java.awt.Color;

import bv.framework.core.Core;
import bv.framework.core.Input;
import bv.framework.spritesCore.SpriteIO;
import bv.framework.state.GameState;
import bv.math.CVector;
import bv.sportsGame.menu.entities.Button;

public class Menu extends GameState {

	public void init() {
		
		objects.add(new Button(new CVector(0,0),SpriteIO.get("drone").scaleNew(100),0,Color.red) {

			public void clicked() {
				Core.gameStateManager.loadGameState(new Game());
			}
			
			public void tick() {
				heading += 0.01;
			}
			
		});
		
	}
	
	Long lastClick = System.currentTimeMillis();
	public void tick() {
		if (Input.getLastMousePress() > lastClick) {
			lastClick = Input.getLastMousePress();
			for (int i = 0; i < objects.size(); i++) if (objects.get(i) instanceof Button) {
				Button button = (Button) objects.get(i);
				if (button.polyBounds().contains(Input.getLastMousePressAdjustedPosition()))
					button.clicked();
			}
		}
	}

	public void load() {
		Core.renderEngine.renderer.setBackgroundColor(Color.gray);
	}

}

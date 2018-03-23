package bv.sportsGame.gameStates;

import java.awt.Color;

import bv.framework.core.Core;
import bv.framework.core.Input;
import bv.framework.math.CVector;
import bv.framework.sprites.SpriteIO;
import bv.framework.state.GameState;
import bv.sportsGame.game.entities.debug.MouseTracker;
import bv.sportsGame.game.entities.debug.PointHighlighter;
import bv.sportsGame.menu.entities.Button;

public class Menu extends GameState {

	public void init() {
		
		objects.add(new Button(new CVector(0,0),SpriteIO.get("rusher").scaleNew(100),0,Color.red) {
			public void clicked() {
				System.out.println("clicked");
				Core.gameStateManager.loadGameState(new Game());
			}
			
			public void tick() {
				heading += 0.01;
			}
			
		});
		objects.add(new PointHighlighter());
		
	}
	
	Long lastClick = System.currentTimeMillis();
	public void tick() {
		if (Input.getLastClickTime() > lastClick) {
			lastClick = Input.getLastClickTime();
			for (int i = 0; i < objects.size(); i++) if (objects.get(i) instanceof Button) {
				Button button = (Button) objects.get(i);
				if (button.polyBounds().contains(Input.getLastClickAdjustedPosition()))
					button.clicked();
			}
		}
	}

	public void load() {
		Core.renderEngine.renderer.setBackgroundColor(Color.gray);
	}

}

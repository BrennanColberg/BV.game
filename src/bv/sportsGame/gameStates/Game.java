/** 
 * @author	Brennan Colberg
 * @since	Jan 17, 2018
 */
package bv.sportsGame.gameStates;

import java.awt.Color;

import bv.gameFramework.core.Core;
import bv.gameFramework.state.GameState;
import bv.gui.FieldObject;
import bv.sportsGame.game.entities.Player;
import bv.sportsGame.game.entities.PointHighlighter;

/** 
 * @author	Brennan Colberg
 * @since	Jan 17, 2018
 */
public class Game extends GameState {

	public void init() {
		objects.add(new FieldObject());
		objects.add(new PointHighlighter());
		objects.add(new Player());
	}
	
	public void updatePhysics() {
		super.updatePhysics();
	}
	
	public void load() {
		Core.renderEngine.renderer.setBackgroundColor(Color.lightGray);
		
	}
	
}

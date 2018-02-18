/** 
 * @author	Brennan Colberg
 * @since	Jan 17, 2018
 */
package bv.sportsGame.gameStates;

import java.awt.Color;

import bv.gameFramework.core.Core;
import bv.gameFramework.state.GameState;
import bv.sportsGame.game.entities.MouseTracker;
import bv.sportsGame.game.entities.PointHighlighter;

/** 
 * @author	Brennan Colberg
 * @since	Jan 17, 2018
 */
public class Game extends GameState {

	public void init() {
		objects.add(new PointHighlighter());
		objects.add(new MouseTracker());
	}
	
	public void updatePhysics() {
		
		super.updatePhysics();
		
	}
	
	public void load() {
		Core.renderEngine.renderer.setBackgroundColor(Color.lightGray);
	}
	
}

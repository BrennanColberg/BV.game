/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
package bv.framework.core;

import bv.framework.core.engines.CollisionEngine;
import bv.framework.core.engines.RenderEngine;
import bv.framework.core.engines.TickEngine;
import bv.framework.spritesCore.SpriteIO;
import bv.framework.state.GameState;
import bv.math.CVector;

/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
public class Core {
	
	public static final CVector STARTING_SCREEN_SIZE = new CVector(1024,576);
	
	public static boolean printEngineUpdatesToConsole = true;
	public static int engineUpdateDelay = 1000;
	public static boolean running = true, ticking = true, rendering = true;
	
	public static GameStateManager gameStateManager;
	public static TickEngine tickEngine;
	public static RenderEngine renderEngine;
	public static CollisionEngine collisionEngine;
	public static void main(String[] args) {
		
		SpriteIO.load();
		
		renderEngine = new RenderEngine(90, STARTING_SCREEN_SIZE);
		gameStateManager = new GameStateManager();
		tickEngine = new TickEngine(90);
		collisionEngine = new CollisionEngine(30);
		renderEngine.start();
		tickEngine.start();
		collisionEngine.start();
		
	}
	
	public static GameState state() {
		return gameStateManager.currentState;
	}
	
}

/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
package bv.gameFramework.core;

import bv.gameFramework.core.engines.CollisionEngine;
import bv.gameFramework.core.engines.RenderEngine;
import bv.gameFramework.core.engines.TickEngine;
import bv.gameFramework.spritesCore.SpriteIO;
import bv.gameFramework.state.GameState;
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
		
		renderEngine = new RenderEngine(120, STARTING_SCREEN_SIZE);
		gameStateManager = new GameStateManager();
		tickEngine = new TickEngine(100.0);
		collisionEngine = new CollisionEngine(30.0);
		renderEngine.start();
		tickEngine.start();
		collisionEngine.start();
		
	}
	
	public static GameState state() {
		return gameStateManager.currentState;
	}
	
}

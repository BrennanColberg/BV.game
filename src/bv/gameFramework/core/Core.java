/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
package bv.gameFramework.core;

import bv.gameFramework.spritesCore.SpriteIO;
import bv.math.CVector;

/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
public class Core {
	
	public static final CVector STARTING_SCREEN_SIZE = new CVector(960,540);
	
	public static boolean printEngineUpdatesToConsole = true;
	public static int engineUpdateDelay = 1000;
	public static boolean running = true, ticking = true, rendering = true;
	
	public static GameStateManager gameStateManager;
	public static TickEngine tickEngine;
	public static RenderEngine renderEngine;
	public static Input input;
	public static void main(String[] args) {
		
		SpriteIO.load();
		
		renderEngine = new RenderEngine(120, STARTING_SCREEN_SIZE);
		gameStateManager = new GameStateManager();
		tickEngine = new TickEngine(100);
		input = new Input();
		renderEngine.start();
		tickEngine.start();
		
	}
	
}

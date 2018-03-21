package bv.framework.core;

import bv.framework.core.engines.CollisionEngine;
import bv.framework.core.engines.RenderEngine;
import bv.framework.core.engines.TickEngine;
import bv.framework.math.CVector;
import bv.framework.sprites.SpriteIO;
import bv.framework.state.GameState;
import bv.sportsGame.gameStates.Menu;

/** Centrally static class around which the running program revolves.
 *  You can see it as the hub; all managers and threads can be accessed through a static call to this class.
 *  Contains the main(String[]) method. 
 *  Largely just used to coordinate startup and initialization of all necessary framework components without a spaghettified hassle...
 * @author Brennan D. Colberg - {@link http://github.com/BrennanColberg} */
public class Core {
	
	/** The {@link CVector} that dictates at what size the screen should start. */
	public static final CVector STARTING_SCREEN_SIZE = new CVector(1024,576);
	
	/** Decrees whether or not updates such as TPS and FPS should show in console. */
	public static boolean printEngineUpdatesToConsole = false;
	// no idea what this does
	public static int engineUpdateDelay = 1000;
	// good lord this is spaghetti, are these even used?
	public static boolean running = true, ticking = true, rendering = true;
	
	/** The {@link GameStateManager} that the running program uses. */
	public static GameStateManager gameStateManager;
	/** The {@link TickEngine} that the running program uses. */
	public static TickEngine tickEngine;
	/** The {@link RenderEngine} that the running program uses. */
	public static RenderEngine renderEngine;
	/** The {@link CollisionEngine} that the running program uses. */
	public static CollisionEngine collisionEngine;
	
	/** The method used to start up the entire program! 
	 * 	@param args console inputs, unused in our case */
	public static void main(String[] args) {
		
		// loads all Sprites and AnimatedSprites into an organized database from the src/sprites folder
		SpriteIO.load();
		
		// initializes various engines and managers
		renderEngine = new RenderEngine			(60, "FPS", STARTING_SCREEN_SIZE);
		gameStateManager = new GameStateManager(new Menu());
		tickEngine = new TickEngine				(90, "TPS");
		collisionEngine = new CollisionEngine	(45, "CPS");
		
		// starts all three threads; program officially begins here!
		renderEngine.start();
		tickEngine.start();
		collisionEngine.start();
		
	}
	
	/** A shorthand method; this simplifies "Core.gameStateManager.currentState.[probably two more things]" into "Core.state().[other stuff]" to make life easier.
	 * @return the program's currently focused {@link GameState} */
	public static GameState state() {
		return gameStateManager.currentState;
	}
	
}

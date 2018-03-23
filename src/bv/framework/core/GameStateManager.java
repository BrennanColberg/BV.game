package bv.framework.core;

import java.util.Stack;

import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.physics.Collidable;
import bv.framework.physics.Physics;
import bv.framework.state.GameState;
import bv.framework.state.Tickable;
import bv.sportsGame.gameStates.Game;
import bv.sportsGame.gameStates.Menu;

/** A class which coordinates {@link GameState}s relevant to the current program. 
 * @author Brennan D. Colberg - {@link http://github.com/BrennanColberg} */
public class GameStateManager {
	
	/* VARIABLES */
	
	/** The {@link GameState} with which the current program always begins. */
	public static final GameState STARTING_GAME_STATE = new Menu();
	
	/** The {@link Stack} which stores currently loaded {@link GameState}s in order.
	 * 	The currently focused {@link GameState} is always the one at the top; when it is removed, the one below it comes into focus.
	 * 	When a new {@link GameState} is loaded, the currently focused one still exists but is covered; upon the newer one's removal, it will once again be focused.
	 * 	This allows for very easy creation of pause screens, menus, various levels, and the like. */
	private Stack<GameState> stateStack;
	/** The {@link GameState} which is currently in focus. 
	 * 	Also at the top of this class's stateStack.
	 * 	Need shorthand? Use "{@link Core}.state().[other stuff]" to reference this object. */
	public GameState currentState;
	
	
	/** The default constructor. 
	 * 	Initializes the stateStack and loads the given {@link GameState}. 
	 * @param state	the {@link GameState} with which to start the program */
	public GameStateManager(GameState state) {
		stateStack = new Stack<GameState>();
		loadGameState(state);
	}
	/** The simplified constructor. 
	 *	Initializes the stateStack and loads the preset starting {@link GameState}. */
	public GameStateManager() {
		this(STARTING_GAME_STATE);
	}
	
	/** A method used to load a new {@link GameState} when the stateStack is updated. 
	 * @param target the {@link GameState} to load into focus */
	public void loadGameState(GameState target) {
		if (!stateStack.contains(target)) stateStack.push(target);
		currentState = target;
		target.load();
	}
	
	/** A method to get the second-down {@link GameState}; used to pre-empt the safety of removal, or perhaps to implement transparency effects for menus.
	 * @return the {@link GameState} second-down in the stateStack, to which focus will pass upon the current {@link GameState}'s removal */
	public GameState gameStateLast() {
		return stateStack.elementAt(stateStack.size()-2);
	}
	
	/** Passes tick() and updatePhysics() instructions down to the current {@link GameState}'s {@link Tickable} and {@link Physics}-implementing objects. */
	@SuppressWarnings("deprecation")
	public void tick() {
		
		// if the top state has been updated since last tick, load the new one
		if (currentState != stateStack.peek())
			loadGameState(stateStack.peek());
		
		// update Input in order to have low-latency calculations
		Input.tick();
		
		// calls tick() in all Tickables in current GameState
		currentState.tickObjects();
		// calls updatePhysics() in all Physics-implementers in current GameState
		currentState.updateObjectPhysics();
	}
	/** Passes render() instructions down to the current {@link GameState}'s {@link Renderable} objects. */
	public void render(Renderer r) {
		currentState.renderObjects(r);
	}
	/** Passes calculateCollisions() instructions down to the current {@link GameState}'s {@link Collidable} objects. */
	public void calculateCollisions() {
		currentState.calculateCollisions();
	}
	
}

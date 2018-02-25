/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
package bv.framework.core;

import java.util.Stack;

import bv.framework.graphics.Renderer;
import bv.framework.state.GameState;
import bv.sportsGame.gameStates.Menu;

/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
public class GameStateManager {
	
	/* VARIABLES */
	
	public static final GameState STARTING_GAME_STATE = new Menu();
	
	// for some reason making these non-static screws stuff up, no idea ... don't touch it
	// probably has to do with multithreaded approach of tick and render
	private Stack<GameState> stateStack;
	public GameState currentState;
	
	public GameStateManager() {
		stateStack = new Stack<GameState>();
		loadGameState(STARTING_GAME_STATE);
	}
	
	public void loadGameState(GameState target) {
		if (!stateStack.contains(target)) stateStack.push(target);
		currentState = target;
		target.load();
	}
	
	public GameState gameStateLast() {
		return stateStack.elementAt(stateStack.size()-2);
	}
	
	public void tick() {
		if (currentState != stateStack.peek())
			loadGameState(stateStack.peek());
		
		Input.tick();
		currentState.tick();
		currentState.updatePhysics();
	}
	public void render(Renderer r) {
		currentState.render(r);
	}
	public void calculateCollisions() {
		currentState.calculateCollisions();
	}
	
}
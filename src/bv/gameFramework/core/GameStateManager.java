/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
package bv.gameFramework.core;

import java.util.Stack;

import bv.gameFramework.state.GameState;
import bv.sportsGame.gameStates.Game;

/** 
 * @author	Brennan Colberg
 * @since	Dec 7, 2017
 */
public class GameStateManager {
	
	/* VARIABLES */
	
	public static final GameState STARTING_GAME_STATE = new Game();
	
	public Stack<GameState> game;
	public GameState currentState;
	
	public GameStateManager() {
		game = new Stack<GameState>();
		loadGameState(STARTING_GAME_STATE);
	}
	
	public void loadGameState(GameState target) {
		if (!game.contains(target)) game.push(target);
		currentState = target;
		target.load();
	}
	
	public void tick() {
		if (currentState != game.peek())
			loadGameState(game.peek());
		
		Input.tick();
		currentState.tick();
		currentState.updatePhysics();
	}
	
	public GameState gameStateLast() {
		return game.elementAt(game.size()-2);
	}
	
}

package bv.sportsGame.game.entities;

import bv.framework.core.Core;
import bv.framework.state.Tickable;

public class GameTimer implements Tickable {
	
	public double secondsLeft; //This is what is specifically counted down
	
	public GameTimer(int startingTime) {
		this.secondsLeft = startingTime;
	}
	
	//This method is what is called every second by the timer in order to update the game timer
	public void tick() {
		secondsLeft -= 1d / Core.tickEngine.targetFrequency;
		if (secondsLeft <= 0) {
			// TODO implement game stop function
			// basically, new state which proclaims winner & returns to menu
		}
	}

}

/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
package bv.gameFramework.core;

import bv.syntax.BV;

/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
public class TickEngine extends Thread {

	/* VARIABLES */
	
	private GameStateManager stateManager;
	
	private double targetTPS, realTPS;
	
	
	/* CONSTRUCTORS */
	
	public TickEngine(double newTargetTPS) {
		stateManager = new GameStateManager();
		setTargetTPS(newTargetTPS);
	}
	
	
	/* METHODS */
	
	public void run() {
		
		long currentTime = System.currentTimeMillis();
		long timeOfLastTick = currentTime, timeOfLastMessage = currentTime;

		double ticks = 0;

		while (Core.running && Core.ticking) {

			currentTime = System.currentTimeMillis();

			if (currentTime - timeOfLastTick >= (1d / this.getTargetTPS()) * 1000d) {
				tick();
				ticks += 1;
				timeOfLastTick += (1d / this.getTargetTPS()) * 1000d;
			}

			if (currentTime - timeOfLastMessage >= Core.engineUpdateDelay) {
				setRealTPS((double) ticks / (double) (currentTime - timeOfLastMessage) * 1000d);
				if (Core.printEngineUpdatesToConsole == true) 
					BV.println(String.format("! TPS: %s of %s", getRealTPS(), getTargetTPS()));
				ticks = 0;
				timeOfLastMessage += Core.engineUpdateDelay;
			}
		}
	}
	public void tick() {

		/* UPDATING CURRENT STATE */
		stateManager.tick();
//		try {
//			currentState = stateStack.peek();
//		} catch (EmptyStackException e) {
//			System.exit(0);
//		}
//
//		if (lastState != currentState) {
//			System.out.println(String.format("Switching states from '%s' to '%s", lastState, currentState));
//			Resources.stopAll();
//
//			if (lastState != null) lastState.unfocus();
//			
//			if (!initiatedStates.contains(currentState)) {
//				currentState.init();
//				currentState.add(new ControlInput());
//				initiatedStates.add(currentState);
//			} else if (currentState.resetting()) {
////				currentState.reset();
//			}
//
//			lastState = currentState;
//			
//			currentState.focus();
//		}
//		
//		Display.input.tick();
//		currentState.tick();
	}
	
	
	/* GETTERS & SETTERS */
	
	public double getTargetTPS() {
		return this.targetTPS;
	}
	public void setTargetTPS(double newTargetTPS) {
		this.targetTPS = newTargetTPS;
	}
	
	public double getRealTPS() {
		return this.realTPS;
	}
	private void setRealTPS(double newRealTPS) {
		this.realTPS = newRealTPS;
	}

}

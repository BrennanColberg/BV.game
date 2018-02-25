/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
package bv.gameFramework.physics;

import bv.gameFramework.core.Core;
import bv.gameFramework.core.GameStateManager;
import bv.syntax.BV;

/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
public class CollisionEngine extends Thread {

	/* VARIABLES */
	
	private GameStateManager stateManager;
	
	private double targetCPS, realCPS;
	
	
	/* CONSTRUCTORS */
	
	public CollisionEngine(double newTargetCPS) {
		stateManager = new GameStateManager();
		setTargetCPS(newTargetCPS);
	}
	
	
	/* METHODS */
	
	public void run() {
		
		long currentTime = System.currentTimeMillis();
		long timeOfLastTick = currentTime, timeOfLastMessage = currentTime;

		double ticks = 0;

		while (Core.running && Core.ticking) {

			currentTime = System.currentTimeMillis();

			if (currentTime - timeOfLastTick >= (1d / this.getTargetCPS()) * 1000d) {
				calculateCollisions();
				ticks += 1;
				timeOfLastTick += (1d / this.getTargetCPS()) * 1000d;
			}

			if (currentTime - timeOfLastMessage >= Core.engineUpdateDelay) {
				setRealCPS((double) ticks / (double) (currentTime - timeOfLastMessage) * 1000d);
				if (Core.printEngineUpdatesToConsole == true) 
					BV.println(String.format("! CPS: %s of %s", getRealCPS(), getTargetCPS()));
				ticks = 0;
				timeOfLastMessage += Core.engineUpdateDelay;
			}
		}
	}
	public void calculateCollisions() {

		/* UPDATING CURRENT STATE */
		stateManager.calculateCollisions();
		
	}
	
	
	/* GETTERS & SETTERS */
	
	public double getTargetCPS() {
		return this.targetCPS;
	}
	public void setTargetCPS(double newTargetCPS) {
		this.targetCPS = newTargetCPS;
	}
	
	public double getRealCPS() {
		return this.realCPS;
	}
	private void setRealCPS(double newRealCPS) {
		this.realCPS = newRealCPS;
	}

}

/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
package bv.framework.core.engines;

import bv.framework.core.Core;

/** 
 * @author	Brennan Colberg
 * @since	Feb 24, 2017
 */
public abstract class Engine extends Thread {

	/* VARIABLES */
	public double targetFrequency, realFrequency;
	
	/* CONSTRUCTORS */
	public Engine(double newTargetFrequency) {
		this.targetFrequency = newTargetFrequency;
	}
	
	/* METHODS */
	public void run() {
		long currentTime = System.currentTimeMillis();	
		long timeOfLastTick = currentTime, timeOfLastMessage = currentTime;
		double occurences = 0;
		while (Core.running) {
			currentTime = System.currentTimeMillis();
			if (currentTime - timeOfLastTick >= (1d / targetFrequency) * 1000d) {
				trigger();
				occurences += 1;
				timeOfLastTick += (1d / targetFrequency) * 1000d;
			}
			if (currentTime - timeOfLastMessage >= Core.engineUpdateDelay) {
				realFrequency = (double) occurences / (double) (currentTime - timeOfLastMessage) * 1000d;
//				if (Core.printEngineUpdatesToConsole == true) 
//					BV.println(String.format("! TPS: %s of %s", getRealTPS(), getTargetTPS()));
				occurences = 0;
				timeOfLastMessage += Core.engineUpdateDelay;
			}
		}
	}
	public abstract void trigger();

}

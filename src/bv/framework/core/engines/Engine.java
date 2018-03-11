package bv.framework.core.engines;

import bv.framework.core.Core;
import bv.framework.syntax.BV;

/** A class used to create a separate {@link Thread} in the current program and set it to run at a fixed frequency of calls per second. 
 * 	Used in games for ticking, rendering, udpating physics, calculating collisions ... everything that must be run at a fixed rate. */
public abstract class Engine extends Thread {

	/* VARIABLES */
	
	/** The frequency of cycles per second at which this engine will ideally run (in {@link Double} form). */
	public double targetFrequency;
	/** The frequency of cycles per second at which this engine most recently ran (in {@link Double} form). */
	public double realFrequency;
	/** The unique message that distinguishes this {@link Engine} when printing updates to the Console (in {@link String} form). */
	public String title;
	
	
	/* CONSTRUCTORS */
	
	/** Default constructor; nothing fancy.
	 * @param newTargetFrequency the frequency of cycles per second at which this engine will ideally run (in {@link Double} form)
	 * @param title the unique message that distinguishes this {@link Engine} when printing Console updates (in {@link String} form) */
	public Engine(double newTargetFrequency, String title) {
		this.targetFrequency = newTargetFrequency;
		this.title = title;
	}
	
	
	/* METHODS */
	
	/** Called once to turn on the engine; starts this new thread. */
	public void run() { // basically magical. don't touch or seek to understand
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
				if (Core.printEngineUpdatesToConsole == true) 
					BV.println(String.format("! %s: %d%c [%d/%d]", title, (int) (realFrequency * 100d / targetFrequency), '%', (int) realFrequency, (int) targetFrequency));
				occurences = 0;
				timeOfLastMessage += Core.engineUpdateDelay;
			}
		}
	}
	/** The functional method of each {@link Engine}.
	 * 	Called approximately [Engine.targetFrequency] times per second. */
	public abstract void trigger();

}

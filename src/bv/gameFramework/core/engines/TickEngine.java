package bv.gameFramework.core.engines;

import bv.gameFramework.core.Core;

public class TickEngine extends Engine {

	public TickEngine(double newTargetFrequency) {
		super(newTargetFrequency);
	}
	
	public void trigger() {
		Core.gameStateManager.tick();
	}
	
}

package bv.framework.core.engines;

import bv.framework.core.Core;

public class TickEngine extends Engine {

	public TickEngine(double newTargetFrequency) {
		super(newTargetFrequency);
	}
	
	public void trigger() {
		Core.gameStateManager.tick();
	}
	
}

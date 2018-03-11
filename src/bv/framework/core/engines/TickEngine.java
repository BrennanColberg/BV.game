package bv.framework.core.engines;

import bv.framework.core.Core;

public class TickEngine extends Engine {

	public TickEngine(double newTargetFrequency, String title) {
		super(newTargetFrequency, title);
	}
	
	public void trigger() {
		Core.gameStateManager.tick();
	}
	
}

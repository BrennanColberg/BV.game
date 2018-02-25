package bv.framework.core.engines;

import bv.framework.core.Core;

public class CollisionEngine extends Engine {
	
	public CollisionEngine(double newTargetFrequency) {
		super(newTargetFrequency);
	}

	public void trigger() {
		Core.gameStateManager.calculateCollisions();
	}

}

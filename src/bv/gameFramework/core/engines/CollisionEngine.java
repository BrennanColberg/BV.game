package bv.gameFramework.core.engines;

import bv.gameFramework.core.Core;

public class CollisionEngine extends Engine {
	
	public CollisionEngine(double newTargetFrequency) {
		super(newTargetFrequency);
	}

	public void trigger() {
		Core.gameStateManager.calculateCollisions();
	}

}

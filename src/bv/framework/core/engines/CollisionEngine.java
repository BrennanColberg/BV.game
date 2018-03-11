package bv.framework.core.engines;

import bv.framework.core.Core;

public class CollisionEngine extends Engine {
	
	public CollisionEngine(double newTargetFrequency, String title) {
		super(newTargetFrequency, title);
	}

	public void trigger() {
		Core.gameStateManager.calculateCollisions();
	}

}

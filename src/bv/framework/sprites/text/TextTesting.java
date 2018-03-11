package bv.framework.sprites.text;

import bv.framework.core.Core;
import bv.framework.physics.Entity;
import bv.framework.state.GameState;
import bv.sportsGame.game.entities.debug.PointHighlighter;

public class TextTesting extends GameState {

	Entity r;
	
	public void init() {
		objects.add(r = new RChar('1'));
		objects.add(new PointHighlighter() {
			public void tick() {
				this.setPosition(((TextTesting) Core.state()).r.position);
			}
		});
	}
	
	public void tick() {
		super.tick();
//		this.position = Input.getMousePosition().minus(Core.state().getSize().scaledBy(0.5));
	}

	public void load() {
		
	}

}

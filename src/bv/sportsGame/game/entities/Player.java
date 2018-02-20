package bv.sportsGame.game.entities;

import java.awt.Color;
import java.awt.event.KeyEvent;

import bv.gameFramework.core.Core;
import bv.gameFramework.core.Input;
import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;
import bv.gameFramework.physics.Entity;
import bv.gameFramework.spritesCore.Sprite;
import bv.gameFramework.spritesCore.SpriteIO;
import bv.math.CVector;
import bv.math.Poly;
import bv.math.Rect;
import bv.sportsGame.game.entities.projectiles.Missile;
import bv.sportsGame.gameStates.Game;
/**
 * @author Aroosh Kumar
 * @since Sunday February 18, 2018
 * 
 * The object which the player controls in the game
 */
public class Player extends Entity implements Renderable {
	Sprite sprite = SpriteIO.get("podracer").scale(50);
	protected double drag = 0.01;
	
	/**
	 * This method make the player follow the mouse and accelerates the player
	 * when the space key is pressed. The player will also experience drag which will
	 * reduce its velocity to zero over time, if the player does not accelerate
	 * 
	 * @author Aroosh Kumar
	 * @since Sunday February 18, 2018
	 * @param none
	 * 
	 */
	public void updatePhysics(){
		
		CVector target = Input.getMouseAdjustedPosition();
		velocity.setAngle(Math.atan2(target.getValue(1) - position.getValue(1), target.getValue(0) - position.getValue(0)));
		
		if (Input.isKeyPressed(KeyEvent.VK_SPACE) && this.velocity.getMagnitude() < 5.0)
			acceleration.setMagnitude(1);
		else
			acceleration.setMagnitude(0);
		// TODO: Fix drag, as applying drag currently glitches out the player
//		this.velocity.setMagnitude(this.velocity.getMagnitude() - this.drag);
		if (Input.isKeyPressed(KeyEvent.VK_W))
			Core.gameStateManager.currentState.objects.add(new Missile(this.getPosition(), this.velocity.getAngle(), 10, 10 + this.velocity.getMagnitude()));
		super.updatePhysics();
	}
	
	@Override
	public void render(Renderer r) {
		sprite.render(r, position, this.velocity.getAngle(), Color.blue);
	}

	@Override
	public Rect rectBounds() {
		return sprite.get(0).rectBounds();
	}

	@Override
	public Poly polyBounds() {
		return sprite.get(0);
	}

}

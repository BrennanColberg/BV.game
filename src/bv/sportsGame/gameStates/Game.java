/** 
 * @author	Brennan Colberg
 * @since	Jan 17, 2018
 */
package bv.sportsGame.gameStates;

import java.awt.Color;
import java.awt.event.KeyEvent;

import bv.gameFramework.core.Core;
import bv.gameFramework.core.Input;
import bv.gameFramework.state.GameState;
import bv.gui.FieldObject;
import bv.math.CVector;
import bv.sportsGame.game.entities.Player;
import bv.sportsGame.game.entities.PointHighlighter;
import bv.sportsGame.game.entities.projectiles.Missile;

/** 
 * @author	Brennan Colberg
 * @since	Jan 17, 2018
 */
public class Game extends GameState {
	Player player;
	public void init() {
		player = new Player();
		objects.add(new FieldObject());
		objects.add(new PointHighlighter());
		objects.add(player);
		this.pixelsPerUnit = 0.5;
	}
	
	public void updatePhysics() {
		// Zooms in when the 1 key is pressed and zooms out when the 2 key is pressed
		if (Input.isKeyPressed(KeyEvent.VK_1)){
			if (this.pixelsPerUnit < 1){
				this.pixelsPerUnit += 0.01;
			}
		}
		else if (Input.isKeyPressed(KeyEvent.VK_2)){
			if (this.pixelsPerUnit > 0.25){
				this.pixelsPerUnit -= 0.01;
			}
		}
		velocity.setAngle(Math.atan2(player.getPosition().getValue(1) - position.getValue(1), player.getPosition().getValue(0) - position.getValue(0)));
		
		velocity.setMagnitude(player.getPosition().minus(this.position).toPVector().getMagnitude() / (50 * this.pixelsPerUnit));
		
		// A very hacky way of deleting all projectiles fired. Press backspace to delete the missiles
		if (Input.isKeyPressed(KeyEvent.VK_BACK_SPACE)){
			for (Object o : objects){
				if (o instanceof Missile){
					objects.set(objects.indexOf(o), null);
				}
			}
		}
		super.updatePhysics();
	}
	
	public void load() {
		Core.renderEngine.renderer.setBackgroundColor(Color.lightGray);
		
	}
	
}

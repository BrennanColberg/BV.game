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
import bv.sportsGame.game.entities.BasicClass;
import bv.sportsGame.game.entities.PointHighlighter;
import bv.sportsGame.game.entities.SpeedsterClass;
import bv.sportsGame.game.entities.projectiles.Missile;

/** 
 * @author	Brennan Colberg
 * @since	Jan 17, 2018
 */
public class Game extends GameState {

	BasicClass player;
	
	public void init() {
		//player = new Player();
		//player = new BasicClass();
		//player = new TankClass();
		player = new SpeedsterClass(true); //I forgot that this works but bc Speedster inherits from BasicClass (which is the type that this variable was defined as being) this actually works. This is mostly for me bc I had forgotten so leave this in just in case I forget. Sorry. I'll delete this later
		objects.add(new FieldObject());
		objects.add(new PointHighlighter());
		objects.add(player);
		
		this.pixelsPerUnit = 0.25;
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
		
		//TODO: For some reason this line of code locks up all key inputs after some time
//		if (Input.isKeyPressed(KeyEvent.VK_A)){
//			this.velocity.add(new PVector(-10, player.getVelocity().getAngle()));
//		}
		
		super.updatePhysics();
	}
	
	public void load() {
		Core.renderEngine.renderer.setBackgroundColor(Color.white);
		
	}
	
}

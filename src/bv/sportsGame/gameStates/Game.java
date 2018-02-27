/** 
 * @author	Brennan Colberg
 * @since	Jan 17, 2018
 */
package bv.sportsGame.gameStates;

import java.awt.Color;
import java.awt.event.KeyEvent;

import bv.framework.core.Core;
import bv.framework.core.Input;
import bv.framework.gui.FieldObject;
import bv.framework.math.CVector;
import bv.framework.math.Rect;
import bv.framework.state.GameState;
import bv.sportsGame.game.entities.Ball;
import bv.sportsGame.game.entities.Goal;
import bv.sportsGame.game.entities.classes.BasicClass;
import bv.sportsGame.game.entities.classes.TankClass;
import bv.sportsGame.game.entities.classes.Team;
import bv.sportsGame.game.entities.projectiles.Missile;

/** 
 * @author	Brennan Colberg
 * @since	Jan 17, 2018
 */
public class Game extends GameState {

	Goal goal1, goal2;
	BasicClass player;
	BasicClass dummy;
	Ball ball;
	public Rect gamefield = new Rect(new CVector(0,0), Core.STARTING_SCREEN_SIZE);
	
	public void init() {
		
		objects.add(new FieldObject());
		
		//This can be implemented in a better way later, I just wanted to get the functionality down
		objects.add(goal1 	= new Goal(new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0)/2 * 4, 0), Team.RIGHT));
		objects.add(goal2 	= new Goal(new CVector(Core.STARTING_SCREEN_SIZE.getValue(0)/ 2 * 4, 0), Team.LEFT));
		objects.add(player 	= new BasicClass(new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0), 0), 0, true));
		objects.add(dummy 	= new TankClass(new CVector(Core.STARTING_SCREEN_SIZE.getValue(0), 0), 1, false));
		objects.add(ball 	= new Ball());
		
		// objects.add(new PointHighlighter()); // only used for debug
		
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
		
		// creates an exponentially-powered speed; smoothly transitions from moving quickly towards the player when faraway to hardly moving at all when local
		// always moves at a rate dependent on screen size
		this.velocity.setAngle(Math.atan2(player.getPosition().getValue(1) - position.getValue(1), player.getPosition().getValue(0) - position.getValue(0)));
		this.velocity.setMagnitude(player.getPosition().minus(this.position).toPVector().getMagnitude() / (50 * this.pixelsPerUnit));
		
		// updates physics for all contained entities
		super.updatePhysics();
		
	}
	
	public void load() {
		Core.renderEngine.renderer.setBackgroundColor(Color.BLACK);
	}
	
}

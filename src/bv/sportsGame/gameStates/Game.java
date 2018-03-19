/** 
 * @author	Brennan Colberg
 * @since	Jan 17, 2018
 */
package bv.sportsGame.gameStates;

import java.awt.Color;
import java.awt.event.KeyEvent;

import bv.framework.core.Core;
import bv.framework.core.Input;
import bv.framework.math.CVector;
import bv.framework.math.Rect;
import bv.framework.state.GameState;
import bv.framework.syntax.BV;
import bv.sportsGame.game.entities.Ball;
import bv.sportsGame.game.entities.GameTimer;
import bv.sportsGame.game.entities.Goal;
import bv.sportsGame.game.entities.classes.BasicClass;
import bv.sportsGame.game.entities.classes.TankClass;
import bv.sportsGame.game.entities.classes.Team;
import bv.sportsGame.game.gui.FieldObject;
import bv.sportsGame.game.gui.HUD;

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
	public HUD hud;
	public static GameTimer gameTimer;
	
	public void init() {
		
		objects.add(new FieldObject());
		
		objects.add(goal1	= new Goal(new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0)/2 * 4, 0), Team.RIGHT));
		objects.add(goal2	= new Goal(new CVector(Core.STARTING_SCREEN_SIZE.getValue(0)/ 2 * 4, 0), Team.LEFT));
		objects.add(player	= new BasicClass(new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0), 0), Team.LEFT, true));
		objects.add(dummy	= new TankClass(new CVector(Core.STARTING_SCREEN_SIZE.getValue(0), 0), Team.RIGHT, false));
		objects.add(ball	= new Ball());
		
		objects.add(hud			= new HUD());
		objects.add(gameTimer 	= new GameTimer(300));
		
		this.zoomFactor = 0.25;
	}
	
	//This is completely harmless to try to see if I can figure out how to commit to the right spot here
	public void updatePhysics() {
		
		// Zooms in when the 1 key is pressed and zooms out when the 2 key is pressed
		double zoomIncrement = 0;
		if (Input.isKeyPressed(KeyEvent.VK_1)) zoomIncrement = +0.01;
		if (Input.isKeyPressed(KeyEvent.VK_2)) zoomIncrement = -0.01;
		this.zoomFactor = BV.clamp(zoomFactor + zoomIncrement, 0.25, 1.0);
		
		// proportionally decreasing speed; position limits to player position
		this.velocity.setAngle(Math.atan2(player.getPosition().getValue(1) - position.getValue(1), player.getPosition().getValue(0) - position.getValue(0)));
		this.velocity.setMagnitude(player.getPosition().minus(this.position).toPVector().getMagnitude() / (50 * this.zoomFactor));
		
		// updates physics for all contained entities
		super.updatePhysics();
		
	}
	
	public void load() {
		Core.renderEngine.renderer.setBackgroundColor(Color.BLACK);
	}
	
}

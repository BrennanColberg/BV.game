package bv.sportsGame.game.entities;

import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;
import bv.gameFramework.physics.Collidable;
import bv.gameFramework.physics.Entity;
import bv.math.CVector;
import bv.math.PVector;
import bv.math.Poly;
import bv.math.Rect;


/**
 * This is the class that controls what happens with the goals.
 * 
 * @author Jonah Austin
 * @since Friday February 23, 2018
 * @param none
 * 
 */

public class Goal extends Entity implements Renderable, Collidable {

	protected int teamIndex; //We can implement for checking it against a team index using int rather than a bool for the possibility of having more than 2 teams in the future
	protected CVector size;
	
	public Goal(CVector pos, int team) {
		size = new CVector(165, 500);
		teamIndex = team;
		position = pos.plus(new CVector((teamIndex == 0) ? size.getValue(0) : -size.getValue(0), 0)); //This is temporary, based off of a 2-team set up
	}
	
	@Override
	public void render(Renderer r) {
		//r.fill(this.rectBounds(), Color.gray); //This is for testing collisions with the goal
	}

	@Override
	public Rect rectBounds() {
		return new Rect(this.position, size);
	}

	@Override
	public Poly polyBounds() {
		return rectBounds().polyBounds();
	}
	
	@Override
	public void onCollision(PVector newVelocity, Entity object) {
		if (object instanceof Ball) {
			Ball ball = (Ball)object;
			System.out.println("Team " + teamIndex + " was just scored on by a player from Team " + ball.teamLastHit);
		}
	}

	@Override
	public Poly trigger() {
		Poly poly = polyBounds();
		poly.setPosition(position);
		return poly;
	}
}

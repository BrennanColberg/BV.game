package bv.sportsGame.game.entities;

import java.awt.Color;

import bv.framework.core.Core;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.PVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Collidable;
import bv.framework.physics.Entity;
import bv.framework.sprites.AnimatedSprite;
import bv.framework.sprites.SpriteIO;
import bv.sportsGame.game.entities.classes.Team;


/**
 * This is the class that controls what happens with the goals.
 * 
 * @author Jonah Austin
 * @since Friday February 23, 2018
 * @param none
 * 
 */

public class Goal extends Entity implements Renderable, Collidable {

	protected Team team;
	protected CVector size;
	AnimatedSprite goalArea = SpriteIO.get("square").scale(300);
	
	public Goal(CVector pos, Team team) {
		size = new CVector(165, 500);
		this.team = team;
		position = pos.plus(new CVector((team == Team.RIGHT) ? size.getValue(0) : -size.getValue(0), 0)); //This is temporary, based off of a 2-team set up
	}
	
	@Override
	public void render(Renderer r) {
		//r.fill(this.rectBounds(), Color.gray); //This is for testing collisions with the goal
		goalArea.render(r, new CVector(-Core.STARTING_SCREEN_SIZE.getValue(0) * 2,0), 0.0, Color.white);
		goalArea.render(r, new CVector(Core.STARTING_SCREEN_SIZE.getValue(0)* 2,0), Math.PI, Color.white);
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
			team.score++;
		}
	}

	@Override
	public Poly trigger() {
		Poly poly = polyBounds();
		poly.setOffset(position);
		return poly;
	}
	
}

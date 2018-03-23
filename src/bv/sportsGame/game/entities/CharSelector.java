package bv.sportsGame.game.entities;

import java.awt.Color;
import java.awt.event.KeyEvent;

import bv.framework.core.Core;
import bv.framework.core.Input;
import bv.framework.graphics.Renderable;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.math.PVector;
import bv.framework.math.Poly;
import bv.framework.math.Rect;
import bv.framework.physics.Collidable;
import bv.framework.physics.Entity;
import bv.framework.physics.Physics;
import bv.framework.sprites.AnimatedSprite;
import bv.framework.sprites.Sprite;
import bv.sportsGame.game.entities.classes.BasicClass;
import bv.sportsGame.game.entities.classes.Team;
import bv.sportsGame.gameStates.Game;

public class CharSelector extends BasicClass implements Renderable, Collidable, Physics {

	public AnimatedSprite sprite;
	public Sprite tSprite;
	
	public CharSelector(AnimatedSprite sprite, Sprite tSprite, CVector position) {
		super(position, Team.LEFT, true, false);
		this.sprite = sprite;
		this.tSprite = tSprite;
		this.position = position;
		maxVelocity = 1.5d;
	}
	
	@Override
	public void updatePhysics() {
		if (Input.isKeyPressed(KeyEvent.VK_SPACE) && this.velocity.getMagnitude() < maxVelocity)
			acceleration.add(new PVector(accelAmount, mouseAngle()));
		
		checkDrag();
		velocity.clamp(-maxVelocity, maxVelocity);
		super.updatePhysics();
	}
	
	@Override
	public void onCollision(PVector newVelocity, Entity object) {
		if (object instanceof CharSelection) {
			CharSelection selection = (CharSelection) object;
			sprite = selection.sprite;
			BasicClass.playerClass = selection.classType;
			Core.gameStateManager.loadGameState(new Game());
		}
	}

	@Override
	public Poly trigger() {
		Poly poly = polyBounds().clone(); //poly derived from clone of polyBounds in order to avoid manipulating the actual polyBounds' offset value
		poly.setOffset(position); //Offsets the cloned poly by the position in order to calculate collisions from the player's position and not 0,0
		return poly;
	}

	@Override
	public void render(Renderer r) {
		sprite.render(r, position, 1, velocity.getAngle(), Color.blue);
	}

	@Override
	public Rect rectBounds() {
		return polyBounds().rectBounds();
	}

	@Override
	public Poly polyBounds() {
		return sprite.polyBounds();
	}

}

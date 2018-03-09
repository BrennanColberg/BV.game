package bv.framework.sprites;

import java.awt.Color;
import java.util.ArrayList;

import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.state.Tickable;

@SuppressWarnings("serial")
public class Sprite extends ArrayList<SpriteFrame> implements Tickable {

	public int index = 0;
	public double scale = 1;
	public double heading = 0;
	public Color color = Color.MAGENTA;
	
	public Sprite(SpriteFrame...frames) {
		for (SpriteFrame sf:frames) {
			this.add(sf);
		}
	}
	
	public void tick() {
		index++;
	}
	
	public void render(Renderer r, CVector position, double scale, double heading, Color color) {
		this.get(index % this.size()).render(r, position, scale, heading, color);
	}
	public void render(Renderer r, CVector position, double scale, double heading) {
		this.render(r, position, scale, heading, this.color);
	}
	public void render(Renderer r, CVector position, Color color) {
		this.render(r, position, this.scale, this.heading, color);
	}
	public void render(Renderer r, CVector position) {
		this.render(r, position, this.scale, this.heading, this.color);
	}

}

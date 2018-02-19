package bv.gui;

import java.awt.Color;

import bv.gameFramework.graphics.Renderable;
import bv.gameFramework.graphics.Renderer;
import bv.gameFramework.spritesCore.Sprite;
import bv.gameFramework.spritesCore.SpriteIO;
import bv.math.CVector;
import bv.math.Poly;
import bv.math.Rect;

public class FieldObject implements Renderable{
	Sprite square = SpriteIO.get("Square").scale(100);
	@Override
	public void render(Renderer r) {
		// TODO Auto-generated method stub
		square.render(r, new CVector(-200,0), 0.0, Color.black);
	}

	@Override
	public Rect rectBounds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Poly polyBounds() {
		// TODO Auto-generated method stub
		return null;
	}

}

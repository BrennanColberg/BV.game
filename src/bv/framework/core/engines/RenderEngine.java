/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
package bv.framework.core.engines;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;

import bv.framework.core.Core;
import bv.framework.core.Display;
import bv.framework.graphics.Renderer;
import bv.math.CVector;

/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
public class RenderEngine extends Engine {
	
	/* VARIABLES */
	public Display display;
	private Canvas canvas;
	public Renderer renderer;
	
	/* CONSTRUCTORS */
	public RenderEngine(double newTargetFrequency, CVector displaySize) {
		super(newTargetFrequency);
		canvas = new Canvas();
		renderer = new Renderer();
		display = new Display(displaySize, this.canvas);
	}
	
	/* METHODS */
	public void run() {
		display.setVisible(true);
		super.run();
	}
	public void trigger() {
		
		BufferStrategy bs = this.getCanvas().getBufferStrategy();
		if (bs == null) {
			this.getCanvas().createBufferStrategy(2);
			return;
		}
		
		renderer.setGraphics(bs.getDrawGraphics());
		renderer.updateBackground();
		Core.gameStateManager.render(renderer);

		renderer.getGraphics().dispose();
		bs.show();
	}
	
	
	/* GETTERS & SETTERS */
	public Display getDisplay() {
		return this.display;
	}
	public void setDisplay(Display newDisplay) {
		this.display = newDisplay;
		newDisplay.setCanvas(this.getCanvas());
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	public void setCanvas(Canvas newCanvas) {
		this.canvas = newCanvas;
		this.display.setCanvas(newCanvas);
	}
	
}

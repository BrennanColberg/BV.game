/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
package bv.gameFramework.core;

import java.awt.Canvas;
import java.awt.image.BufferStrategy;

import bv.gameFramework.graphics.Renderer;
import bv.math.CVector;
import bv.syntax.BV;

/** 
 * @author	Brennan Colberg
 * @since	Dec 17, 2017
 */
public class RenderEngine extends Thread {
	
	/* VARIABLES */
	
	public Display display;
	private Canvas canvas;
	public Renderer renderer;
	
	private double targetFPS, realFPS;
	
	
	/* CONSTRUCTORS */
	
	public RenderEngine(double newTargetFPS, CVector displaySize) {
		canvas = new Canvas();
		renderer = new Renderer();
		display = new Display(displaySize, this.canvas);
		setTargetFPS(newTargetFPS);
	}
	
	
	/* METHODS */
	
	public void run() {
		
		display.setVisible(true);
		
		long currentTime = System.currentTimeMillis();
		long timeOfLastFrame = currentTime, timeOfLastUpdate = currentTime;

		double frames = 0;

		while (Core.running && Core.rendering) {

			currentTime = System.currentTimeMillis();

			if (currentTime - timeOfLastFrame >= 1d / this.getTargetFPS() * 1000d) {
				render();
				frames += 1;
				timeOfLastFrame += 1d / this.getTargetFPS() * 1000d;
			}

			if (currentTime - timeOfLastUpdate >= Core.engineUpdateDelay) {
				setRealFPS((double) frames / (double) (currentTime - timeOfLastUpdate) * 1000d);
				if (Core.printEngineUpdatesToConsole == true) 
					BV.println(String.format("! FPS: %s of %s", getRealFPS(), getTargetFPS()));
				frames = 0;
				timeOfLastUpdate += Core.engineUpdateDelay;
			}
		}
	}
	public void render() {
		
		BufferStrategy bs = this.getCanvas().getBufferStrategy();
		if (bs == null) {
			this.getCanvas().createBufferStrategy(2);
			return;
		}
		
		renderer.setGraphics(bs.getDrawGraphics());
		renderer.updateBackground();
		Core.gameStateManager.currentState.render(renderer);

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
	
	public double getTargetFPS() {
		return this.targetFPS;
	}
	public void setTargetFPS(double newTargetFPS) {
		this.targetFPS = newTargetFPS;
	}
	
	public double getRealFPS() {
		return this.realFPS;
	}
	private void setRealFPS(double newRealFPS) {
		this.realFPS = newRealFPS;
	}
}

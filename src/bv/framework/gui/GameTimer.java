package bv.framework.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;

public class GameTimer implements ActionListener {
	
	private CVector position;
	private NumberCharacters[] digits;
	private int secondsLeft; //This is what is specifically counted down
	private Timer timer;
	
	public GameTimer(CVector pos, int startingTime) {
		position = pos;
		digits = new NumberCharacters[4];
		secondsLeft = startingTime;
		timer = new Timer(1000, (ActionListener) this);
		timer.start();
		updateDigits();
	}
	
	//This is just used for centering the digits on the screen
	private CVector leftBoundPosition() {
		int digitsWidth = 20; //The starting amount for this is equal to the number of paces inbetween the digits times the amount of space between them in order to get the total space between digits
		for (NumberCharacters n : digits) {
			digitsWidth += n.width;
		}
		return new CVector(position.getValue(0) - digitsWidth / 2, position.getValue(1));
	}
	
	public void renderDigits(Renderer r) {
		for (int i = 0; i < 4; i++) {
			digits[i].sprite.render(r, leftBoundPosition().plus(new CVector(80 * i)), 0, Color.white);
		}
	}
	
	private void updateDigits() {
		int minute = Math.floorDiv(secondsLeft, 60);
		int secondsTens = Math.floorDiv(secondsLeft % 60, 10);
		int secondsOnes = (secondsLeft % 60) - secondsTens * 10;
		digits[0] = NumberCharacters.getCharacter(minute);
		digits[1] = NumberCharacters.COLON;
		digits[2] = NumberCharacters.getCharacter(secondsTens);
		digits[3] = NumberCharacters.getCharacter(secondsOnes);
	}
	
	//This method is what is called every second by the timer in order to update the game timer
	@Override
	public void actionPerformed(ActionEvent a) {
		secondsLeft--;
		if (secondsLeft <= 0) {
			//The round has ended - time is up
		}
		updateDigits();
	}

}

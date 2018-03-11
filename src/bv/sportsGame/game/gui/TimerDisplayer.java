package bv.sportsGame.game.gui;

import java.awt.Color;

import bv.framework.core.Core;
import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;
import bv.framework.physics.Entity;
import bv.framework.sprites.TextSprite;
import bv.framework.state.Tickable;

public class TimerDisplayer extends Entity implements Tickable {
	
	private TextSprite[] digits; //B- not good to have this as global variable
	private double secondsLeft; //This is what is specifically counted down
	
	public TimerDisplayer(CVector position, int startingTime) {
		this.position = position;
		this.digits = new TextSprite[5];
		this.secondsLeft = startingTime;
		updateDigits();
	}
	
	//This is just used for centering the digits on the screen
	private CVector leftBoundPosition() {
		int digitsWidth = 45; //The starting amount for this is equal to the CharSprite of spaces in between the digits times the amount of space between them in order to get the total space between digits
		for (TextSprite n : digits) {
			digitsWidth += n.width(1);
		}
		return new CVector(position.getValue(0) - digitsWidth / 2, position.getValue(1));
	}
	
	public void renderDigits(Renderer r) {
		CVector posAdjustment = new CVector(0, 0);
		for (int i = 0; i < 4; i++) {
			posAdjustment.add(new CVector(digits[i].width(1) / 2, 0));
			digits[i].size(10).render(r, leftBoundPosition().plus(posAdjustment), Color.white);
			posAdjustment.add(new CVector(digits[i].width(1) / 2 + 15, 0));
		}
	}
	
	private void updateDigits() {
		// old Jonah code
//		int minute = Math.floorDiv(secondsLeft, 60);
//		int secondsTens = Math.floorDiv(secondsLeft % 60, 10); //B: problem here ... if double digit, you're screwed
//		int secondsOnes = (secondsLeft % 60) - secondsTens * 10;
		
		// converting to Integer to have access to .toString() later on
		Integer seconds = (int) secondsLeft;
		Integer minutes = ((int) secondsLeft) / 60; // same as floorDiv, more readable
		
		/* turning minutes CharSprite into a string, then using that string to find chars from each place */
		String minuteString = minutes.toString();
		// using if operator for first digit; basically, if string is not 2 digits long then display a zero
		char minuteTensChar = minuteString.length() < 2 ? 0 : minuteString.charAt(minuteString.length() - 2);
		char minuteOnesChar = minuteString.charAt(minuteString.length() - 1);
		 
		/* turning seconds CharSprite into string then into chars */
		String secondString = seconds.toString();
		// using if operator for first digit; basically, if string is not 2 digits long then display a zero
		char secondTensChar = secondString.length() < 2 ? 0 : secondString.charAt(secondString.length() - 2);
		char secondOnesChar = secondString.charAt(secondString.length() - 1);
		
		// getting CharSprite class for each calculated character
		// will display...  MM:SS  ...where M is a min digit and S is a sec digit
		digits[0] = TextSprite.fromCharacter(minuteTensChar);
		digits[1] = TextSprite.fromCharacter(minuteOnesChar);
		digits[2] = TextSprite.fromCharacter(':');
		digits[3] = TextSprite.fromCharacter(secondTensChar);
		digits[4] = TextSprite.fromCharacter(secondOnesChar);
	}
	
	//This method is what is called every second by the timer in order to update the game timer
	public void tick() {
		secondsLeft -= 1d / Core.tickEngine.targetFrequency;
		if (secondsLeft <= 0) {
			// STOP
		}
		updateDigits();
	}

}

package bv.framework.gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import bv.framework.graphics.Renderer;
import bv.framework.math.CVector;

public class GameTimer implements ActionListener {
	
	private CVector position;
	private Number[] digits; //B- not good to have this as global variable
	private int secondsLeft; //This is what is specifically counted down
	private Timer timer; //B- let's use ticks, I'll fix (hey that rhymes)
	
	public GameTimer(CVector pos, int startingTime) {
		position = pos;
		digits = new Number[5];
		secondsLeft = startingTime;
		
		// let's not do this ... we have ticks for a reason and this will make it hella complicated to pause/play. Will fix later -B
		timer = new Timer(1000, (ActionListener) this);
		timer.start();
		
		updateDigits();
	}
	
	public void setPosition(CVector position) {
		this.position = position;
	}
	
	//This is just used for centering the digits on the screen
	private CVector leftBoundPosition() {
		int digitsWidth = 45; //The starting amount for this is equal to the number of spaces in between the digits times the amount of space between them in order to get the total space between digits
		for (Number n : digits) {
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
		Integer seconds = secondsLeft;
		Integer minutes = secondsLeft / 60; // same as floorDiv, more readable
		
		/* turning minutes number into a string, then using that string to find chars from each place */
		String minuteString = minutes.toString();
		// using if operator for first digit; basically, if string is not 2 digits long then display a zero
		char minuteTensChar = minuteString.length() < 2 ? 0 : minuteString.charAt(minuteString.length() - 2);
		char minuteOnesChar = minuteString.charAt(minuteString.length() - 1);
		 
		/* turning seconds number into string then into chars */
		String secondString = seconds.toString();
		// using if operator for first digit; basically, if string is not 2 digits long then display a zero
		char secondTensChar = secondString.length() < 2 ? 0 : secondString.charAt(secondString.length() - 2);
		char secondOnesChar = secondString.charAt(secondString.length() - 1);
		
		// getting Number class for each calculated character
		// will display...  MM:SS  ...where M is a min digit and S is a sec digit
		digits[0] = Number.fromCharacter(minuteTensChar);
		digits[1] = Number.fromCharacter(minuteOnesChar);
		digits[2] = Number.fromCharacter(':');
		digits[3] = Number.fromCharacter(secondTensChar);
		digits[4] = Number.fromCharacter(secondOnesChar);
	}
	
	//This method is what is called every second by the timer in order to update the game timer
	public void actionPerformed(ActionEvent a) {
		secondsLeft--;
		if (secondsLeft <= 0) {
			//The round has ended - time is up
		}
		updateDigits();
	}

}

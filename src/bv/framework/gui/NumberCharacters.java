package bv.framework.gui;

import bv.framework.sprites.Sprite;
import bv.framework.sprites.SpriteIO;

public enum NumberCharacters {
	ZERO (SpriteIO.get("Number_0").scaleNew(10), 7),
	ONE (SpriteIO.get("Number_1").scaleNew(10), 2),
	TWO (SpriteIO.get("Number_2").scaleNew(10), 7),
	THREE (SpriteIO.get("Number_3").scaleNew(10), 7),
	FOUR (SpriteIO.get("Number_4").scaleNew(10), 7),
	FIVE (SpriteIO.get("Number_5").scaleNew(10), 7),
	SIX (SpriteIO.get("Number_6").scaleNew(10), 7),
	SEVEN (SpriteIO.get("Number_7").scaleNew(10), 7),
	EIGHT (SpriteIO.get("Number_8").scaleNew(10), 7),
	NINE (SpriteIO.get("Number_9").scaleNew(10), 7),
	DECIMAL (SpriteIO.get("Number_DecimalPoint").scaleNew(10), 2),
	COLON (SpriteIO.get("Text_Colon").scaleNew(10), 2);

	public Sprite sprite;
	public int width;
	private NumberCharacters(Sprite sprite, int width) {
		this.sprite = sprite;
		this.width = width * 10;
	}
	
	public static NumberCharacters getCharacter(int index) {
		return NumberCharacters.values()[index];
	}
}

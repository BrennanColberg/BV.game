package bv.framework.gui;

import bv.framework.math.CVector;
import bv.framework.math.Poly;
import bv.framework.sprites.SpriteFrame;
import bv.framework.sprites.SpriteIO;

public enum Number {
	ZERO	(0, '0'),
	ONE		(1, '1'),
	TWO		(2, '2'),
	THREE	(3, '3'),
	FOUR	(4, '4'),
	FIVE	(5, '5'),
	SIX		(6, '6'),
	SEVEN	(7, '7'),
	EIGHT	(8, '8'),
	NINE	(9, '9'),
	
	COLON	(10, ':'),
	DECIMAL	(11, '.');
	
	public static final double SPACE_TO_HEIGHT_RATIO = 0.25;
	
	private SpriteFrame sprite;
	private double width;
	private char character;
	
	public SpriteFrame size(double size) { return sprite.scaleNew(size); }
	public double width(double size) { return width * size; }
	
	private Number(int index, char character) {
		this.sprite = SpriteIO.get("numberCharacters").get(index);
		this.width = sprite.rectBounds().getSize().getValue(0);
		this.character = character;
	}
	
	public static Number fromCharacter(char character) {
		for (Number n:Number.values()) 
			if (n.character == character)
				return n;
		return null;
	}
	
	public static SpriteFrame fromString(String string, double size) {
		char[] chars = string.toCharArray();
		SpriteFrame result = new SpriteFrame();
//		double width = 0;
		
//		for (char c:chars) {
//			switch(c) {
//			case ' ': width += size * SPACE_TO_HEIGHT_RATIO; break;
//			default: width += fromCharacter(c).width(size); break;
//			}
//		}
		
		CVector cursor = new CVector(0,0);
		for (char c:chars) {
			switch(c) {
			case ' ': 
				cursor.add(new CVector(SPACE_TO_HEIGHT_RATIO, 0));
				break;
			default:
				Poly newCharPoly = fromCharacter(c).sprite.get(0);
				cursor.add(new CVector(newCharPoly.getPosition().getValue(0), 0));
				result.add(newCharPoly);
				cursor.add(new CVector(newCharPoly.getPosition().getValue(0), 0));
				break;
			}
		}
		
		return result;
	}
}

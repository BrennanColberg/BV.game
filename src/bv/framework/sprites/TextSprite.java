package bv.framework.sprites;

import bv.framework.math.CVector;
import bv.framework.math.Poly;

public enum TextSprite {
	
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
	DECIMAL	(11, '.'),
	
	A (12, 'A'),
	B (13, 'B'),
	C (14, 'C');
	
	public static final double SPACE_SIZE = 0.5;
	public static final double GAP_SIZE = 0.125;
	
	
	public Sprite sprite;
	private double width;
	private char character;
	
	public Sprite getSprite() { return sprite.clone(); }
	public double getWidth() { return width; }
	
	private TextSprite(int index, char character) {
		this.sprite = SpriteIO.get("numberCharacters").get(index);
		
		// NECESSARY! now all are 1 unit high, enabling "height" inputs to be pixel-accurate
		double height = this.sprite.rectBounds().getSize().getValue(1);
		for (Poly p:sprite) p.scale(1/height);
		
		this.width = sprite.rectBounds().getSize().getValue(0); // for some reason this needs to be halved. no idea, don't touch it
		this.character = character;
	}
	
	public static TextSprite fromCharacter(char character) {
		for (TextSprite n:TextSprite.values()) 
			if (n.character == character)
				return n;
		return null;
	}
	public static Sprite spriteFromCharacter(char character) {
		TextSprite ts = fromCharacter(character);
		return (ts == null) ? null : ts.getSprite();
	}
	
	public static Sprite spriteFromString(String string) {
		
		Sprite result = new Sprite();
		
		// needs to keep track of the total width of the string
		// used to track where to place each new letter
		double width = 0;
		
		for (char c : string.toCharArray()) {
			switch(c) {
			case ' ':
				if (result.size() != 0) 
					width += GAP_SIZE;
				width += SPACE_SIZE;
				break;
			default:
				TextSprite charObj = TextSprite.fromCharacter(c);
				if (charObj == null) break;
				Poly charPoly = charObj.sprite.get(0).clone();
				
				if (result.size() != 0) width += GAP_SIZE;
				
				charPoly.setOffset(new CVector(width + charObj.width * 0.5, 0));
				result.add(charPoly);
				
				width += charObj.width;
				
				break;
			}
		}
		
		// hacky way to center result sprite on position
		for (Poly p:result) p.setOffset(p.getOffset().minus(new CVector(width/2, 0)));
		
		return result;
	}
}

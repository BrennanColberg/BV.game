package bv.framework.sprites;

import java.util.HashMap;

import bv.framework.io.FOLDER;
import bv.framework.io.IO;
import bv.framework.io.TXT;
import bv.framework.math.CVector;
import bv.framework.math.PVector;
import bv.framework.math.Poly;
import bv.framework.syntax.BV;

public class SpriteIO {
	
	private static final String BASE_PATH = "sprites";
	
	private static HashMap<String,Sprite> spriteLibrary = new HashMap<String,Sprite>();

	public static void load() {
		BV.println("Loading Sprites!");
		String[] files = FOLDER.read(BASE_PATH);
		for (int i = 0; i < files.length; i++) {
			String SPRITE_PATH = files[i];
			switch (IO.fileTypeOf(SPRITE_PATH)) {
				case FOLDER: spriteLibrary.put(SPRITE_PATH, loadSprite(BASE_PATH + "/" + SPRITE_PATH)); break;
				case TXT: 
//					BV.println("Sorry! Can't load this TXT file: " + SPRITE_PATH); 
					break;
				default: break;
			}
		}
	}
	private static Sprite loadSprite(String SPRITE_PATH) {
		BV.println(SPRITE_PATH + " [Sprite]");
		Sprite result = new Sprite();
		String[] frames = FOLDER.read(SPRITE_PATH);
		for (int i = 0; i < frames.length; i++) {
			String FRAME_PATH = frames[i];
			switch (IO.fileTypeOf(FRAME_PATH)) {
				case FOLDER: result.add(loadSpriteFrame(SPRITE_PATH + "/" + FRAME_PATH)); break;
				case TXT: BV.println(SPRITE_PATH + "/" + FRAME_PATH + " [Sprite Settings]"); break;
				default: break;
			}
		}
		return result;
	}
	private static SpriteFrame loadSpriteFrame(String FRAME_PATH) {
		BV.println(FRAME_PATH + " [Frame]");
		SpriteFrame result = new SpriteFrame();
		String[] polies = FOLDER.read(FRAME_PATH);
		for (int i = 0; i < polies.length; i++) {
			String POLY_PATH = polies[i];
			switch (IO.fileTypeOf(POLY_PATH)) {
				case FOLDER: break;
				case TXT: result.add(loadPoly(FRAME_PATH + "/" + POLY_PATH)); break;
				default: break;
			}
		}
		return result;
	}
	private static Poly loadPoly(String POLY_PATH) {
		BV.println(POLY_PATH + " [Poly]");
		Poly result = new Poly();
		String[] lines = TXT.read(POLY_PATH);
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].toUpperCase();
			String[] word = line.split(" ");
			switch (word[0]) {
			
				case "POSITION":
					if (word.length < 3) break;
					result.setPosition(new CVector(Double.parseDouble(word[1]), Double.parseDouble(word[2])));
					break;
			
				case "POINT":
				case "CVECTOR":
				case "C":
					if (word.length < 3) break;
					result.addPoint(new CVector(Double.parseDouble(word[1]), Double.parseDouble(word[2])));
					break;
					
				case "PVECTOR":
				case "P":
					if (word.length < 3) break;
					result.addPoint(new PVector(Double.parseDouble(word[1]), Double.parseDouble(word[2])));
					break;
					
				case "SCALE":
					if (word.length < 2) break;
					result.scale(Double.parseDouble(word[1]));
					break;
					
				case "ROTATE":
					if (word.length < 2) break;
					if (word.length < 3) { result.rotate(Double.parseDouble(word[1])); break; }
					switch (word[2]) {
						case "RAD":
						case "RADIANS":
							result.rotate(Double.parseDouble(word[1]));
							break;
						case "DEG":
						case "DEGREES":
							result.rotate(Double.parseDouble(word[1]) * BV.PI / 180.0);
							break;
						case "P":
						case "PI":
							result.rotate(Double.parseDouble(word[1]) * BV.PI);
							break;
						case "2P":
						case "2PI":
						case "T":
						case "TAU":
						case "R":
						case "ROTATION":
						case "ROTATIONS":
							result.rotate(Double.parseDouble(word[1]) * BV.TAU);
							break;
						default: break;
					}
					break;
					
				case "SHADE":
					break;
					
				default: break;
			}
		}
		return result;
	}
	
	public static Sprite get(String path) {
		return spriteLibrary.get(path).clone();
	}
	
}

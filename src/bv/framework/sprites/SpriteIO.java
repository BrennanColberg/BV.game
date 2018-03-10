package bv.framework.sprites;

import java.util.ArrayList;
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
	
	private static HashMap<String,AnimatedSprite> spriteLibrary = new HashMap<String,AnimatedSprite>();

	public static void load() {
		BV.println("Loading Sprites!");
		String[] files = FOLDER.read(BASE_PATH);
		for (int i = 0; i < files.length; i++) {
			String path = files[i];
			switch (IO.fileTypeOf(path)) {
				case FOLDER: spriteLibrary.put(path, loadSprite(BASE_PATH + "/" + path)); break;
				case TXT: 
					spriteLibrary.put(path.substring(0, path.length()-4), new AnimatedSprite(new SpriteFrame(loadPoly(BASE_PATH + "/" + path))));
					break;
				default: break;
			}
		}
	}
	private static AnimatedSprite loadSprite(String path) {
		BV.println(path);
		AnimatedSprite result = new AnimatedSprite();
		String[] frames = FOLDER.read(path);
		for (int i = 0; i < frames.length; i++) {
			String file = frames[i];
			switch (IO.fileTypeOf(file)) {
				case FOLDER: result.add(loadSpriteFrame(path + "/" + file)); break;
				case TXT: 
					if (file.equals("settings.txt"));
//						BV.println(path + "/" + file); 
					else result.add(new SpriteFrame(loadPoly(path + "/" + file)));
					break;
				default: break;
			}
		}
		return result;
	}
	private static SpriteFrame loadSpriteFrame(String path) {
		BV.println(path);
		SpriteFrame result = new SpriteFrame();
		String[] polies = FOLDER.read(path);
		for (int i = 0; i < polies.length; i++) {
			String file = polies[i];
			switch (IO.fileTypeOf(file)) {
				case FOLDER: break;
				case TXT: result.add(loadPoly(path + "/" + file)); break;
				default: break;
			}
		}
		return result;
	}
	private static Poly[] loadPoly(String path) {
		BV.println(path);
		ArrayList<Poly> result = new ArrayList<Poly>();
		Poly poly = new Poly();
		String[] lines = TXT.read(path);
		for (int i = 0; i < lines.length; i++) {
			String line = lines[i].toUpperCase();
			String[] word = line.split(" ");
			switch (word[0]) {
			
				case "NEW":
					if (poly.getPoints().size() == 0) break;
					else {
						result.add(poly);
						poly = new Poly();
					}
					break;
			
				case "POSITION":
					if (word.length < 3) break;
					poly.setPosition(new CVector(Double.parseDouble(word[1]), Double.parseDouble(word[2])));
					break;
			
				case "POINT":
				case "CVECTOR":
				case "C":
					if (word.length < 3) break;
					poly.addPoint(new CVector(Double.parseDouble(word[1]), Double.parseDouble(word[2])));
					break;
					
				case "PVECTOR":
				case "P":
					if (word.length < 3) break;
					poly.addPoint(new PVector(Double.parseDouble(word[1]), Double.parseDouble(word[2])));
					break;
					
				case "SCALE":
					if (word.length < 2) break;
					poly.scale(Double.parseDouble(word[1]));
					break;
					
				case "ROTATE":
					if (word.length < 2) break;
					if (word.length < 3) { poly.rotate(Double.parseDouble(word[1])); break; }
					switch (word[2]) {
						case "RAD":
						case "RADIANS":
							poly.rotate(Double.parseDouble(word[1]));
							break;
						case "DEG":
						case "DEGREES":
							poly.rotate(Double.parseDouble(word[1]) * BV.PI / 180.0);
							break;
						case "P":
						case "PI":
							poly.rotate(Double.parseDouble(word[1]) * BV.PI);
							break;
						case "2P":
						case "2PI":
						case "T":
						case "TAU":
						case "R":
						case "ROTATION":
						case "ROTATIONS":
							poly.rotate(Double.parseDouble(word[1]) * BV.TAU);
							break;
						default: break;
					}
					break;
					
				case "SHADE":
					break;
					
				default: break;
			}
		}
		result.add(poly);
		return result.toArray(new Poly[]{});
	}
	
	public static AnimatedSprite get(String path) {
		return spriteLibrary.get(path).clone();
	}
	
}

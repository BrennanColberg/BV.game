package bv.framework.sprites;

import java.util.HashMap;

import bv.framework.io.FileType;
import bv.framework.io.IO;

public class SpriteIO {
	
	private static HashMap<String,Sprite> spriteLibrary = new HashMap<String,Sprite>();

	public static void load() {
		
	}
	
	public static Sprite get(String path) {
		return spriteLibrary.get(IO.correctPath(path, FileType.TXT)).clone();
	}
	
}

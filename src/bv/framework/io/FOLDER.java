/** 
 * @author	Brennan Colberg
 * @since	Nov 25, 2017
 */
package bv.framework.io;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import bv.framework.sprites.SpriteIO;

public abstract class FOLDER {
	
	public static String[] read(String path) { 
		try {
			ArrayList<String> result = new ArrayList<String>();
			String name;
			BufferedReader br = new BufferedReader(new InputStreamReader(SpriteIO.class.getClassLoader().getResourceAsStream(path)));
			while ((name = br.readLine()) != null) {
				result.add(name); // include path or not?
			}
			return result.toArray(new String[]{});
		} catch (IOException e) { return null; }
	}
	
}

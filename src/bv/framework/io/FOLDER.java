/** 
 * @author	Brennan Colberg
 * @since	Nov 25, 2017
 */
package bv.framework.io;
 
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class FOLDER {
	
	public static String[] read(String path) { 
		try {
			
			path = IO.correctPath(path, FileType.TXT);
			
			BufferedReader reader = new BufferedReader(new FileReader(path));
			ArrayList<String> text = new ArrayList<String>();
			
			while (reader.ready()) text.add(reader.readLine());
			
			reader.close();
			return text.toArray(new String[]{});
		
		} catch (IOException e) { return null; } 
	}
	
	public static void write(String path, String...input) {
		try {
			
			path = IO.correctPath(path, FileType.TXT);
			Files.write(Paths.get(path), Arrays.asList(input), Charset.forName("UTF-8"));
			
		} catch (IOException e) { }
	}

}

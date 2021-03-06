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

/** A command class designed to read from, write to, and generally manage raw text files. 
 * 	Use {@link FileType}.RawText to reference the corresponding file type.
 * @author	Brennan Colberg
 * @since	Nov 25, 2017
 */
public abstract class TXT {
	
	/** A static method used to read all lines of a designated raw text (".txt") file.
	 * @param path The document location path, in {@link String} form, from the project folder. May contain "src/", ".txt", both, or neither.
	 * @return A {@link String[]} array containing all lines of the requested text.
	 * @author	Brennan Colberg
	 * @since	Nov 26, 2017
	 */
	public static String[] read(String path) { 
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader("src/" + path));
			ArrayList<String> text = new ArrayList<String>();
			
			while (reader.ready()) text.add(reader.readLine());
			
			reader.close();
			return text.toArray(new String[]{});
		
		} catch (IOException e) { e.printStackTrace(); return null; } 
	}
	
	/** A static method used to clear all the lines of, and then write to, a designated raw text (".txt") file.
	 * @param path The document location path, in {@link String} form, from the project folder. May contain "src/", ".txt", both, or neither.
	 * @author	Brennan Colberg
	 * @since	Nov 26, 2017
	 */
	public static void write(String path, String...input) {
		try {
			
			Files.write(Paths.get(path), Arrays.asList(input), Charset.forName("UTF-8"));
			
		} catch (IOException e) { }
	}

}

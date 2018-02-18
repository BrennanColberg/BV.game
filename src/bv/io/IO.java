/** 
 * @author	Brennan Colberg
 * @since	Nov 25, 2017
 */
package bv.io;

/** A command class containing various commands used in BV.io for general IO.
 * @author	Brennan Colberg
 * @since	Nov 25, 2017
 */
public abstract class IO {
	
	/** A static method used to 'clean up' inputted data paths, which adds relevant prefixes and suffixes where necessary. Used whenever a path is used, to facilitate coding without technical perfection.
	 * @param path The raw path, as inputted.
	 * @param suffix The desired suffix, used to denote file type. For example, a raw text file would have ".txt" inputted here.
	 * @return The complete path in a usably correct form (with both "src/" prefix and designated suffix).
	 * @author	Brennan Colberg
	 * @since	Nov 26, 2017
	 */
	static String correctPath(String path, String suffix) { 
		if (path.split("/")[0] != "src") 
			path = "src/" + path;
		if (!suffix.startsWith("."))
			suffix = "." + suffix;
		if (!path.endsWith("suffix"))
			path = path + suffix;
		return path;
	}
	
	/** A static method used to 'clean up' inputted data paths, which adds relevant prefixes and suffixes where necessary. Used whenever a path is used, to facilitate coding without technical perfection.
	 * This version redirects to {@link correctPath(String, String)}, with the second parameter obtained from the enum {@link FileType}.
	 * @param path The raw path, as inputted.
	 * @param fileType The desired file type, referencing the {@FileType} enum.
	 * @return The complete path in a usably correct form (with both "src/" prefix and designated suffix).
	 * @author	Brennan Colberg
	 * @since	Nov 28, 2017
	 */
	public static String correctPath(String path, FileType fileType) { 
		return correctPath(path, fileType.suffix);
	}
	
}

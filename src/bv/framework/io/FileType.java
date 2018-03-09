/** 
 * @author	Brennan Colberg
 * @since	Nov 28, 2017
 */
package bv.framework.io;

/** An enum designed to facilitate IO coding; enhances the code readability of path and extension management.
 * @author	Brennan Colberg
 * @since	Nov 28, 2017
 */
public enum FileType {
	
	/** References a raw text (".txt") file
	 * Use the class {@link TXT} to manage such files.
	 * @author Brennan Colberg
	 * @since Nov 28, 2017
	 */
	TXT(".txt"),
	
	/** References an audio WAV (".wav") file
	 * Use the class {@link WAV} to manage such files.
	 * @author Brennan Colberg
	 * @since Nov 28, 2017
	 */
	WAV(".wav"),
	
	FOLDER("");
	
	public String suffix;
	private FileType(String suffix) {
		this.suffix = suffix;
	}
}

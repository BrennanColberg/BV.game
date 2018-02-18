/** 
 * @author	Brennan Colberg
 * @since	Dec 19, 2017
 */
package bv.math;

import bv.syntax.BV;

/** 
 * @author	Brennan Colberg
 * @since	Dec 19, 2017
 */
public class BMath extends BV {
	
	public static double hypot(double...sides) { double sum = 0; for (int i = 0; i < sides.length; i++) sum += Math.pow(sides[i],2); return Math.sqrt(sum); }
	public static double hypot(CVector cv) { return hypot(cv.getValues()); }
	
}

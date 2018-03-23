/** 
 * @author	Brennan Colberg
 * @since	Dec 19, 2017
 */
package bv.framework.syntax;

import bv.framework.math.CVector;
import bv.framework.math.PVector;
import bv.framework.physics.Entity;

/** 
 * @author	Brennan Colberg
 * @since	Dec 19, 2017
 */
public class BMath extends BV {
	
	public static double hypot(double...sides) { double sum = 0; for (int i = 0; i < sides.length; i++) sum += Math.pow(sides[i],2); return Math.sqrt(sum); }
	public static double hypot(CVector cv) { return hypot(cv.getValues()); }
	
	public static PVector[] collisionVelocity(Entity object1, Entity object2) {
		//This is the actual math for calculating velocities of objects after a collision. There are quite a few things wrong with this
		//One 'problem' being that the collision angle is calculated as if the two objects were circles
		//In practice this may cause problems or just look weird
		double m1 = object1.mass;
		double m2 = object2.mass;
		double v1 = object1.velocity.getMagnitude();
		double v2 = object2.velocity.getMagnitude();
		double theta1 = object1.velocity.getAngle();
		double theta2 = object2.velocity.getAngle();
		double phi = object2.position.minus(object1.position).toPVector().getAngle();

		//Rotate the cartesian plane so that it is a 1D collision problem
		double v1x = v1 * Math.cos(theta1 - phi);
		double v1y = v1 * Math.sin(theta1 - phi);
		
		double v2x = v2 * Math.cos(theta2 - phi);
		double v2y = v2 * Math.sin(theta2 - phi);
		
		//Calculate the new final velocity given the 1D formula:
		//u1x = ((m1 - m2)v1 + 2m2v2)/(m1 + m2)
		double u1x = ((m1 - m2) * v1x + 2 * m2 * v2x) / (m1 + m2);
		double u1y = v1y;
		CVector u1 = new CVector(u1x, u1y);
		PVector pu1 = u1.toPVector();
		pu1.setAngle(pu1.getAngle() + phi);
		
		double u2x = ((m2 - m1) * v2x + 2 * m1 * v1x) / (m2 + m1);
		double u2y = v2y;
		CVector u2 = new CVector(u2x, u2y);
		PVector pu2 = u2.toPVector();
		pu2.setAngle(pu2.getAngle() + phi);
		
		return new PVector[] {pu1, pu2};
	}
	
}

/** 
 * @author	Brennan Colberg
 * @since	Nov 25, 2017
 */
package bv.gameFramework.core;

import java.util.List;

/** A command class that simplifies many common methods and commands.
 * @author	Brennan Colberg
 * @since	Nov 25, 2017
 */
public abstract class BV {
	
	public static void println(String text) { System.out.println(text); }
	public static void print(String text) { System.out.print(text); }
	public static void printlnAll(String[] text) { for (String item:text) println(item); }
	public static void printlnAll(List<String> text) { for (String item:text) println(item); }
	public static void printAll(String[] text) { for (String item:text) print(item); }
	public static void printAll(List<String> text) { for (String item:text) print(item); }
	
	public static void errln(String text) { System.err.println(text); }
	public static void err(String text) { System.err.print(text); }
	public static void errlnAll(String[] text) { for (String item:text) errln(item); }
	public static void errlnAll(List<String> text) { for (String item:text) errln(item); }
	public static void errAll(String[] text) { for (String item:text) err(item); }
	public static void errAll(List<String> text) { for (String item:text) err(item); }
	
	public static String merge(String[] text, String join) { StringBuilder sb = new StringBuilder(); for (String s:text) { if (s != text[0]) sb.append(join); sb.append(s); } return sb.toString(); }
	
	public static int[] clone(int[] in) { int[] result = new int[in.length]; for (int i = 0; i < in.length; i++) result[i] = in[i]; return result; }
	public static long[] clone(long[] in) { long[] result = new long[in.length]; for (int i = 0; i < in.length; i++) result[i] = in[i]; return result; }
	public static float[] clone(float[] in) { float[] result = new float[in.length]; for (int i = 0; i < in.length; i++) result[i] = in[i]; return result; }
	public static double[] clone(double[] in) { double[] result = new double[in.length]; for (int i = 0; i < in.length; i++) result[i] = in[i]; return result; }
	
	
	public static int clamp(int value, int min, int max) { return Math.max(Math.min(value, max), min); }
	public static long clamp(long value, long min, long max) { return Math.max(Math.min(value, max), min); }
	public static float clamp(float value, float min, float max) { return Math.max(Math.min(value, max), min); }
	public static double clamp(double value, double min, double max) { return Math.max(Math.min(value, max), min); }
	
	public static boolean within(int value, int min, int max) { return BV.clamp(value, min, max) == value; }
	public static boolean within(long value, long min, long max) { return BV.clamp(value, min, max) == value; }
	public static boolean within(float value, float min, float max) { return BV.clamp(value, min, max) == value; }
	public static boolean within(double value, double min, double max) { return BV.clamp(value, min, max) == value; }
	
	public static void stop() { System.exit(0); }
	
	public static final double PI = Math.PI;
	public static final double TAU = 2 * Math.PI;
	
}

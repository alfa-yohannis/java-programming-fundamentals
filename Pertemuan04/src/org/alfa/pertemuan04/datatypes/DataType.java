package org.alfa.pertemuan04.datatypes;

import java.util.Arrays;
import java.util.List;

public class DataType {

	public static void main(String[] args) {

		/** Primitive Types **/
		boolean a = false;
		byte b = 1;
		short c = 1;
		int d = 1;
		long e = 1;
		float f = 1f;
		double g = 1d;
		char h = '1';

		/** Wrapper Classes **/
		Boolean i = false;
		Byte j = 1;
		Short k = 2;
		Integer l = 3;
		Long m = 4l;
		Float n = 5.9f;
		Double o = 6.9d;
		Character p = '1';

		String q = "1";

		/** (Auto)Boxing and (Auto)Unboxing **/
		Integer r1 = new Integer(3);
		Integer r2 = Integer.valueOf(3);
		Integer s = 3;
		int t = s;

		/** Advantages of using Wrapper Classes **/

		// primitives cant be null
//		double[] primitives = { b, c, d, f, h, null };

		// treat the values as objects
		Object[] boxedPrimitives = { i, null, n, p, q };
		List<Object> list = Arrays.asList(boxedPrimitives);

		// objects have methods
		System.out.println(l.compareTo(5));
		
		int  r = 3;
		Integer u = 3;
		System.out.println("x = " + u.compareTo(5));
		System.out.println("y = " + u.compareTo(2));
		System.out.println("z = " + u.compareTo(3));

	}

}

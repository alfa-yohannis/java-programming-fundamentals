package org.alfa.pertemuan04.datatypes;

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
		Integer r = new Integer(3); 
		Integer s = 3;
		int t = s;
		
		/** Advantages of using Wrapper Classes **/
	
//		double[] primitives = {a, b, f, h};
		
		// treat the values as objects
		Object[] boxedPrimitives = {i, l, n, p};

		// values have methods
		
		System.out.println(l.compareTo(3));
		
	}

}

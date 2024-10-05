package org.alfa.pertemuan04.datatypes;

public class TypeConversion {

	public static void main(String[] args) {
		
//		
//		byte x = Byte.MIN_VALUE;
//		int h = Byte.MAX_VALUE;
//
//		System.out.println("x = " + x);
//		System.out.println("h = " + h);
//
//		int a = Byte.MIN_VALUE - 3;
//		System.out.println("a = " + a);
//		System.out.println("a = " + (byte) a); //casting to byte
////
//		float c = 4.5f;
//		long b = 4;
//		double d = a + b + c;	
//		System.out.println("d = " + d); // implicit conversion
//		System.out.println("d = " + String.valueOf(d)); // explicit conversion
//		System.out.println("d = " + (int) d); // explicit conversion
//////
//		int z = Integer.parseInt("31");
//		System.out.println(z);
//		String y = String.valueOf(z);
//		System.out.println(y);
//		
		int r = 49;
		System.out.println(r);
		char s = (char) 49; // s = '1' 
		System.out.println(s);
		int t = (int) 'a'; 
		System.out.println(t);

	}

}

package org.alfa.pertemuan04.datatypes;

public class TypeConversion {

	public static void main(String[] args) {
		
		float c = 4.5f;
		int a = Byte.MAX_VALUE + 1;
		long b = 4;
		double d = a + b + c;
		
		System.out.println("d = " + d);
		System.out.println("d = " + String.valueOf(d));
		System.out.println("d = " + (int) d);

		System.out.println("a = " + (byte) a);
		
		int x = Integer.parseInt("31");
		System.out.println(x);
		
		int r = 49;
		System.out.println(r);
		char s = (char) 49;
		System.out.println(s);
		int t = (int) s;
		System.out.println(t);
		int u = Character.getNumericValue(s);
		System.out.println(u);
		

	}

}

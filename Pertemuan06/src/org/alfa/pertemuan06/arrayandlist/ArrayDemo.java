package org.alfa.pertemuan06.arrayandlist;

import java.util.Arrays;

public class ArrayDemo {

	public static void main(String[] args) {

		int[] a = null;
		int[] b = new int[] {};
		int[] c = { 1, 2, 3 };
		double[] d = new double[] { 1, 2, 3, 4 };
		double[] e = Arrays.copyOfRange(d, 1, d.length - 1);
		
		System.out.println(a);
		System.out.println(c.length);
		System.out.println(b.length);
		System.out.println(d.length);
		
		System.out.println();
		
		System.out.print(c[0]);
		System.out.print(", " + c[1]);
		System.out.print(", " + c[2]);
		
		System.out.println();
		System.out.println();
		
		int[] f = new int[3];
		f[2] = 99;
		f[1] = 100;
		f[0] = 31;
		System.out.print(f[0]);
		System.out.print(", " + f[1]);
		System.out.print(", " + f[2]);
		
		
		

	}

	

}

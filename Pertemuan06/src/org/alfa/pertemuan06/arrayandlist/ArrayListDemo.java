package org.alfa.pertemuan06.arrayandlist;

import java.util.ArrayList;
import java.util.Arrays;

public class ArrayListDemo {

	public static void main(String[] args) {

		ArrayList<Integer> a = null;
		ArrayList<Integer> b = new ArrayList<Integer>();
		ArrayList<Integer> c = new ArrayList<>(Arrays.asList(73, 101, 6));

		System.out.println(a);
		System.out.println(b);
		System.out.println(c);

		b.add(99);
		b.add(98);
		b.add(97);
		System.out.println(b);

		System.out.println("b's Size = " + b.size());

		b.remove(0);
		System.out.println(b);

		b.remove(97);
//		Integer z = 97;
//		b.remove(z);
		System.out.println(b);
		
		System.out.println("c(0) = " + c.get(0));
		System.out.println("c(2) = " + c.get(c.size() - 1));
		
		c.add(999, 0);
		System.out.println("c(0) = " + c.get(0));
		System.out.println("c(1) = " + c.get(1));
		
	}

}

package org.alfa.pertemuan07.looping;

public class PrimitiveLoop {

	public static void main(String[] args) {

//		executeWhileLoop(2, 1, 10);
//		System.out.println();

//		executeDoWhile(3, 1, 10);
//		System.out.println();
//		
//		executeForLoop(4, 1, 10);
//		System.out.println();
//		
		executeForIn(5, new int[] { 1, 2, 3, 4, 100, 6, 7, 8, 9, 10 });
	}

	public static void executeWhileLoop(int value, int from, int to) {
		while (from <= to) {
			System.out.println(value + " * " + from + " = " + (value * from));
			from++;
		}
	}

	public static void executeDoWhile(int value, int from, int to) {
		do {
			System.out.println(value + " * " + from + " = " + (value * from));
			from++;
		} while (from <= to);
	}

	public static void executeForLoop(int value, int from, int to) {
		for (int f = from ; f <= to ; f++) {
			System.out.println(value + " * " + f + " = " + (value * f));
		}
		
//		for ( ; from <= to ; from++) {
//			System.out.println(value + " * " + from + " = " + (value * from));
//		}
	}

	public static void executeForIn(int value, int[] array) {
		for (int element : array) {
			System.out.println(value + " * " + element + " = " + (value * element));
		}
	}

}

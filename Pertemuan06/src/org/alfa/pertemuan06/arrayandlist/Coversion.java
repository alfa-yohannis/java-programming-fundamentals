package org.alfa.pertemuan06.arrayandlist;

import java.util.ArrayList;
import java.util.Arrays;

public class Coversion {

	public static void main(String[] args) {

		// create a new list
		ArrayList<Integer> list = new ArrayList<>();
		list.add(3);
		list.add(2);
		list.add(1);
		System.out.println("List: " + list);

//		// list to array
		Integer[] array = list.toArray(new Integer[0]);
		System.out.println("Array 1: " + Arrays.toString(array));
////		
		Integer[] array2 = list.stream().toArray(Integer[]::new);
		System.out.println("Array 2: " + Arrays.toString(array2));
////		
		// array to list
		ArrayList<Integer> newList = new ArrayList<>(Arrays.asList(array));
		System.out.println("New List: " + list);
////
		int[] a = new int[list.size()];
		a[2] = list.get(0); 
		a[1] = list.get(1); 
		a[0] = list.get(2);
		System.out.println("Array a: " + Arrays.toString(a));
////		
		ArrayList<Integer> l = new ArrayList<>();
		l.add(a[2]);
		l.add(a[1]);
		l.add(a[0]);
		System.out.println("List l: " + l.toString());
	}

}

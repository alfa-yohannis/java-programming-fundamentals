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

		// list to array
		Integer[] array = list.toArray(new Integer[0]);
		System.out.println("Array 1: " + Arrays.toString(array));
		
		Integer[] array2 = list.stream().toArray(Integer[]::new);
		System.out.println("Array 2: " + Arrays.toString(array2));
		
		// array to list
		ArrayList<Integer> newList = new ArrayList<>(Arrays.asList(array));
		System.out.println("New List: " + list);

	}

}

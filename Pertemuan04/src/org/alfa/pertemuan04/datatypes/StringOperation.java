package org.alfa.pertemuan04.datatypes;

public class StringOperation {

	public static void main(String[] args) {
		
		String a = "Balonku ada seribu";
		System.out.println(a.substring(0, 3));
		System.out.println(a.toUpperCase());
		System.out.println(a.replace("a", "o"));
		System.out.println(a.contains("ada"));
		System.out.println(a.concat(" lima ratus"));
		
	}

}

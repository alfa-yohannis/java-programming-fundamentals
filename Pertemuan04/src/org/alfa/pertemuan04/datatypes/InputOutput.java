package org.alfa.pertemuan04.datatypes;

import java.util.Scanner;

public class InputOutput {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ketik nama Anda: ");
		String input = scanner.nextLine();
		System.out.println("Nama Anda adalah " + input);
		
		System.out.print("x = ");
		int x = scanner.nextInt();
		System.out.print("y = ");
		int y = scanner.nextInt();
		System.out.println("y + x = " + (y + x));
		
		
	}

}

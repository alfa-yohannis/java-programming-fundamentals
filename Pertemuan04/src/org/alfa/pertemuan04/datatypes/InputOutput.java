package org.alfa.pertemuan04.datatypes;

import java.util.Scanner;

public class InputOutput {
	
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Type your name: ");
		String input = scanner.nextLine();
		System.out.println("Your name is " + input);
		
		System.out.print("x = ");
		int x = scanner.nextInt();
		System.out.print("y = ");
		int y = scanner.nextInt();
		System.out.println("y + x = " + (y + x));
		
		boolean quit = false;
		String input = null;
		while(!quit) {
			System.out.print("Do you want to quit? (y/n) ");
			input = scanner.nextLine();
			if ("y".equals(input)) {
				quit = true;
			}
		}
		System.out.println("You have logged out!");
	}
}
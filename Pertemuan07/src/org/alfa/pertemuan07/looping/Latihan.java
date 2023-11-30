package org.alfa.pertemuan07.looping;



public class Latihan {
	
	public static void main (String[] args) {
		
		int start = 5;
		int end = 1;
		int i = start;
		while (i >= end) {
			int j = i;
			while (j >= end) {
				System.out.print(j);
				j--;
			}
			System.out.println();
			i--;
		}
		
	}
}
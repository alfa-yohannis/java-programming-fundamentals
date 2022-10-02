package org.alfa.pertemuan07.looping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NestedLoop {

	public static void main(String[] args) {

		executeNestedLoop(1, 10, 1, 10);
		System.out.println();

	}

	public static void executeNestedLoop(int startRow, int endRow, int fromCol, int toCol) {
		for (; startRow <= endRow; startRow++) {
			int temp = fromCol;
			while (fromCol <= toCol) {
				System.out.print(startRow + "x" + fromCol + "=" + (startRow * fromCol) + " ");
				fromCol++;
			}
			fromCol = temp;
			System.out.println();
		}
	}
}

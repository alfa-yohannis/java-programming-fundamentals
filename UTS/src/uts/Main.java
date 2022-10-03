package uts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	private static ArrayList<BankAccount> bankAccountList = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		ATM.initialise();
		ATM.start();
	}
	
}

package uts;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ATM {

	private static ArrayList<BankAccount> bankAccountList;
	private static Scanner scanner;

	public static void initialise() {
		bankAccountList = new ArrayList<>(
				Arrays.asList(new BankAccount(BankAccount.ADMIN_NUMBER, BankAccount.ADMIN_NAME, AccountType.ADMIN)));
		scanner = new Scanner(System.in);
	}

	public static void start() throws IOException {
		boolean exit = false;
		while (!exit) {
			System.out.println();
			System.out.println("=== Login ===");
			System.out.print("Account Number:");
			String accountNumber = scanner.nextLine();
			System.out.print("Password:");
			String password = scanner.nextLine();

			BankAccount bankAccount = null;
			if ((bankAccount = login(accountNumber, password)) == null) {
				System.out.println("Account tidak ditemukan atau password Anda salah!");
				System.out.println("Silahkan coba lagi ...");
				System.out.println();
			} else {
				if (AccountType.ADMIN == bankAccount.getAccountType()) {
					handleAdminMainMenu();
				} else {
					handleClientMainMenu(accountNumber);
				}
			}
		}

	}

	private static void handleClientMainMenu(String accountNumber) {

	}

	public static double transfer(String accountNumber, String targetNumber, double amount) {
		return -1;
	}

	public static double withdraw(String accountNumber, double amount) {
		return -1;
	}

	public static double deposit(String accountNumber, double amount) {
		return -1;
	}

	private static void handleAdminMainMenu() {

	}

	public static boolean updateBankAccount(String accountNumber, String name) {
		return false;
	}

	public static BankAccount getBankAccount(String accountNumber) {

		return null;
	}

	public static BankAccount login(String accountNumber, String password) {
		return null;
	}

	public static boolean addBankAccount(String accountNumber, String name) {
		return false;
	}

	public static boolean deleteBankAccount(String accountNumber) {
		return false;
	}

}

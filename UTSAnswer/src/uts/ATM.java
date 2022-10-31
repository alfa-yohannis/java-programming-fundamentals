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
		boolean quitClientMainMenu = false;
		while (!quitClientMainMenu) {
			System.out.println();
			System.out.println("=== Client Menu ===");
			System.out.println("1. Check Balance");
			System.out.println("2. Deposit");
			System.out.println("3. Withdraw");
			System.out.println("4. Transfer");
			System.out.println("0. Back");
			System.out.print("Pilihan: ");
			String option = scanner.nextLine();

			if ("1".equals(option)) {

				System.out.println();
				System.out.println("=== Client - Account Balance ===");
				BankAccount account = getBankAccount(accountNumber);
				System.out.println("Account Number: " + account.getAccountNumber());
				System.out.println("Name: " + account.getName());
				System.out.println("Balance: " + account.getBalance());

			} else if ("2".equals(option)) {

				System.out.println();
				System.out.println("=== Client - Deposit ===");
				BankAccount account = getBankAccount(accountNumber);
				System.out.println("Account Number: " + account.getAccountNumber());
				System.out.println("Name: " + account.getName());
				System.out.println("Balance: " + account.getBalance());
				System.out.println();
				System.out.print("Amount to deposit: ");
				double amount = scanner.nextDouble();
				scanner.nextLine();
				double newBalance = deposit(accountNumber, amount);
				System.out.println();
				System.out.println("Success! " + amount + " has been deposited into your account.");
				System.out.println("Your new balance is " + newBalance);

			} else if ("3".equals(option)) {

				System.out.println();
				System.out.println("=== Client - Withdraw ===");
				BankAccount account = getBankAccount(accountNumber);
				System.out.println("Account Number: " + account.getAccountNumber());
				System.out.println("Name: " + account.getName());
				System.out.println("Balance: " + account.getBalance());
				System.out.println();
				System.out.print("Amount to withdraw: ");
				double amount = scanner.nextDouble();
				scanner.nextLine();
				double newBalance = withdraw(accountNumber, amount);
				System.out.println();
				System.out.println("Success! " + amount + " has been withdrawn from your account.");
				System.out.println("Your new balance is " + newBalance);

			} else if ("4".equals(option)) {

				System.out.println();
				System.out.println("=== Client - Transfer ===");
				BankAccount account = getBankAccount(accountNumber);
				System.out.println("Account Number: " + account.getAccountNumber());
				System.out.println("Name: " + account.getName());
				System.out.println("Balance: " + account.getBalance());
				System.out.println();

				System.out.print("Type the receipient's account number: ");
				String targetNumber = scanner.nextLine();

				BankAccount targetAccount = getBankAccount(targetNumber);
				System.out.println("Recepient's Account Number: " + targetAccount.getAccountNumber());
				System.out.println("Recepient's Name: " + targetAccount.getName());
				System.out.println();
				
				System.out.print("Type the amount to transfer: ");
				double amount = scanner.nextDouble();
				scanner.nextLine();
				double newBalance = transfer(accountNumber, targetNumber, amount);
				System.out.println();
				System.out.println("Success! " + amount + " has been transferred");
				System.out.println("from account " + accountNumber + " to account " + targetNumber);
				System.out.println("Your new balance is " + newBalance);

			} else if ("0".equals(option)) {
				quitClientMainMenu = true;
			}

		}

	}

	public static double transfer(String accountNumber, String targetNumber, double amount) {
		BankAccount account = getBankAccount(accountNumber);
		double newBalance = account.withdraw(amount);
		
		BankAccount targetAccount = getBankAccount(targetNumber);
		targetAccount.save(amount);
		
		return newBalance;
	}

	public static double withdraw(String accountNumber, double amount) {
		BankAccount account = getBankAccount(accountNumber);
		double newBalance = account.withdraw(amount);
		return newBalance;
	}

	public static double deposit(String accountNumber, double amount) {
		BankAccount account = getBankAccount(accountNumber);
		double newBalance = account.save(amount);
		return newBalance;
	}

	private static void handleAdminMainMenu() {
		boolean quitAdminMainMenu = false;
		while (!quitAdminMainMenu) {
			System.out.println();
			System.out.println("=== Admin Menu ===");
			System.out.println("1. Add Account");
			System.out.println("2. Update Account");
			System.out.println("3. Delete Account");
			System.out.println("0. Back");
			System.out.print("Pilihan: ");
			String option = scanner.nextLine().trim();

			if ("1".equals(option)) {

				System.out.println();
				System.out.println("=== Admin - Add New Account ===");
				System.out.print("Account Number: ");
				String accountNumber = scanner.nextLine().trim();
				System.out.print("Name: ");
				String name = scanner.nextLine().trim();
				addBankAccount(accountNumber, name);

			} else if ("2".equals(option)) {

				System.out.println();
				System.out.println("=== Admin - Update Account ===");
				System.out.print("Account Number: ");
				String accountNumber = scanner.nextLine().trim();
				BankAccount account = getBankAccount(accountNumber);
				System.out.println("Current Name: " + account.getName());
				System.out.println("Balance: " + account.getBalance());
				System.out.println();
				System.out.print("Input Your New Name: ");
				String newName = scanner.nextLine().trim();
				updateBankAccount(accountNumber, newName);

			} else if ("3".equals(option)) {

				System.out.println();
				System.out.println("=== Admin - Delete Account ===");
				System.out.print("Account Number: ");
				String accountNumber = scanner.nextLine().trim();
				BankAccount account = getBankAccount(accountNumber);
				System.out.println("Name: " + account.getName());
				System.out.println("Balance: " + account.getBalance());
				deleteBankAccount(accountNumber);

			} else if ("0".equals(option)) {

				quitAdminMainMenu = true;
			}

		}
	}

	public static boolean updateBankAccount(String accountNumber, String name) {
		BankAccount account = getBankAccount(accountNumber);
		if (account == null) {
			System.out.println();
			System.out.println("Account with number '" + accountNumber //
					+ "' does not exist.");
			return false;
		} else {
			System.out.println();
			account.setName(name);
			System.out.println("account with number '" + account.getAccountNumber() //
					+ "' has been updated.");
			return true;
		}

	}

	public static BankAccount getBankAccount(String accountNumber) {
		for (BankAccount account : bankAccountList) {
			if (accountNumber.equals(account.getAccountNumber())) {
				return account;
			}
		}
		return null;
	}

	/***
	 * 
	 * @param accountNumber
	 * @param password
	 * @return
	 */
	public static BankAccount login(String accountNumber, String password) {
		BankAccount bankAccount = getBankAccount(accountNumber);
		if (bankAccount == null || !password.equals(bankAccount.getPassword())) {
			return null;
		} else {
			return bankAccount;
		}

	}

	public static boolean addBankAccount(String accountNumber, String name) {
		BankAccount account = new BankAccount(accountNumber, name);
		for (BankAccount ba : bankAccountList) {
			if (accountNumber.equals(ba.getAccountNumber())) {
				System.out.println();
				System.out.println("Account with number " + accountNumber + " exists.");
				System.out.println("Please choose another number.");
				return false;
			}
		}
		bankAccountList.add(account);
		System.out.println();
		System.out.println("New account with number '" + account.getAccountNumber() //
				+ "' and name '" + account.getName() + "' has been created.");
		return true;
	}

	public static boolean deleteBankAccount(String accountNumber) {
		BankAccount account = getBankAccount(accountNumber);
		if (account == null) {
			System.out.println();
			System.out.println("Account with number '" + accountNumber //
					+ "' does not exist.");
			return false;
		} else {
			System.out.println();
			System.out.println("account with number '" + account.getAccountNumber() //
					+ "' has been deleted.");
			return true;
		}
	}

}

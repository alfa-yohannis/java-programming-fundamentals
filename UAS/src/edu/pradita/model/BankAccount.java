package edu.pradita.model;

public class BankAccount {

	public static final String ADMIN_NUMBER = "1234"; // REPLACE THIS WITH YOUR NIM
	public static final String ADMIN_NAME = "Rendy"; // REPLACE THIS WITH YOUR NAME
	public static final String DEFAULT_PASSWORD = "1234";
	private AccountType accountType = AccountType.REGULAR;
	private String accountNumber;
	private String name;
	private String password = DEFAULT_PASSWORD;
	private double balance = 0.0;

	public void setName(String name) {
		this.name = name;
	}

	public BankAccount(String accountNumber, String name) {
		this.accountNumber = accountNumber;
		this.name = name;
	}

	public BankAccount(String accountNumber, String name, AccountType accountType) {
		this.accountNumber = accountNumber;
		this.name = name;
		this.accountType = accountType;
	}

	public double getBalance() {
		return this.balance;
	}

	public double save(double amount) {
		this.balance += amount;
		return 0;
	}

	public double withdraw(double amount) {
		this.balance -= amount;
		return 0;
	}

	public String getName() {
		return this.name;
	}

	public String getAccountNumber() {
		return this.accountNumber;
	}

	@Override
	public String toString() {
		return "{" + this.accountNumber + ", " + this.name + ", " + this.balance + "}";
	}

	public AccountType getAccountType() {
		return this.accountType;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
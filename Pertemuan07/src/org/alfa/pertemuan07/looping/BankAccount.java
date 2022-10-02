package org.alfa.pertemuan07.looping;

public class BankAccount {

	private String accountNumber;
	private String name;
	private double balance = 0.0;

	public BankAccount(String accountNumber, String name) {
		this.name = name;
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return this.balance;
	}

	public void save(double amount) {
		this.balance = this.balance + amount;
	}

	public void withdraw(double amount) {
		this.balance = this.balance - amount;
	}

	public String getName() {
		return name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	@Override
	public String toString() {
		return "{" + this.accountNumber + ", " + this.name + ", " + this.balance + "}";
	}
}

package com.bank;

public class BankAccount {

	private String accountNumber;
	private double balance;

	public BankAccount(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getAccountNumber() {
		return this.accountNumber;
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

}

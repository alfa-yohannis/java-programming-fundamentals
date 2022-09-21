package com.bank;

/***
 * A class that represents the bank account in the real world.
 * @author Alice
 * 
 */
public class BetterBankAccount {

	private String accountNumber;
	private double balance;

	/***
	 * 
	 * @param accountNumber the account number.
	 */
	public BetterBankAccount(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	/***
	 * The method return the number of the bank account in String.
	 * @return The account number.
	 */
	public String getAccountNumber() {
		return this.accountNumber;
	}

	/***
	 * Get the balance of the bank account.
	 * @return The balance of the account.
	 */
	public double getBalance() {
		return this.balance;
	}

	/***
	 * Add amount to the balance.  
	 * @param amount The amount to be saved.
	 */
	public void save(double amount) {
		this.balance = this.balance + amount;
	}

	/***
	 * Withdraw amount from the balance.
	 * @param amount The amount to be withdrawn.
	 */
	public void withdraw(double amount) {
		if (amount > 0) {
			this.balance = this.balance - amount;
		} else {
			System.out.println("WARNING: Amount should be larger than zero!");
		}
	}

}

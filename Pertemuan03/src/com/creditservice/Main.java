package com.creditservice;

import com.bank.BankAccount;
import com.bank.BetterBankAccount;

/***
 * The Main class to launch the program.
 * 
 * @author Bob
 */
public class Main {

	/***
	 * The main method to launch the program.
	 * 
	 * @param args Parameters for the main method.
	 */
	public static void main(String[] args) {
		

		BankAccount bankAccount = new BankAccount("100012");
		
		
		BetterBankAccount account1 = new BetterBankAccount("ABC123");
		System.out.println("Account number: " + account1.getAccountNumber());

		System.out.println("Initial: " + account1.getBalance());

		account1.save(100.0);
		System.out.println("After Saving: " + account1.getBalance());
//
		//account1.balance = 30.0;//
		
		account1.withdraw(10);
		System.out.println("After Withdrawal: " + account1.getBalance());
	}

}

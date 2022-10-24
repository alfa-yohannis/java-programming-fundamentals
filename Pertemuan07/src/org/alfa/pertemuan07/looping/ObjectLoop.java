package org.alfa.pertemuan07.looping;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ObjectLoop {

	public static void main(String[] args) {
		ArrayList<BankAccount> accountList = new ArrayList<>();
		accountList.add(new BankAccount("1001", "Alice"));
		accountList.get(accountList.size() - 1).save(100);
		accountList.add(new BankAccount("1002", "Bob"));
		accountList.get(accountList.size() - 1).save(200);
		accountList.add(new BankAccount("1003", "Charlie"));
		accountList.get(accountList.size() - 1).save(300);
		System.out.println(accountList);
		
		executeForIn(accountList);
		System.out.println();
		executeIterator(accountList);
		System.out.println();
		executeForEach(accountList);
		System.out.println();
//		
		System.out.println();
//		
		int i = 0;
		while (i < accountList.size()) {
			BankAccount ba = accountList.get(i);
			System.out.println(ba.getName());
			i++;
		}
		
		System.out.println();
		for (int k = 0; k < accountList.size(); k++) {
			BankAccount ba = accountList.get(k);
			System.out.println(ba.getName());
		}

	}

	public static void executeForIn(List<BankAccount> bankAccountList) {
		System.out.print("Account number: ");
		for (BankAccount bankAccount : bankAccountList) {
			System.out.print(bankAccount.getAccountNumber() + " ");
		}
	}

	public static void executeIterator(List<BankAccount> bankAccountList) {
		System.out.print("Name: ");
		Iterator<BankAccount> iterator = bankAccountList.iterator();
		while (iterator.hasNext()) {
			BankAccount bankAccount = iterator.next();
			System.out.print(bankAccount.getName() + " ");
		}
	}

	public static void executeForEach(List<BankAccount> bankAccountList) {
		System.out.print("Balance: ");
		bankAccountList.forEach(bankAccount -> {
			System.out.print(bankAccount.getBalance() + " ");
		});
	}

}

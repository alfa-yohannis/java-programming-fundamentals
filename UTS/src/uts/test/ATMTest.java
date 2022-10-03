package uts.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uts.ATM;
import uts.AccountType;
import uts.BankAccount;

class ATMTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetAdminAccount() throws IOException {
		ATM.initialise();
		BankAccount account = ATM.getBankAccount(BankAccount.ADMIN_NUMBER);
		assertEquals(account.getAccountType(), AccountType.ADMIN);
	}

	@Test
	void testAdminLoginSuccess() throws IOException {
		ATM.initialise();
		BankAccount bankAccount = ATM.login(BankAccount.ADMIN_NUMBER, BankAccount.DEFAULT_PASSWORD);
		assertEquals(bankAccount.getAccountType(), AccountType.ADMIN);
	}

	@Test
	void testLoginNoAccount() throws IOException {
		ATM.initialise();
		BankAccount bankAccount = ATM.login("ZZZZ", BankAccount.DEFAULT_PASSWORD);
		assertEquals(null, bankAccount);
	}

	@Test
	void testLoginWrongPassword() throws IOException {
		ATM.initialise();
		BankAccount bankAccount = ATM.login(BankAccount.ADMIN_NUMBER, "XXX");
		assertEquals(null, bankAccount);
	}
	
	@Test
	void testAddRegularAccount() throws IOException {
		ATM.initialise();
		ATM.addBankAccount("1112", "Bob Sponge");
		BankAccount bankAccount = ATM.getBankAccount("1112");
		assertEquals(bankAccount.getAccountNumber(), "1112");
	}

	@Test
	void testUpdateRegularAccount() throws IOException {
		ATM.initialise();
		ATM.addBankAccount("1112", "Bob Sponge");
		BankAccount bankAccount = ATM.getBankAccount("1112");
		assertEquals(bankAccount.getAccountNumber(), "1112");

		ATM.updateBankAccount("1112", "Sponge Bob");
		bankAccount = ATM.getBankAccount("1112");
		assertEquals("Sponge Bob", bankAccount.getName());
	}

	@Test
	void testDeleteRegularAccount() throws IOException {
		ATM.initialise();
		ATM.addBankAccount("1112", "Bob Sponge");
		BankAccount bankAccount = ATM.getBankAccount("1112");
		assertEquals(bankAccount.getAccountNumber(), "1112");
		
		boolean isDeleted = ATM.deleteBankAccount("1112");
		assertEquals(true, isDeleted);
	}

	@Test
	void testDeposit() throws IOException {
		ATM.initialise();
		ATM.addBankAccount("1112", "Bob Sponge");
		BankAccount bob = ATM.getBankAccount("1112");

		ATM.addBankAccount("1113", "Charlie Chaplin");
		BankAccount charlie = ATM.getBankAccount("1113");

		ATM.deposit(bob.getAccountNumber(), 200);
		ATM.deposit(charlie.getAccountNumber(), 100);

		bob = ATM.getBankAccount("1112");
		charlie = ATM.getBankAccount("1113");
		assertEquals(200, bob.getBalance());
		assertEquals(100, charlie.getBalance());

			}
	
	@Test
	void testWithdraw() throws IOException {
		ATM.initialise();
		ATM.addBankAccount("1112", "Bob Sponge");
		BankAccount bob = ATM.getBankAccount("1112");

		ATM.addBankAccount("1113", "Charlie Chaplin");
		BankAccount charlie = ATM.getBankAccount("1113");

		ATM.deposit(bob.getAccountNumber(), 200);
		ATM.deposit(charlie.getAccountNumber(), 100);

		bob = ATM.getBankAccount("1112");
		charlie = ATM.getBankAccount("1113");
		
		ATM.withdraw(bob.getAccountNumber(), 10);
		ATM.withdraw(charlie.getAccountNumber(), 20);

		bob = ATM.getBankAccount("1112");
		charlie = ATM.getBankAccount("1113");
		assertEquals(190, bob.getBalance());
		assertEquals(80, charlie.getBalance());
	}

	@Test
	void testTransfer() throws IOException {
		ATM.initialise();
		ATM.addBankAccount("1112", "Bob Sponge");
		BankAccount bob = ATM.getBankAccount("1112");

		ATM.addBankAccount("1113", "Charlie Chaplin");
		BankAccount charlie = ATM.getBankAccount("1113");

		ATM.deposit(bob.getAccountNumber(), 200);
		ATM.deposit(charlie.getAccountNumber(), 100);

		ATM.transfer(bob.getAccountNumber(), charlie.getAccountNumber(), 25);

		bob = ATM.getBankAccount("1112");
		charlie = ATM.getBankAccount("1113");

		assertEquals(175, bob.getBalance());
		assertEquals(125, charlie.getBalance());
	}


}

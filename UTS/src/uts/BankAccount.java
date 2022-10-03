package uts;

public class BankAccount {

	public static final String ADMIN_NUMBER = "1111"; // REPLACE THIS WITH YOUR NIM
	public static final String ADMIN_NAME = "admin"; // REPLACE THIS WITH YOUR NAME
	public static final String DEFAULT_PASSWORD = "1111";
	private AccountType accountType = AccountType.REGULAR;
	private String accountNumber;
	private String name;
	private String password = DEFAULT_PASSWORD;
	private double balance = 0.0;

	public void setName(String name) {

	}

	public BankAccount(String accountNumber, String name) {

	}

	public BankAccount(String accountNumber, String name, AccountType accountType) {

	}

	public double getBalance() {
		return -1;
	}

	public double save(double amount) {
		return -1;
	}

	public double withdraw(double amount) {
		return -1;
	}

	public String getName() {
		return null;
	}

	public String getAccountNumber() {
		return null;
	}

	@Override
	public String toString() {
		return "{" + this.accountNumber + ", " + this.name + ", " + this.balance + "}";
	}

	public AccountType getAccountType() {
		return null;
	}

	public String getPassword() {
		return null;
	}

	public void setPassword(String password) {
		
	}
}

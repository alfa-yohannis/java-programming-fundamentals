package edu.pradita.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ATM {

	private static ArrayList<BankAccount> bankAccountList;
	private static Scanner scanner;
	private static String pin = "";
	private static String accountNumber = "";

	
	public static String getPin() {
		return pin;
	}

	public static void setPin(String pin) {
		ATM.pin = pin;
	}

	public static String getAccountNumber() {
		return accountNumber;
	}

	public static void setAccountNumber(String accountNumber) {
		ATM.accountNumber = accountNumber;
	}

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
			System.out.print("Account Number : ");
			String accountNumber = scanner.nextLine();
			System.out.print("Password : ");
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
		boolean loop = true;

		BankAccount temp = getBankAccount(accountNumber);

		System.out.println();
		System.out.println("Welcome " + temp.getName());

		while (loop) {
			temp = getBankAccount(accountNumber);

			System.out.println("=== CLIENT - Menu ===");
			System.out.println("1. Check Balance");
			System.out.println("2. Deposit");
			System.out.println("3. Widthdraw");
			System.out.println("4. Transfer");
			System.out.println("x. kembali");

			System.out.print("Opsi : ");
			String option = scanner.nextLine();

			switch (option) {
			case "1":

				System.out.println();
				System.out.println("=== Checking Balance ===");
				System.out.println("Saldo : " + temp.getBalance());
				scanner.nextLine();

				System.out.print("Menu lain? (y/n) ");
				String answ1 = scanner.nextLine();

				if (answ1.equals("y")) {
					continue;
				} else if (answ1.equals("n")) {
					loop = false;
				}

				break;

			case "2":
				boolean loop_deposit = true;

				while (loop_deposit) {
					System.out.println();
					System.out.println("=== Deposit ===");
					System.out.print("Amount : ");
					String string_amount = scanner.nextLine();

					double amount = Double.parseDouble(string_amount);

					deposit(accountNumber, amount);

					System.out.print("Menu lain? (y/n) ");
					String answer_client_case_2 = scanner.nextLine();

					if (answer_client_case_2.equals("y")) {
						loop_deposit = false;
					} else if (answer_client_case_2.equals("n")) {
						loop_deposit = false;
						loop = false;
					}
				}

				break;

			case "3":
				boolean loop_widthdraw = true;

				while (loop_widthdraw) {
					System.out.println();
					System.out.println("=== Withdraw ===");
					System.out.print("Amount : ");
					String string_amount2 = scanner.nextLine();

					double amount2 = Double.parseDouble(string_amount2);

					withdraw(accountNumber, amount2);

					System.out.print("Menu lain? (y/n) ");
					String answer_client_case_3 = scanner.nextLine();

					if (answer_client_case_3.equals("y")) {
						loop_widthdraw = false;
					} else if (answer_client_case_3.equals("n")) {
						loop_widthdraw = false;
						loop = false;
					}
				}

				break;

			case "4":
				boolean loop_transfer = true;

				System.out.println();
				System.out.println("=== Transfer ===");

				while (loop_transfer) {
					System.out.print("Target Account Number : ");
					String toAccount = scanner.nextLine();

					BankAccount temp2 = getBankAccount(toAccount);

					if (temp2 != null && !temp.equals(temp2)) {
						System.out.println("Target Nama : " + temp2.getName());

						System.out.print("Amount : ");
						double amount3 = Double.parseDouble(scanner.nextLine());

						transfer(accountNumber, toAccount, amount3);
						loop_transfer = false;

					} else if (temp.equals(temp2)) {
						System.out.println("Tidak bisa transfer ke akun sendiri");
						continue;
					} else {
						scanner.nextLine();
						continue;
					}

					System.out.print("Menu lain? (y/n) ");
					String answer_transfer = scanner.nextLine();

					if (answer_transfer.equals("y")) {
						continue;
					} else if (answer_transfer.equals("n")) {
						loop = true;
						;
					}
				}

				break;

			case "x":
				loop = false;
				break;
			default:
				System.out.println("Opsi tidak ditemukan");
				break;

			}
		}
	}

	public static double transfer(String accountNumber, String targetNumber, double amount) {
		for (int i = 0; i < bankAccountList.size(); i++) {
			if (bankAccountList.get(i).getAccountNumber().equals(accountNumber)) {
				BankAccount temp_sender_account = getBankAccount(accountNumber);

				for (int j = 0; j < bankAccountList.size(); j++) {
					if (bankAccountList.get(j).getAccountNumber().equals(targetNumber)) {
						BankAccount temp_account = getBankAccount(targetNumber);
						boolean confirmed = false;

						while (!confirmed) {
							System.out.print("Transfer " + amount + " ke " + temp_account.getName() + "? (y/n) ");

							String confirm = scanner.nextLine();

							if (confirm.equals("y")) {
								temp_sender_account.withdraw(amount);
								bankAccountList.set(i, temp_sender_account);
								temp_account.save(amount);
								bankAccountList.set(j, temp_account);

								System.out.println("Transfer Success");
								System.out.println("Saldo anda sekarang : " + temp_sender_account.getBalance());
								scanner.nextLine();

								confirmed = true;
							} else if (confirm.equals("n")) {
								System.out.println("Transfer dibatalkan");
								scanner.nextLine();
								confirmed = true;
							} else {
								System.out.println("Lanjut y/n");
								System.out.println();
							}
						}
					}
				}
			}
		}

		return -1;
	}

	public static double withdraw(String accountNumber, double amount) {
		for (int i = 0; i < bankAccountList.size(); i++) {
			if (bankAccountList.get(i).getAccountNumber().equals(accountNumber)) {
				BankAccount temp_account = getBankAccount(accountNumber);

				boolean confirmed = false;
				while (!confirmed) {
					System.out.print("Withdraw " + amount + " dari akun bank anda? (y/n) ");
					String confirm = scanner.nextLine();

					if (confirm.equals("y")) {
						temp_account.withdraw(amount);
						bankAccountList.set(i, temp_account);
						System.out.println("Withdraw Berhasil");
						System.out.println("Saldo anda sekarang : " + temp_account.getBalance());
						scanner.nextLine();

						confirmed = true;
					} else if (confirm.equals("n")) {
						System.out.println("Withdraw Dibatalkan");
						scanner.nextLine();

						confirmed = true;
					} else {
						System.out.println("Lanjut y/n");
						System.out.println();
					}
				}
			}
		}

		return -1;
	}

	public static double deposit(String accountNumber, double amount) {
		for (int i = 0; i < bankAccountList.size(); i++) {
			if (bankAccountList.get(i).getAccountNumber().equals(accountNumber)) {
				BankAccount temp = getBankAccount(accountNumber);

				boolean confirmed = false;
				while (!confirmed) {
					System.out.print("Deposit " + amount + "? (y/n) ");
					String confirm = scanner.nextLine();

					if (confirm.equals("y")) {
						temp.save(amount);
						bankAccountList.set(i, temp);

						System.out.println("Saldo anda sekarang : " + temp.getBalance());
						scanner.nextLine();

						confirmed = true;
					} else if (confirm.equals("n")) {
						System.out.println("Deposit dibatalkan");
						scanner.nextLine();
						confirmed = true;
					} else {
						System.out.println("Lanjut y/n");
						System.out.println();
					}
				}
			}
		}

		return -1;
	}

	private static void handleAdminMainMenu() {
		boolean end = false;

		System.out.println();
		System.out.println("Login Sebagai ADMIN");

		while (!end) {
			boolean loop = true;

			System.out.println("=== ADMIN - Menu ===");
			System.out.println("1. Add new account");
			System.out.println("2. Update an account");
			System.out.println("3. Delete an account");
			System.out.println("x. kembali");

			System.out.print("Opsi : ");
			String option = scanner.nextLine();

			switch (option) {
			case "1":
				while (loop) {
					System.out.println();
					System.out.println("=== ADMIN - Add new account ===");
					System.out.println("\"x\" untuk kembali");
					System.out.print("Nama : ");
					String name = scanner.nextLine();

					if (name.equals("x")) {
						loop = false;
						break;
					}

					System.out.print("Account Number : ");
					String accountNumber1 = scanner.nextLine();

					if (!name.equals("") && !accountNumber1.equals("")) {
						boolean status_terima = addBankAccount(accountNumber1, name);

						if (status_terima) {
							System.out.println("Akun dengan nomor \"" + accountNumber1 + "\" dan nama \"" + name
									+ "\" berhasil ditambahkan");
							scanner.nextLine();
						} else {
							System.out.println("Akun sudah ada");
							scanner.nextLine();
						}

						System.out.print("Tambah akun lain? (y/n) ");
						String answer1 = scanner.nextLine();

						if (answer1.equals("y")) {
							continue;
						} else if (answer1.equals("n")) {
							loop = false;
							;
						}
					} else {
						System.out.println();
						System.out.println("Nama dan Account Number tidak boleh kosong");
						loop = true;
					}
				}

				break;

			case "2":
				while (loop) {
					System.out.println();
					System.out.println("=== ADMIN - Update account ===");
					System.out.println("\"x\" untuk kembali");
					System.out.print("Account Number : ");
					String accountNumber1 = scanner.nextLine();

					if (accountNumber1.equals("x")) {
						loop = false;
						break;
					}

					BankAccount temp = getBankAccount(accountNumber1);

					if (temp == null) {
						continue;
					}

					boolean loop1 = true;
					boolean loop_password = true;

					while (loop1) {
						System.out.println();
						System.out.println("Update akun " + temp.getName());
						System.out.println("1. Edit nama");
						System.out.println("2. Edit password");
						System.out.println("x. Kembali");
						System.out.print("Opsi : ");

						String option1 = scanner.nextLine();

						switch (option1) {
						case "1":
							System.out.println();
							System.out.print("Nama Baru : ");
							String newName = scanner.nextLine();

							String oldName = temp.getName();

							boolean status_terima = updateBankAccount(accountNumber1, newName);

							if (status_terima) {
								System.out.println("Berhasil mengupdate nama dari \"" + oldName + "\" menjadi \""
										+ newName + "\"");
							} else {
								System.out.println("Gagal update nama");
							}
							break;
						case "2":
							while (loop_password) {
								System.out.println();
								System.out.println("\"x\" untuk kembali");
								System.out.print("Password Sekarang : ");
								String password = scanner.nextLine();

								if (password.equals("x")) {
									loop_password = false;
								}

								if (temp.getPassword().equals(password)) {
									System.out.print("Password baru : ");
									String newPass = scanner.nextLine();

									for (int i = 0; i < bankAccountList.size(); i++) {
										if (bankAccountList.get(i).getAccountNumber().equals(accountNumber1)) {
											bankAccountList.get(i).setPassword(newPass);
										}
									}

									System.out.println("Password berhasil diubah");
									loop_password = false;

								} else {
									System.out.println("Password tidak sesuai");
								}
							}
							break;
						case "x":
							loop1 = false;
							loop = false;
							loop_password = false;
							break;
						default:
							System.out.println("Opsi tidak ditemukan");
							break;
						}
					}
				}

				break;

			case "3":
				while (loop) {
					System.out.println();
					System.out.println("=== ADMIN - Delete account ===");
					System.out.println("\"x\" untuk kembali");
					System.out.print("Account Number : ");
					String accountNumber2 = scanner.nextLine();

					boolean status_terima = deleteBankAccount(accountNumber2);

					if (accountNumber2.equals("x")) {
						loop = false;
						break;
					}

					if (!status_terima) {
						System.out.println("Account tidak ditemukan");
						loop = true;
					} else {
						System.out.print("Hapus akun lain? (y/n) ");
						String answer1 = scanner.nextLine();

						if (answer1.equals("y")) {
							loop = true;
						} else if (answer1.equals("n")) {
							loop = false;
						}
					}
				}
				break;

			case "x":
				try {
					start();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			default:
				System.out.println();
				System.out.println("Opsi tidak ditemukan");
				break;
			}

		}

	}

	public static boolean updateBankAccount(String accountNumber, String name) {
		for (int i = 0; i < bankAccountList.size(); i++) {
			if (bankAccountList.get(i).getAccountNumber().equals(accountNumber)) {
				bankAccountList.get(i).setName(name);
				return true;
			}
		}
		return false;
	}

	public static BankAccount getBankAccount(String accountNumber) {
		for (int i = 0; i < bankAccountList.size(); i++) {
			if (bankAccountList.get(i).getAccountNumber().equals(accountNumber)) {
				return bankAccountList.get(i);
			}
		}

		System.out.println("Account not Found");
		return null;
	}

	public static BankAccount login(String accountNumber, String password) {
		for (int i = 0; i < bankAccountList.size(); i++) {
			if (bankAccountList.get(i).getAccountNumber().equals(accountNumber)
					&& bankAccountList.get(i).getPassword().equals(password)) {
				return bankAccountList.get(i);
			}
		}

		return null;
	}

	public static boolean addBankAccount(String accountNumber, String name) {
		for (int i = 0; i < bankAccountList.size(); i++) {
			if (bankAccountList.get(i).getAccountNumber().equals(accountNumber)) {
				return false;
			}
		}

		boolean confirmed = false;
		BankAccount account = new BankAccount(accountNumber, name);

		while (confirmed == false) {
			System.out.print("Password : ");
			String password = scanner.nextLine();

			account.setPassword(password);
			bankAccountList.add(account);
			confirmed = true;
		}
		return true;
	}

	public static boolean deleteBankAccount(String accountNumber) {
		for (int i = 0; i < bankAccountList.size(); i++) {
			if (bankAccountList.get(i).getAccountNumber().equals(accountNumber)) {
				boolean confirmed = false;

				while (!confirmed) {
					if (bankAccountList.get(i).getAccountType().equals(AccountType.ADMIN)) {
						System.out.println("Tidak bisa menghapus akun admin");
						confirmed = true;
					} else {
						System.out.print("Hapus akun (" + bankAccountList.get(i).getName() + ") ? (y/n) ");
						String confirm = scanner.nextLine();

						if (confirm.equals("y")) {
							bankAccountList.remove(i);
							System.out.println("Akun dihapus");

							return true;
						} else if (confirm.equals("n")) {
							System.out.println("Gagal hapus akun");

							return false;
						} else {
							System.out.println("Lanjut y/n");
							System.out.println();
						}
					}
				}
				return true;
			}
		}
		return false;
	}
}

package edu.pradita.p10;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Data {

	private static List<User> users = new ArrayList<>();

	public static List<User> getUsers() {
		return users;
	}

	public static List<User> saveUsers(List<User> users) {
		try {
			Data.users.clear();
			Data.users.addAll(users);
			
			File file = new File("data.csv");
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			for (User user : Data.users) {
				bw.write(user.getUsername() + "," + user.getPassword() + System.lineSeparator());
			}

			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return users;
	}

	public static List<User> loadUsers() {
		try {
			File file = new File("data.csv");
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String line = br.readLine();
			while (line != null) {
				String[] lineArray = line.split(",");
				users.add(new User(lineArray[0].trim(), lineArray[1].trim()));
				line = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return users;
	}
}

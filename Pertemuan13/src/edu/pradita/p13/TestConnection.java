package edu.pradita.p13;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestConnection {
	
	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager //
					.getConnection("jdbc:mysql://localhost:3306/hello", "root", "1234");

			PreparedStatement statement = connection.prepareStatement("select * from item where code = ?");
			statement.setString(1, "AB001");
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				String code = resultSet.getString("code");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				double quantity = resultSet.getDouble("quantity");
				System.out.println(code + " " + name + " " + price + " " + ((int) quantity));

			}
			resultSet.close();
			statement.close();
			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}
}

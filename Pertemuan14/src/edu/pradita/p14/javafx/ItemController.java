package edu.pradita.p14.javafx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemController implements IForm{

	@FXML
	private TableView<Item> table;
	@FXML
	private TableColumn<Item, String> colCode;
	@FXML
	private TableColumn<Item, String> colName;
	@FXML
	private TableColumn<Item, Double> colPrice;
	@FXML
	private TableColumn<Item, Double> colQuantity;

	private ObservableList<Item> items = FXCollections.observableArrayList();

	public void initialize() {
		// Initialize cell value factories
		colCode.setCellValueFactory(cellData -> cellData.getValue().codeProperty());
		colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
		colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
		colQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

		// Set table items
		table.setItems(items);
		table.setEditable(true);

		// Make columns editable
		colCode.setCellFactory(TextFieldTableCell.forTableColumn());
		colName.setCellFactory(TextFieldTableCell.forTableColumn());
		colPrice.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
		colQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));

		// Set edit commit actions for each editable column
		colCode.setOnEditCommit(event -> {
			Item item = event.getRowValue();
			item.setCode(event.getNewValue());
			handleTableEdit(item, "code");
		});

		colName.setOnEditCommit(event -> {
			Item item = event.getRowValue();
			item.setName(event.getNewValue());
			handleTableEdit(item, "name");
		});

		colPrice.setOnEditCommit(event -> {
			Item item = event.getRowValue();
			item.setPrice(event.getNewValue());
			handleTableEdit(item, "price");
		});

		colQuantity.setOnEditCommit(event -> {
			Item item = event.getRowValue();
			item.setQuantity(event.getNewValue());
			handleTableEdit(item, "quantity");
		});

		// Load items into the table
		loadItems();
		
		items.add(new Item("", "", 0.0, 0.0));
	}

	private void loadItems() {
		try {
			String query = "SELECT code, name, price, quantity FROM item";
			PreparedStatement statement = MainController.CONNECTION.prepareStatement(query);
			ResultSet resultSet = statement.executeQuery();
			items.clear();

			while (resultSet.next()) {
				String code = resultSet.getString("code");
				String name = resultSet.getString("name");
				double price = resultSet.getDouble("price");
				double quantity = resultSet.getDouble("quantity");

				items.add(new Item(code, name, price, quantity));
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void handleTableEdit(Item item, String column) {
		try {
			String query = "UPDATE item SET code = ?, name = ?, price = ?, quantity = ? WHERE code = ?";
			PreparedStatement statement = MainController.CONNECTION.prepareStatement(query);
			statement.setString(1, item.getCode());
			statement.setString(2, item.getName());
			statement.setDouble(3, item.getPrice());
			statement.setDouble(4, item.getQuantity());
			statement.setString(5, item.getCode());

			if (statement.executeUpdate() == 0) {
				query = "INSERT INTO item(code, name, price, quantity) VALUES(?, ?, ?, ?)";
				statement = MainController.CONNECTION.prepareStatement(query);
				statement.setString(1, item.getCode());
				statement.setString(2, item.getName());
				statement.setDouble(3, item.getPrice());
				statement.setDouble(4, item.getQuantity());
				
			}
			
			if (items.size() == 0 || !"".equals(items.get(items.size() - 1).code.get().trim())) {
				items.add(new Item("", "", 0.0, 0.0));
			}

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void closeWindow() {
		table.getScene().getWindow().hide();
	}

	public static class Item {
		private final SimpleStringProperty code;
		private final SimpleStringProperty name;
		private final SimpleDoubleProperty price;
		private final SimpleDoubleProperty quantity;

		public Item(String code, String name, double price, double quantity) {
			this.code = new SimpleStringProperty(code);
			this.name = new SimpleStringProperty(name);
			this.price = new SimpleDoubleProperty(price);
			this.quantity = new SimpleDoubleProperty(quantity);
		}

		public String getCode() {
			return code.get();
		}

		public SimpleStringProperty codeProperty() {
			return code;
		}

		public void setCode(String code) {
			this.code.set(code);
		}

		public String getName() {
			return name.get();
		}

		public SimpleStringProperty nameProperty() {
			return name;
		}

		public void setName(String name) {
			this.name.set(name);
		}

		public double getPrice() {
			return price.get();
		}

		public SimpleDoubleProperty priceProperty() {
			return price;
		}

		public void setPrice(double price) {
			this.price.set(price);
		}

		public double getQuantity() {
			return quantity.get();
		}

		public SimpleDoubleProperty quantityProperty() {
			return quantity;
		}

		public void setQuantity(double quantity) {
			this.quantity.set(quantity);
		}
	}

	@Override
	public String getDocumentCode() {
		return null	;
	}
}

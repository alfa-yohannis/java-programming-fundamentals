package edu.pradita.p14.javafx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.*;

public class SalesOrderController {

    @FXML private TextField txtCode;
	@FXML
	private TextField txtDate;
	@FXML
	private TextField txtTotal;
    @FXML private TextArea txtNote;
    @FXML private TableView<OrderItem> table;
    @FXML private TableColumn<OrderItem, Integer> colLine;
    @FXML private TableColumn<OrderItem, String> colCode, colName;
    @FXML private TableColumn<OrderItem, Double> colPrice, colQuantity, colTotal;
    @FXML private Button btnAddItem, btnRemoveItem, btnConfirm;

    private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
    private boolean isAddMode = false;
    
	private static String currentCode;

    public void initialize() {
        colLine.setCellValueFactory(cellData -> cellData.getValue().lineProperty().asObject());
        colCode.setCellValueFactory(cellData -> cellData.getValue().itemCodeProperty());
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        colQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        colTotal.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());

        table.setItems(orderItems);

        

        displayLastOrder();
    }

    @FXML
    private void newOrder() {
        isAddMode = true;
        enableDisableElements();
        clearForm();
    }

    @FXML
    private void findOrder() {
        // Implement find order logic
    }

    @FXML
    private void displayFirstOrder() {
        try {
            PreparedStatement statement = MainController.CONNECTION.prepareStatement(
                    "SELECT * FROM `order` WHERE code = (SELECT MIN(code) FROM `order`)");
            displayOrder(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayPrevOrder() {
        try {
            PreparedStatement statement = MainController.CONNECTION.prepareStatement(
            	    "select * from `order` t1 where t1.code = (select max(code)  from `order` t2 where t2.code < ?) limit 1;");
            statement.setString(1, txtCode.getText());
            displayOrder(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayNextOrder() {
        try {
            PreparedStatement statement = MainController.CONNECTION.prepareStatement(
            	    "select * from `order` t1 where t1.code = (select min(code)  from `order` t2 where t2.code > ?) limit 1;");
            statement.setString(1, txtCode.getText());
            displayOrder(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayLastOrder() {
        try {
            PreparedStatement statement = MainController.CONNECTION.prepareStatement(
                    "SELECT * FROM `order` WHERE code = (SELECT MAX(code) FROM `order`)");
            displayOrder(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayOrder(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            currentCode = resultSet.getString("code");
            txtCode.setText(currentCode);
            txtDate.setText(resultSet.getString("date"));
            txtNote.setText(resultSet.getString("note"));
            loadOrderItems(currentCode);

            isAddMode = false;
            enableDisableElements();
        } 
        resultSet.close();
        statement.close();
    }

    private void loadOrderItems(String code) {
        orderItems.clear();
        try {
            PreparedStatement statement = MainController.CONNECTION.prepareStatement(
                    "SELECT line, itemcode, name, price, quantity, (quantity * price) AS total FROM `order_detail` WHERE code = ?");
            statement.setString(1, code);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                orderItems.add(new OrderItem(rs.getInt("line"), rs.getString("itemcode"),
                        rs.getString("name"), rs.getDouble("price"), rs.getDouble("quantity")));
            }
            rs.close();
            statement.close();
            calculateTotal();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void addItem() {
        if (isAddMode) {
            int line = orderItems.size() + 1;
            orderItems.add(new OrderItem(line, "ITEM001", "Sample Item", 100.0, 1));
            calculateTotal();
        }
    }

    @FXML
    private void removeItem() {
        if (isAddMode) {
            OrderItem selectedItem = table.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                orderItems.remove(selectedItem);
                calculateTotal();
            }
        }
    }

    @FXML
    private void confirmOrder() {
        if (isAddMode) {
            try {
                PreparedStatement stmt = MainController.CONNECTION.prepareStatement(
                        "INSERT INTO `order` (note) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, txtNote.getText());
                stmt.executeUpdate();
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    String newCode = generatedKeys.getString(1);
                    txtCode.setText(newCode);

                    for (OrderItem item : orderItems) {
                        PreparedStatement detailStmt = MainController.CONNECTION.prepareStatement(
                                "INSERT INTO `order_detail` (code, line, itemcode, name, price, quantity) " +
                                        "VALUES (?, ?, ?, ?, ?, ?)");
                        detailStmt.setString(1, newCode);
                        detailStmt.setInt(2, item.getLine());
                        detailStmt.setString(3, item.getItemCode());
                        detailStmt.setString(4, item.getName());
                        detailStmt.setDouble(5, item.getPrice());
                        detailStmt.setDouble(6, item.getQuantity());
                        detailStmt.executeUpdate();
                        detailStmt.close();
                    }
                    isAddMode = false;
                    enableDisableElements();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order saved successfully.");
                    alert.showAndWait();
                }
                generatedKeys.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void calculateTotal() {
        double total = orderItems.stream().mapToDouble(OrderItem::getTotal).sum();
        txtTotal.setText(String.format("%.2f", total));
    }

    private void enableDisableElements() {
        btnAddItem.setDisable(!isAddMode);
        btnRemoveItem.setDisable(!isAddMode);
        btnConfirm.setDisable(!isAddMode);
        txtNote.setEditable(isAddMode);
    }

    private void clearForm() {
        txtCode.clear();
        txtDate.clear();
        txtNote.clear();
        txtTotal.clear();
        orderItems.clear();
    }
    
    public static String getCurrentOrderCode() {
        try {
        	return currentCode;
//			return SalesOrderController.this.txtCode.getText();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // Assumes only one instance is in use
		return null;
    }
    
    public class OrderItem {
        private final SimpleIntegerProperty line;
        private final SimpleStringProperty itemCode;
        private final SimpleStringProperty name;
        private final SimpleDoubleProperty price;
        private final SimpleDoubleProperty quantity;
        private final SimpleDoubleProperty total;

        public OrderItem(int line, String itemCode, String name, double price, double quantity) {
            this.line = new SimpleIntegerProperty(line);
            this.itemCode = new SimpleStringProperty(itemCode);
            this.name = new SimpleStringProperty(name);
            this.price = new SimpleDoubleProperty(price);
            this.quantity = new SimpleDoubleProperty(quantity);
            this.total = new SimpleDoubleProperty(price * quantity);
        }

        // Getters
        public int getLine() {
            return line.get();
        }

        public String getItemCode() {
            return itemCode.get();
        }

        public String getName() {
            return name.get();
        }

        public double getPrice() {
            return price.get();
        }

        public double getQuantity() {
            return quantity.get();
        }

        public double getTotal() {
            return total.get();
        }

        // Setters for updating properties
        public void setQuantity(double quantity) {
            this.quantity.set(quantity);
            updateTotal();
        }

        public void setPrice(double price) {
            this.price.set(price);
            updateTotal();
        }

        // Property getters for JavaFX binding
        public SimpleIntegerProperty lineProperty() {
            return line;
        }

        public SimpleStringProperty itemCodeProperty() {
            return itemCode;
        }

        public SimpleStringProperty nameProperty() {
            return name;
        }

        public SimpleDoubleProperty priceProperty() {
            return price;
        }

        public SimpleDoubleProperty quantityProperty() {
            return quantity;
        }

        public SimpleDoubleProperty totalProperty() {
            return total;
        }

        // Private method to update the total when price or quantity changes
        private void updateTotal() {
            total.set(price.get() * quantity.get());
        }

    	public void setLine(int i) {
    		line.set(i);
    		
    	}
    }
}

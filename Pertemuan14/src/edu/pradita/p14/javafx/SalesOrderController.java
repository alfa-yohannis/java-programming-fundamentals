package edu.pradita.p14.javafx;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.sql.*;

public class SalesOrderController implements IForm {

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
	    
	    // Set colQuantity as editable
	    colQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
	    colQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new javafx.util.converter.DoubleStringConverter()));
	    colQuantity.setOnEditCommit(event -> {
	        OrderItem orderItem = event.getRowValue();
	        double newQuantity = event.getNewValue();
	        orderItem.setQuantity(newQuantity); // Update quantity in OrderItem
	        calculateTotal(); // Recalculate total after quantity change
	    });
	    
	    colTotal.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());

	    table.setItems(orderItems);
	    table.setEditable(true); // Enable editing in the table

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
            openSelectForm();
        }
    }

    private void openSelectForm() {
        try {
            // Load SelectForm.fxml
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SelectForm.fxml"));
            Parent root = loader.load();

            // Get the controller and set the callback
            SelectFormController controller = loader.getController();

            // Set query for SelectFormController to load items with quantity > 0
            String query = "SELECT code, name, price FROM item WHERE quantity > 0";
            controller.loadTableData(query); // Assuming loadItems(query) is a method in SelectFormController

            // Set the OnSelectListener to handle selected item data
            controller.setOnSelectListener(values -> {
                if (values != null && values.length > 0) {
                    String itemCode = (String) values[0];
                    String itemName = (String) values[1];
                    double itemPrice = ((Number) values[2]).doubleValue(); // Convert price to double
                    double quantity = 1.0; // Default quantity

                    int line = orderItems.size() + 1;
                    orderItems.add(new OrderItem(line, itemCode, itemName, itemPrice, quantity));
                    calculateTotal();
                }
            });

            // Show SelectForm in a new modal window
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Select Item");
            stage.setScene(new Scene(root));
            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
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
                // Step 1: Get the maximum existing order code and generate a new code
                PreparedStatement maxCodeStmt = MainController.CONNECTION.prepareStatement("SELECT MAX(code) AS code FROM `order`;");
                ResultSet maxCodeResultSet = maxCodeStmt.executeQuery();
                String maxCode = null;
                if (maxCodeResultSet.next()) {
                    maxCode = maxCodeResultSet.getString("code");
                }
                String newCode = maxCode != null ? String.valueOf(Integer.parseInt(maxCode) + 1) : "1";
                maxCodeResultSet.close();
                maxCodeStmt.close();

                // Step 2: Insert the new order record with the generated code
                PreparedStatement orderStmt = MainController.CONNECTION.prepareStatement(
                        "INSERT INTO `order` (code, note) VALUES (?, ?);");
                orderStmt.setString(1, newCode);
                orderStmt.setString(2, txtNote.getText());
                orderStmt.executeUpdate();
                orderStmt.close();

                // Step 3: Insert each OrderItem into the order_detail table
                for (OrderItem item : orderItems) {
                    PreparedStatement detailStmt = MainController.CONNECTION.prepareStatement(
                            "INSERT INTO `order_detail` (code, line, itemcode, name, price, quantity) VALUES (?, ?, ?, ?, ?, ?);");
                    detailStmt.setString(1, newCode);
                    detailStmt.setInt(2, item.getLine());
                    detailStmt.setString(3, item.getItemCode());
                    detailStmt.setString(4, item.getName());
                    detailStmt.setDouble(5, item.getPrice());
                    detailStmt.setDouble(6, item.getQuantity());
                    detailStmt.executeUpdate();
                    detailStmt.close();

                    // Step 4: Update the stock quantity in the item table
                    PreparedStatement updateStockStmt = MainController.CONNECTION.prepareStatement(
                            "UPDATE item SET quantity = quantity - ? WHERE code = ?;");
                    updateStockStmt.setDouble(1, item.getQuantity());
                    updateStockStmt.setString(2, item.getItemCode());
                    updateStockStmt.executeUpdate();
                    updateStockStmt.close();
                }

                // Step 5: Finalize the order and update the UI
                isAddMode = false;
                enableDisableElements();
                displayLastOrder(); // Display the last order to confirm the save

                // Show a success alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Order saved successfully.");
                alert.showAndWait();

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

	@Override
	public String getDocumentCode() {
		return txtCode.getText();
	}	
}

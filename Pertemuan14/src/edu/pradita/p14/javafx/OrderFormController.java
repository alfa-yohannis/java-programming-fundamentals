package edu.pradita.p14.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.eclipse.birt.core.framework.Platform;
import org.eclipse.birt.report.engine.api.*;

import java.awt.Desktop;
import java.io.File;
import java.sql.*;

public class OrderFormController {

    @FXML private TextField txtCode, txtDate, txtTotal;
    @FXML private TextArea txtNote;
    @FXML private TableView<OrderItem> table;
    @FXML private TableColumn<OrderItem, Integer> colLine;
    @FXML private TableColumn<OrderItem, String> colCode, colName;
    @FXML private TableColumn<OrderItem, Double> colPrice, colQuantity, colTotal;
    @FXML private Button btnAddItem, btnRemoveItem, btnConfirm;

    private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();
    private boolean isAddMode = false;

    public static Connection CONNECTION;

    public void initialize() {
        // Initialize columns
        colLine.setCellValueFactory(cellData -> cellData.getValue().lineProperty().asObject());
        colCode.setCellValueFactory(cellData -> cellData.getValue().itemCodeProperty());
        colName.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        colPrice.setCellValueFactory(cellData -> cellData.getValue().priceProperty().asObject());
        colQuantity.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());
        colTotal.setCellValueFactory(cellData -> cellData.getValue().totalProperty().asObject());

        // Bind items to the table
        table.setItems(orderItems);

        // Initialize database connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            CONNECTION = DriverManager.getConnection("jdbc:mysql://localhost:3306/pradita", "alfa", "1234");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Load the last order on startup
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
        // Implement find order logic (e.g., open a dialog to search for an order)
    }

    @FXML
    private void displayFirstOrder() {
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(
                    "SELECT * FROM `order` WHERE code = (SELECT MIN(code) FROM `order`)");
            displayOrder(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void displayPrevOrder() {
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(
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
            PreparedStatement statement = CONNECTION.prepareStatement(
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
            PreparedStatement statement = CONNECTION.prepareStatement(
                    "SELECT * FROM `order` WHERE code = (SELECT MAX(code) FROM `order`)");
            displayOrder(statement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayOrder(PreparedStatement statement) throws SQLException {
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            String code = resultSet.getString("code");
            txtCode.setText(code);
            txtDate.setText(resultSet.getString("date"));
            txtNote.setText(resultSet.getString("note"));

            loadOrderItems(code);

            isAddMode = false;
            enableDisableElements();
        } 
        resultSet.close();
        statement.close();
    }

    private void loadOrderItems(String code) {
        orderItems.clear();
        try {
            PreparedStatement statement = CONNECTION.prepareStatement(
                    "SELECT line, itemcode, name, price, quantity, (quantity * price) AS total FROM `order_detail` WHERE code = ?");
            statement.setString(1, code);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                int line = rs.getInt("line");
                String itemCode = rs.getString("itemcode");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                double quantity = rs.getDouble("quantity");

                orderItems.add(new OrderItem(line, itemCode, name, price, quantity));
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
            // Example item; in real code, prompt user to select an item
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
                for (int i = 0; i < orderItems.size(); i++) {
                    orderItems.get(i).setLine(i + 1);
                }
                calculateTotal();
            }
        }
    }

    @FXML
    private void confirmOrder() {
        if (isAddMode) {
            try {
                // Insert new order
                PreparedStatement stmt = CONNECTION.prepareStatement(
                        "INSERT INTO `order` (note) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, txtNote.getText());
                stmt.executeUpdate();
                ResultSet generatedKeys = stmt.getGeneratedKeys();
                if (generatedKeys.next()) {
                    String newCode = generatedKeys.getString(1);
                    txtCode.setText(newCode);

                    // Insert order details
                    for (OrderItem item : orderItems) {
                        PreparedStatement detailStmt = CONNECTION.prepareStatement(
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

                        // Update stock
                        PreparedStatement stockStmt = CONNECTION.prepareStatement(
                                "UPDATE item SET quantity = quantity - ? WHERE code = ?");
                        stockStmt.setDouble(1, item.getQuantity());
                        stockStmt.setString(2, item.getItemCode());
                        stockStmt.executeUpdate();
                        stockStmt.close();
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

    @FXML
    private void printReport() {
        try {
            EngineConfig config = new EngineConfig();
            Platform.startup(config);
            IReportEngineFactory factory = (IReportEngineFactory) Platform.createFactoryObject(
                    IReportEngineFactory.EXTENSION_REPORT_ENGINE_FACTORY);
            IReportEngine engine = factory.createReportEngine(config);

            IReportRunnable design = engine.openReportDesign("reports/test.rptdesign");
            IRunAndRenderTask task = engine.createRunAndRenderTask(design);
            task.setParameterValue("order_code", txtCode.getText());
            PDFRenderOption options = new PDFRenderOption();
            options.setOutputFileName("reports/test.pdf");
            options.setOutputFormat("pdf");

            task.setRenderOption(options);
            task.run();
            task.close();
            engine.destroy();

            if (Desktop.isDesktopSupported()) {
                File myFile = new File("reports/test.pdf");
                Desktop.getDesktop().open(myFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Platform.shutdown();
        }
    }

    @FXML
    private void openItemForm() {
        // Implement logic to open the ItemForm (e.g., a new dialog window)
    }

    @FXML
    private void showAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("Order Form Application");
        alert.setContentText("Developed with JavaFX and BIRT for report generation.");
        alert.showAndWait();
    }

    public void stop() {
        try {
            if (CONNECTION != null && !CONNECTION.isClosed()) {
                CONNECTION.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

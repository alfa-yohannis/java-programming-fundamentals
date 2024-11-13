package edu.pradita.p14.javafx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SelectFormController {

    @FXML
    private TableView<Object[]> table;
    @FXML
    private TextField textField;

    private ObservableList<Object[]> tableData = FXCollections.observableArrayList();
    private OnSelectListener onSelectListener;
    private String baseQuery;
    private List<Integer> columnTypes = new ArrayList<>(); // Store column types for dynamic filtering

    public void initialize() {
        // Initialization logic can be left empty or used for other setup if needed
    }

    public void loadTableData(String query) {
        baseQuery = query; // Store the base query for future searches
        executeQuery(baseQuery); // Load initial data
    }

    private void executeQuery(String query) {
        try {
            PreparedStatement statement = MainController.CONNECTION.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metadata = resultSet.getMetaData();
            int colCount = metadata.getColumnCount();

            // Clear existing columns, data, and column types
            table.getColumns().clear();
            tableData.clear();
            columnTypes.clear();

            // Create columns dynamically based on ResultSetMetaData
            for (int i = 1; i <= colCount; i++) {
                final int colIndex = i - 1;
                String colName = metadata.getColumnName(i);
                int colType = metadata.getColumnType(i);

                // Store the column type for later use in search filters
                columnTypes.add(colType);

                TableColumn<Object[], String> column = new TableColumn<>(colName);
                column.setCellValueFactory(cellData ->
                        new SimpleStringProperty(cellData.getValue()[colIndex] != null ? cellData.getValue()[colIndex].toString() : "")
                );

                table.getColumns().add(column);
            }

            // Populate table data
            while (resultSet.next()) {
                Object[] row = new Object[colCount];
                for (int i = 1; i <= colCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                tableData.add(row);
            }
            resultSet.close();
            statement.close();

            table.setItems(tableData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onFind() {
        String searchText = textField.getText().trim();
        if (searchText.isEmpty()) {
            // If search text is empty, reload the original query
            executeQuery(baseQuery);
            return;
        }

        // Build search query dynamically based on column types
        StringBuilder searchQuery = new StringBuilder(baseQuery + " WHERE ");
        List<String> conditions = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        try {
            for (int i = 0; i < columnTypes.size(); i++) {
                int colType = columnTypes.get(i);
                
                switch (colType) {
                    case Types.VARCHAR:
                    case Types.CHAR:
                    case Types.LONGVARCHAR:
                        // For text columns, use LIKE
                        conditions.add("column" + (i + 1) + " LIKE ?");
                        params.add("%" + searchText + "%");
                        break;

                    case Types.INTEGER:
                    case Types.BIGINT:
                    case Types.DECIMAL:
                    case Types.NUMERIC:
                    case Types.FLOAT:
                    case Types.DOUBLE:
                        // For numeric columns, use '=' if input can be parsed to a number
                        try {
                            double numericValue = Double.parseDouble(searchText);
                            conditions.add("column" + (i + 1) + " = ?");
                            params.add(numericValue);
                        } catch (NumberFormatException ignored) {
                            // Skip numeric columns if the search text is not a number
                        }
                        break;

                    default:
                        // Other types can be handled as needed
                        break;
                }
            }

            if (conditions.isEmpty()) {
                // If no valid conditions were built, return without executing the query
                return;
            }

            // Join all conditions with "OR" and execute the search query
            searchQuery.append(String.join(" OR ", conditions));
            PreparedStatement statement = MainController.CONNECTION.prepareStatement(searchQuery.toString());

            // Set parameters in the statement
            for (int i = 0; i < params.size(); i++) {
                statement.setObject(i + 1, params.get(i));
            }

            ResultSet resultSet = statement.executeQuery();
            ResultSetMetaData metadata = resultSet.getMetaData();
            int colCount = metadata.getColumnCount();

            tableData.clear();
            while (resultSet.next()) {
                Object[] row = new Object[colCount];
                for (int i = 1; i <= colCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                tableData.add(row);
            }

            resultSet.close();
            statement.close();

            table.setItems(tableData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onCancel() {
        Stage stage = (Stage) table.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onSelect() {
        Object[] selectedRow = table.getSelectionModel().getSelectedItem();
        if (selectedRow != null && onSelectListener != null) {
            onSelectListener.select(selectedRow);
        }
        onCancel();
    }

    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    public interface OnSelectListener {
        void select(Object[] values);
    }
}
	
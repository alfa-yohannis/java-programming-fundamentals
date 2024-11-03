package edu.pradita.p14.javafx;

import javafx.beans.property.*;

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

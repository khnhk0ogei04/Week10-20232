package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;

import Entity.Fruit;
import Entity.Order;
import Entity.Customer;
import Manager.Utils;

public class OrderController {
	@FXML
	private TextField customerField;
    @FXML
    private TextField idField;
    @FXML
    private TextField quantityField;

    private ArrayList<Fruit> fruitData = CreateFruitController.getFruitData();
    private static Hashtable<String, ArrayList<Order>> orderHistory = new Hashtable<>();
    private static ArrayList<String> customerList = new ArrayList<>();
    public static Hashtable<String, ArrayList<Order>> getOrderHistory(){
    	return orderHistory;
    }
    public static ArrayList<String> getCustomerList() {
        return customerList;
    }
    @FXML
    private void Order(ActionEvent event) {
    	String customerName = customerField.getText();
    	if (!Utils.checkInputString2(customerName)) {
    		return;
    	}
        String fruitId = idField.getText();
        Fruit selectedFruit = null;
        for (Fruit fruit : fruitData) {
            if (fruit.getFruitId().equalsIgnoreCase(fruitId)) {
                selectedFruit = fruit;
                break;
            }
        }

        if (selectedFruit == null) {
            showAlert("Item not found!", AlertType.INFORMATION);
            return;
        }
        int quantity;
        try {
            quantity = Integer.parseInt(quantityField.getText());
            if (quantity <= 0) {
                showAlert("Invalid quantity, the numbers of quantity must be positive integer!", AlertType.WARNING);
                return;
            }
            if (quantity > selectedFruit.getQuantity()) {
            	showAlert("Sorry, my shop is having only " + selectedFruit.getQuantity() + " " + selectedFruit.getFruitName(), AlertType.WARNING);
            	return;
            }
            selectedFruit.setQuantity(selectedFruit.getQuantity() - quantity);
            } catch (NumberFormatException e) {
            	showAlert("Invalid quantity, please try again", AlertType.WARNING);
            	return;
            }
        Order newOrder = new Order(fruitId, selectedFruit.getFruitName(), quantity, selectedFruit.getPrice());
        ArrayList<Order> customerOrders = orderHistory.getOrDefault(customerName, new ArrayList<>());
        customerOrders.add(newOrder);
        orderHistory.put(customerName, customerOrders);
        showAlert("Order placed successfully!", AlertType.CONFIRMATION);
        if (!customerList.contains(customerName)) {
            customerList.add(customerName);
            System.out.println("Customer list updated: " + getCustomerList());
        }
    }


    private void showAlert(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setContentText(message);
        alert.show();
    }
}

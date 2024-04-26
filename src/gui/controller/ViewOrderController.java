package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.beans.property.SimpleStringProperty;
import javafx.application.Platform;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.ArrayList;

import Entity.Order;

public class ViewOrderController {
    @FXML
    private ComboBox<String> combobox;
    @FXML
    private TableView<Order> tableView;
    @FXML
    private TableColumn<Order, String> idOrderColumn;
    @FXML
    private TableColumn<Order, String> fruitNameColumn;
    @FXML
    private TableColumn<Order, Integer> quantityColumn;
    @FXML
    private TableColumn<Order, Double> priceColumn;
    @FXML
    private TableColumn<Order, Double> totalColumn;
    @FXML
    private TextField sumAll;
    @FXML
    private void updateListCustomer() {
        ArrayList<String> customerList = OrderController.getCustomerList();
        ObservableList<String> currentItems = combobox.getItems();
        if (!currentItems.equals(FXCollections.observableArrayList(customerList))) {
            Platform.runLater(() -> {
                combobox.getItems().clear();
                combobox.getItems().addAll(customerList);
            });
        }
    }

    public void initialize() {
        idOrderColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFruitId()));
        fruitNameColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getFruitName()));
        quantityColumn.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getQuantity()).asObject());
        priceColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrice()).asObject());
        totalColumn.setCellValueFactory(data -> new SimpleDoubleProperty(data.getValue().getPrice() * data.getValue().getQuantity()).asObject());

        combobox.setItems(FXCollections.observableArrayList(OrderController.getCustomerList()));

        combobox.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                combobox.setValue(newValue);
                updateOrderTable(newValue); 
            }
        });

    }

    private void updateOrderTable(String customerName) {
        if (customerName != null && OrderController.getOrderHistory().containsKey(customerName)) {
            ObservableList<Order> orders = FXCollections.observableArrayList(OrderController.getOrderHistory().get(customerName));
            tableView.setItems(orders);

            double sum = orders.stream().mapToDouble(order -> order.getPrice() * order.getQuantity()).sum();
            sumAll.setText(String.format("%.2f", sum));
        } else {
            tableView.getItems().clear();
            sumAll.clear();
        }
    }

}

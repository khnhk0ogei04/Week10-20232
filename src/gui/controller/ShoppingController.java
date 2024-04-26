package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import java.util.ArrayList;

import Entity.Fruit;

public class ShoppingController {
    @FXML
    private TableView<Fruit> FruitShop;
    @FXML
    private TableColumn<Fruit, String> idColumn;
    @FXML
    private TableColumn<Fruit, String> nameColumn;
    @FXML
    private TableColumn<Fruit, Double> priceColumn;
    @FXML
    private TableColumn<Fruit, Integer> quantityColumn;
    @FXML
    private TableColumn<Fruit, String> originColumn;
    @FXML
    private Button loadShop; // Đã thay đổi từ ComboBox sang Button
    ArrayList<Fruit> fruits = CreateFruitController.getFruitData();
    @FXML
    private void loadFruitInShop(ActionEvent event) {
        viewFruitList();
    }
    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("fruitId"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("fruitName"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        originColumn.setCellValueFactory(new PropertyValueFactory<>("origin"));

    }

    private void viewFruitList() {
        ObservableList<Fruit> fruitObservableList = FXCollections.observableArrayList(fruits);
        FruitShop.setItems(fruitObservableList);
    }
}

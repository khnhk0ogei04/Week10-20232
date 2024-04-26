package gui.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Manager.Utils;
import Entity.Fruit;
import java.util.ArrayList;

public class CreateFruitController{

    @FXML
    private TextField fruitIdField;
    @FXML
    private TextField fruitNameField;
    @FXML
    private TextField priceField;
    @FXML
    private TextField quantityField;
    @FXML
    private ComboBox<String> originComboBox;

    private static ArrayList<Fruit> fruitData = new ArrayList<>();
    public static ArrayList<Fruit> getFruitData(){
    	return fruitData;
    }
    @FXML
    public void initialize() {
        ObservableList<String> originList = FXCollections.observableArrayList("China", "Vietnam", "Laos", "Japan");
        originComboBox.setItems(originList);
    }
    @FXML
    private void addNewFruit() {
        String maHoaQua = fruitIdField.getText().trim();
        String tenHoaQua = fruitNameField.getText().trim();
        String giaText = priceField.getText().trim();
        String soLuongText = quantityField.getText().trim();
        String xuatXu = originComboBox.getValue();

        if (maHoaQua.isEmpty() || tenHoaQua.isEmpty() || giaText.isEmpty() || soLuongText.isEmpty() || xuatXu == null) {
            showAlert("Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        if (!Utils.checkIdExist(fruitData, maHoaQua)) {
            showAlert("Mã hoa quả đã tồn tại. Vui lòng nhập mã khác.");
            return;
        }

        double gia;
        int soLuong;
        try {
            gia = Double.parseDouble(giaText);
            soLuong = Integer.parseInt(soLuongText);
        } catch (NumberFormatException e) {
            showAlert("Giá hoặc số lượng không hợp lệ. Vui lòng nhập số.");
            return;
        }

        Fruit hoaQua = new Fruit(maHoaQua, tenHoaQua, gia, soLuong, xuatXu);
        fruitData.add(hoaQua);
        clearForm();
        showAlert("Thêm hoa quả thành công!");
    }

    private void clearForm() {
        fruitIdField.clear();
        fruitNameField.clear();
        priceField.clear();
        quantityField.clear();
        originComboBox.getSelectionModel().clearSelection();
    }

    private void showAlert(String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

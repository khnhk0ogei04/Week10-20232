package gui.controller;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class SidebarController {
    @FXML
    void loadCreateFruitScene(ActionEvent event) {
        loadContent("createFruit.fxml",event);
    }

    @FXML
    void loadViewOrderScene(ActionEvent event) {
        loadContent("ViewOrder.fxml",event);
    }

    @FXML
    void loadShoppingScene(ActionEvent event) {
        loadContent("Shopping.fxml",event);
    }

    @FXML
    void Order(ActionEvent event) {
        loadContent("Order.fxml",event);
    }

    @FXML
    void exitApplication(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Xác nhận thoát");
        alert.setHeaderText("Bạn có chắc chắn muốn thoát không?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Platform.exit();
        }
    }


    private void loadContent(String fxmlFileName, ActionEvent event) {
        try {
            String fxmlPath = "/gui/fxml/" + fxmlFileName;
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            BorderPane content = loader.load();
            Node sourceNode = (Node) event.getSource();
            Scene scene = sourceNode.getScene();
            Parent root = scene.getRoot();
            if (root instanceof BorderPane) {
                BorderPane borderPane = (BorderPane) root;
                borderPane.setRight(null); 
                borderPane.setRight(content);
            } else {
                System.out.println("Root is not an instance of BorderPane!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}

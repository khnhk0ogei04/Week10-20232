package main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    private BorderPane root;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader mainLoader = new FXMLLoader(getClass().getResource("../gui/fxml/MainScreen.fxml"));
        root = mainLoader.load();
        
        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource("../gui/fxml/Sidebar.fxml"));
        root.setLeft(sidebarLoader.load());
 
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("APP");
        primaryStage.setMinWidth(780);
        primaryStage.setMaxWidth(780);

        
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

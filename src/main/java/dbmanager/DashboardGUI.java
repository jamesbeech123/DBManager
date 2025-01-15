package dbmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class DashboardGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        primaryStage.setScene(new Scene(loader.load(), 800, 600));
        primaryStage.setTitle("JavaFX Application");
        primaryStage.show();
    }

    public static void main() {
        launch();
    }
}
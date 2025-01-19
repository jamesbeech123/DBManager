package dbmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class DashboardGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load main.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dbmanager/main.fxml"));
        BorderPane root = loader.load();

        // Add a TabPane for navigation
        TabPane tabPane = new TabPane();

        // Dashboard Tab
        Tab dashboardTab = new Tab("Dashboard");
        dashboardTab.setClosable(false);
        dashboardTab.setContent(new Label("Dashboard Content")); // Replace with actual dashboard content

        // Database Visualizer Tab
        Tab visualizerTab = new Tab("Database Visualizer");
        visualizerTab.setClosable(false);
        DatabaseVisualizer visualizer = new DatabaseVisualizer();
        visualizerTab.setContent(visualizer.createVisualizer());

        // Add tabs to TabPane
        tabPane.getTabs().addAll(dashboardTab, visualizerTab);

        // Set TabPane as the center of the root layout
        root.setCenter(tabPane);

        // Set the scene and stage
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setTitle("JavaFX Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

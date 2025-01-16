package dbmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.bson.Document;

public class GUIController {

    private final DBHandler dbHandler;

    public GUIController() {
        dbHandler = new DBHandler();
    }



    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

package dbmanager;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.bson.Document;

public class GUIController {

    private final DBHandler dbHandler;

    public GUIController() {
        // Initialize DBHandler with a collection (replace with your actual MongoDB setup)
        dbHandler = new DBHandler(MongoDBUtils.getCollection("testdb", "testcollection"));
    }

    @FXML
    public void onCreateDocument() {
        Document doc = new Document("name", "New User").append("age", 30);
        dbHandler.create(doc);
        showAlert("Success", "Document created successfully!");
    }

    @FXML
    public void onReadDocuments() {
        dbHandler.readAll(); // Consider displaying results in a GUI table
        showAlert("Info", "Check the console for retrieved documents.");
    }

    @FXML
    public void onUpdateDocument() {
        Document filter = new Document("name", "New User");
        Document update = new Document("age", 31);
        dbHandler.updateOne(filter, update);
        showAlert("Success", "Document updated successfully!");
    }

    @FXML
    public void onDeleteDocument() {
        Document filter = new Document("name", "New User");
        dbHandler.deleteOne(filter);
        showAlert("Success", "Document deleted successfully!");
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

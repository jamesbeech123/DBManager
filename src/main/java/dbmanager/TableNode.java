package dbmanager;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.util.List;

public class TableNode extends VBox {
    private double mouseX;
    private double mouseY;

    // Constructor now accepts a list of column names
    protected TableNode(String tableName, List<String> columnNames) {
        setPadding(new javafx.geometry.Insets(10));
        setSpacing(5);

        Label titleLabel = new Label(tableName);
        getChildren().add(titleLabel); // Add table name as the header

        // Dynamically add column labels
        for (String columnName : columnNames) {
            Label columnLabel = new Label(columnName); // Create a label for each column
            getChildren().add(columnLabel); // Add the label to the VBox
        }

        setOnMousePressed(this::onMousePressed);
        setOnMouseDragged(this::onMouseDragged);
    }

    private void onMouseDragged(MouseEvent mouseEvent) {
        setLayoutX(mouseEvent.getSceneX() - getLayoutX());
        setLayoutY(mouseEvent.getSceneY() - getLayoutY());
    }

    private void onMousePressed(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getSceneX() - getLayoutX();
        mouseY = mouseEvent.getSceneY() - getLayoutY();
    }
}

package dbmanager;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;



public class TableNode extends VBox {
    private double mouseX;
    private double mouseY;

    protected TableNode(String tableName){
        setPadding(new javafx.geometry.Insets(10));
        setSpacing(5);

        Label titleLabel = new Label(tableName);
        getChildren().add(titleLabel);

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

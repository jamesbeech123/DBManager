package dbmanager;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

public class TableNode extends StackPane {
    private double mouseX;
    private double mouseY;

    // Constructor now accepts a list of column names and table data
    protected TableNode(String tableName, List<String> columnNames, List<List<String>> tableData) {
        // Rounded rectangle background
        setBackground(new Background(new BackgroundFill(
                Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY
        )));
        setPadding(new Insets(10));

        // VBox container for table content
        VBox contentBox = new VBox();
        contentBox.setSpacing(10);
        contentBox.setPadding(new Insets(10));

        // Header label for table name
        Label titleLabel = new Label(tableName);
        titleLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");
        contentBox.getChildren().add(titleLabel);

        // TableView to display data
        TableView<List<String>> tableView = createTableView(columnNames, tableData);
        contentBox.getChildren().add(tableView);

        // Add VBox to the StackPane
        getChildren().add(contentBox);

        // Enable dragging
        setOnMousePressed(this::onMousePressed);
        setOnMouseDragged(this::onMouseDragged);
    }

    private TableView<List<String>> createTableView(List<String> columnNames, List<List<String>> tableData) {
        TableView<List<String>> tableView = new TableView<>();

        // Dynamically create table columns
        for (int i = 0; i < columnNames.size(); i++) {
            final int colIndex = i;
            TableColumn<List<String>, String> column = new TableColumn<>(columnNames.get(i));
            column.setCellValueFactory(cellData ->
                    new javafx.beans.property.SimpleStringProperty(cellData.getValue().get(colIndex))
            );
            tableView.getColumns().add(column);
        }

        // Add data to the table
        tableView.getItems().addAll(tableData);

        // Make table dynamically resize
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return tableView;
    }

    private void onMouseDragged(MouseEvent mouseEvent) {
        setLayoutX(mouseEvent.getSceneX() - mouseX);
        setLayoutY(mouseEvent.getSceneY() - mouseY);
    }

    private void onMousePressed(MouseEvent mouseEvent) {
        mouseX = mouseEvent.getSceneX() - getLayoutX();
        mouseY = mouseEvent.getSceneY() - getLayoutY();
    }
}

package dbmanager;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseVisualizer  {
    public Parent createVisualizer() {
        WhiteboardComponent whiteboard = new WhiteboardComponent();
        DBConnection db = new DBConnection();

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "SELECT table_name FROM information_schema.tables WHERE table_schema = 'people';")) {

            double x = 50;
            double y = 50;
            while (rs.next()) {
                String tableName = rs.getString("table_name");
                TableNode tableNode = new TableNode(tableName, db.getColumnNames(tableName));

                // Position nodes
                tableNode.setLayoutX(x);
                tableNode.setLayoutY(y);
                whiteboard.getChildren().add(tableNode);

                y += 100; // Adjust spacing between tables
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return whiteboard;
    }
}
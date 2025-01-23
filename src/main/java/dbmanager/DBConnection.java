package dbmanager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/testDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "databasepassword";

    // Connect to the database
    public static Connection connect() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Get column names of a table
    public static List<String> getColumnNames(String tableName) throws SQLException {
        List<String> columnNames = new ArrayList<>();

        String query = "SELECT column_name FROM information_schema.columns WHERE table_schema = 'people' AND table_name = ?";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, tableName);  // Set table name dynamically
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                columnNames.add(rs.getString("column_name"));
            }
        }

        return columnNames;
    }

    // Get data from the table
    public static List<List<String>> getTableData(String tableName) throws SQLException {
        List<List<String>> tableData = new ArrayList<>();

        String query = "SELECT * FROM people." + tableName;

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (rs.next()) {
                List<String> row = new ArrayList<>();

                // Iterate through each column in the row
                for (int i = 1; i <= columnCount; i++) {
                    row.add(rs.getString(i));
                }

                tableData.add(row); // Add the row to the data list
            }
        }

        return tableData;
    }
}

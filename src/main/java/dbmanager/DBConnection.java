package dbmanager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/testDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "databasepassword";

    public static Connection connect() throws Exception {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

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
}

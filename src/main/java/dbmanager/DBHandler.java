package dbmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBHandler {

    public void create(String name, int age) {
        String query = "INSERT INTO users (name, age) VALUES (?, ?)";
        try (Connection connection = DBConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, age);
            stmt.executeUpdate();
            System.out.println("User added successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readAll() {
        String query = "SELECT * FROM users";
        try (Connection connection = DBConnection.connect();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Name: " + rs.getString("name") +
                        ", Age: " + rs.getInt("age"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(int id, String newName, int newAge) {
        String query = "UPDATE users SET name = ?, age = ? WHERE id = ?";
        try (Connection connection = DBConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newName);
            stmt.setInt(2, newAge);
            stmt.setInt(3, id);
            stmt.executeUpdate();
            System.out.println("User updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String query = "DELETE FROM users WHERE id = ?";
        try (Connection connection = DBConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("User deleted successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package gateway.database;

import java.sql.*;
import java.util.*;

public class Database {
    private final Connection connection;

    public Database(Connection connection) {
        this.connection = connection;
    }

    public List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT id, phone, content FROM messages ORDER BY id DESC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                messages.add(new Message(
                        rs.getInt("id"),
                        rs.getString("phone"),
                        rs.getString("content")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }
}

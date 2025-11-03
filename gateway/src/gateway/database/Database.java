package gateway.database;

import java.sql.*;
import java.util.*;

public class Database
{
    public static List<Message> getMessages()
    {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT id, phone, content FROM messages ORDER BY id DESC";

        try (
                Connection connection = DriverManager.getConnection(
                        DatabaseConfig.URL,
                        DatabaseConfig.USER,
                        DatabaseConfig.PASSWORD
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        )
        {
            while (resultSet.next())
            {
                messages.add(new Message(
                        resultSet.getInt("id"),
                        resultSet.getString("phone"),
                        resultSet.getString("content")
                ));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return messages;
    }
}

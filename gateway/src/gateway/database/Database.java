package gateway.database;


import java.sql.*;
import java.sql.SQLException;
import java.util.*;

public class Database
{
    public static List<Message> getMessages()
    {
        List<Message> messages = new ArrayList<>();
        String sql ="SELECT id, phone, content FROM messages ORDER BY DESC";
        try (Connection connection = DriverManager.getConnection(DatebaseConfig.URL,DatebaseConfig.USER,DatebaseConfig.PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql))
        {
            while (resultSet.next())
            {
                messages.add(new Message(
                        resultSet.getInt("id"),
                        resultSet.getString("phone"),
                        resultSet.getString("content")));

            }
            catch (SQLException e)
            {
                e.printStacktrace();
            }

            return messages;

        }

    }

}

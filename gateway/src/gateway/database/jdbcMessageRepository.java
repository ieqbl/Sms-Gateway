package gateway.database;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

public class jdbcMessageRepository implements MessageRepository
{
    private final String url = System.getenv("DB_URL");
    private final String user = System.getenv("DB_USER");
    private final String pass = System.getenv("DB_PASS");

    public jdbcMessageRepository()
    {
        if(url == null ||user == null || pass == null)
        {
            throw new RuntimeException("Database environment variables missing");
        }

    }

    @Override
    public void save(Message message)
    {
        String sql = """
            INSERT INTO messages (receiver, content, status, driver, created_at)
            VALUES (?, ?, ?, ?, ?)
        """;

        try(Connection connection = DriverManager.getConnection(url,user,pass);
        PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, message.getReceiver());
            statement.setString(2,message.getContent());
            statement.setString(3, message.getStatus());
            statement.setString(4, message.getDriver());


        }
        catch (SQLException e)
        {
            throw new RuntimeException("Failed to save message", e);
        }
    }
    @Override
    public List<Message> findAll(int page, int size)
    {
        String sql = """
            SELECT * FROM messages
            ORDER BY created_at DESC
            LIMIT ? OFFSET ?
        """;

        List<Message> messages = new ArrayList<>();

        int offset = (page - 1) * size;

        try(Connection connection = DriverManager.getConnection(url,user,pass);
        PreparedStatement statement = connection.prepareStatement(sql))
        {

            statement.setInt(1,size);
            statement.setInt(2, offset);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next())
            {
                Message m = new Message(

                        resultSet.getString("receiver"),
                        resultSet.getString("content"),
                        resultSet.getString("status"),
                        resultSet.getString("driver"),
                        resultSet.getTimestamp("createdAt").toLocalDateTime()
                );


                messages.add(m);
            }

        }
        catch (SQLException e)
        {
            throw new RuntimeException("Failed to load messages", e);
        }
        return messages;
    }
}


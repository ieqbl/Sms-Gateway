package gateway.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SmsLogRepositoryImpl implements SmsLogRepository
{
    private final Connection connection;

    public SmsLogRepositoryImpl (Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void InsertSmsLogs(String from, String to, String driver, String response) throws SQLException
    {
        String sql = "INSERT INTO sms_logs (from_address, to_address, driver, driver_response) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, from);
            statement.setString(2, to);
            statement.setString(3, driver);
            statement.setString(4, response);
            statement.executeUpdate();
        }
    }

    @Override
    public void GetSmsLogs() throws SQLException
    {
        String sql = "SELECT id, from_address, to_address, driver, created_at FROM sms_logs ORDER BY created_at DESC";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                System.out.println(
                        resultSet.getInt("ID")+ " | " +
                        resultSet.getString("from_address") + " -> " +
                        resultSet.getString("to_address") + " | " +
                        resultSet.getString("driver") + " | " +
                        resultSet.getTimestamp("created_at")
                );

            }
        }
    }
}

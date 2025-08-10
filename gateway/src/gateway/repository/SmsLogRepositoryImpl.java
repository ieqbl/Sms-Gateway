package gateway.repository;

import gateway.model.SmsLog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SmsLogRepositoryImpl implements SmsLogRepository
{
    private final Connection connection;

    public SmsLogRepositoryImpl(Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void save(SmsLog smsLog) throws SQLException
    {
        String sql = "INSERT INTO sms_logs (from_address, to_address, driver, driver_response) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, smsLog.getFromAddress());
            statement.setString(2, smsLog.getToAddress());
            statement.setString(3, smsLog.getDriver());
            statement.setString(4, smsLog.getDriverResponse());
            statement.executeUpdate();
        }
    }

    @Override
    public List<SmsLog> findAll() throws SQLException
    {
        String sql = "SELECT * FROM sms_logs ORDER BY created_at DESC";
        List<SmsLog> logs = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery())
        {
            while (resultSet.next())
            {
                SmsLog log = new SmsLog(
                        resultSet.getInt("id"),
                        resultSet.getString("from_address"),
                        resultSet.getString("to_address"),
                        resultSet.getString("driver"),
                        resultSet.getTimestamp("updated_at"),
                        resultSet.getTimestamp("created_at"),
                        resultSet.getInt("retry"),
                        resultSet.getString("driver_response")
                );
                logs.add(log);
            }
        }

        return logs;
    }

    @Override
    public SmsLog findByID(int id) throws SQLException
    {
        String sql = "SELECT * FROM sms_logs WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery())
            {
                if (resultSet.next())
                {
                    return new SmsLog(
                            resultSet.getInt("id"),
                            resultSet.getString("from_address"),
                            resultSet.getString("to_address"),
                            resultSet.getString("driver"),
                            resultSet.getTimestamp("updated_at"),
                            resultSet.getTimestamp("created_at"),
                            resultSet.getInt("retry"),
                            resultSet.getString("driver_response")
                    );
                }
            }
        }

        return null;
    }

    @Override
    public void delete(int id) throws SQLException
    {
        String sql = "DELETE FROM sms_logs WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setInt(1, id);
            statement.executeUpdate();
        }
    }

    @Override
    public void update(SmsLog smsLog) throws SQLException
    {
        String sql = "UPDATE sms_logs SET from_address=?, to_address=?, driver=?, driver_response=?, retry=?, updated_at=CURRENT_TIMESTAMP WHERE id=?";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, smsLog.getFromAddress());
            statement.setString(2, smsLog.getToAddress());
            statement.setString(3, smsLog.getDriver());
            statement.setString(4, smsLog.getDriverResponse());
            statement.setInt(5, smsLog.getRetry());
            statement.setInt(6, smsLog.getId());
            statement.executeUpdate();
        }
    }
}

package gateway;

import gateway.repository.ConfigRepository;
import gateway.repository.ConfigRepositoryImpl;
import gateway.repository.SmsLogRepository;
import gateway.repository.SmsLogRepositoryImpl;

import java.sql.*;

public class DbServices
{
    private Connection connection;

    private ConfigRepository configRepository;
    private SmsLogRepository smsLogRepository;

    public void Connect() throws SQLException
    {
        String url = "jdbc:postgresql://127.0.0.1:5432/postgres";
        connection = DriverManager.getConnection(url, "postgres", "mysecretpassword");

        configRepository = new ConfigRepositoryImpl(connection);
        smsLogRepository = new SmsLogRepositoryImpl(connection);
    }
    public void saveMessage(String number,String message) throws SQLException
    {
        PreparedStatement statement = connection.prepareStatement("INSERT INTO Messages (phone,content) VALUES (?, ?)");
        statement.setString(1, number);
        statement.setString(2, message);
        statement.executeUpdate();
        statement.close();
    }
    public void close() throws SQLException
    {
        if (connection != null) connection.close();
    }

    public Connection getConnection()
    {
        return connection;
    }
    public ConfigRepository getConfigRepository()
    {
        return configRepository;
    }
    public SmsLogRepository getSmsLogRepository()
    {
        return smsLogRepository;
    }

}

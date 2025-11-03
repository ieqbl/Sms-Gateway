package gateway;

import gateway.repository.ConfigRepository;
import gateway.repository.ConfigRepositoryImpl;
import gateway.repository.SmsLogRepository;
import gateway.repository.SmsLogRepositoryImpl;
import java.sql.*;

public class DbServices {
    private Connection connection;
    private ConfigRepository configRepository;
    private SmsLogRepository smsLogRepository;

    public void connect() throws SQLException {
        String url = "jdbc:postgresql://127.0.0.1:5432/mydb";
        connection = DriverManager.getConnection(url, "Ieqbli", "Mamali@2005");

        configRepository = new ConfigRepositoryImpl(connection);
        smsLogRepository = new SmsLogRepositoryImpl(connection);
        System.out.println("✅ Connected to database successfully.");
    }

    public Connection getConnection() {
        return connection;
    }

    public ConfigRepository getConfigRepository() {
        return configRepository;
    }

    public SmsLogRepository getSmsLogRepository() {
        return smsLogRepository;
    }

    public void saveMessage(String number, String message) throws SQLException {
        String sql = "INSERT INTO messages (phone, content) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, number);
            stmt.setString(2, message);
            stmt.executeUpdate();
        }
    }

    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("🧹 Database connection closed.");
        }
    }
}

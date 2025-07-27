package gateway.repository;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConfigRepositoryImpl implements ConfigRepository
{
    private final Connection connection;

    public ConfigRepositoryImpl (Connection connection)
    {
        this.connection = connection;
    }

    @Override
    public void InsertConfig(String driver) throws SQLException
    {
        String sql = "INSERT INTO config (config_driver) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql))
        {
            statement.setString(1, driver);
            statement.executeUpdate();
        }
    }

    @Override
    public String GetConfig() throws SQLException
    {
        String sql = "SELECT config_driver FROM config LIMIT 1";
        try (PreparedStatement statement1 = connection.prepareStatement(sql))
        {
            ResultSet resultSet = statement1.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getString("config_driver");
            }
        }
        return null;
    }

}

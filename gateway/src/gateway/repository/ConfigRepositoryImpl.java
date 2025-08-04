package gateway.repository;

import javax.xml.transform.Result;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import gateway.model.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

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

    //Config

    @Override
    public void save(Config config) throws SQLException
    {
        String sql;

//        if (config.GetID() > 0) {
//            sql = "INSERT INTO config (id, config_driver) VALUES (?, ?)";
//            try (PreparedStatement ps = connection.prepareStatement(sql))
//            {
//                ps.setInt(1, config.GetID());
//                ps.setString(2, config.GetConfigDriver());
//                ps.executeUpdate();
//            }
//        }
//        else
//        {
            sql = "INSERT INTO config (config_driver) VALUES (?)";
            try (PreparedStatement ps = connection.prepareStatement(sql))
            {
                ps.setString(1, config.GetConfigDriver());
                ps.executeUpdate();
            }
 //       }
    }


    @Override
    public List<Config> findAll() throws SQLException
    {
        List<Config> list = new ArrayList<>();
        String sql = "Select id, config_driver FROM config";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            {
                while (resultSet.next())
                {
                    list.add(new Config(resultSet.getInt("id"), resultSet.getString("config_driver")));
                }
            }
        }

        return list;
    }

    @Override
    public Config findByID(int ID) throws SQLException
    {
        String sql ="SELECT id, config_driver FROM config WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1, ID);
            try (ResultSet resultSet = preparedStatement.executeQuery())
            {
                if (resultSet.next())
                    return new Config(resultSet.getInt("id"), resultSet.getString("config_driver"));
            }
        }
        return null;
    }

    @Override
    public void update(Config config) throws SQLException
    {
        String sql ="UPDATE config SET config_driver=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setString(1,config.GetConfigDriver());
            preparedStatement.setInt(2,config.GetID());
            preparedStatement.executeUpdate();
        }

    }

    @Override
    public void delete(int ID) throws SQLException
    {
        String sql = "DELETE FROM config WHERE id=?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql))
        {
            preparedStatement.setInt(1,ID);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            System.out.println("did not work");
        }
    }

    @Override
    public void deleteAll() throws SQLException {
        String sql = "DELETE FROM config";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }
    }


}

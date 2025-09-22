package gateway.repository;

import java.sql.SQLException;
public interface ConfigRepository
{
    void InsertConfig(String driver) throws SQLException;
    String GetConfig() throws SQLException;
}

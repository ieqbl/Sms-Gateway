package gateway.repository;
import gateway.model.Config;
import java.sql.SQLException;
import java.util.List;

public interface ConfigRepository
{
    void InsertConfig(String driver) throws SQLException;
    String GetConfig() throws SQLException;


    //Config
    void save(Config config) throws SQLException;
    List<Config> findAll() throws SQLException;
    Config findByID (int ID) throws SQLException;
    void update(Config config) throws SQLException;
    void delete(int ID) throws SQLException;
    void deleteAll() throws SQLException;
}

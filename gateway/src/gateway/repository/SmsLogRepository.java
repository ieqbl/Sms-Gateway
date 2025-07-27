package gateway.repository;

import java.sql.SQLException;
public interface SmsLogRepository
{
    void InsertSmsLogs(String from, String to, String driver, String response) throws SQLException;
    void GetSmsLogs() throws SQLException;
}

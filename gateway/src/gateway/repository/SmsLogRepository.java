package gateway.repository;

import gateway.model.SmsLog;
import java.sql.SQLException;
import java.util.List;

public interface SmsLogRepository
{
    void save(SmsLog smsLog) throws SQLException;
    List<SmsLog> findAll() throws SQLException;
    SmsLog findByID(int id) throws SQLException;
    void delete(int id) throws SQLException;
    void update(SmsLog smsLog) throws SQLException;
}

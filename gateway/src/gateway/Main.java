package gateway;

import api.SmsDriver;
import gateway.migration.MigrationRunner;
import gateway.repository.ConfigRepository;
import gateway.repository.SmsLogRepository;


public class Main
{
    public static void main(String[] args)
    {
        try
        {
            SmsDriver driver = DriverFactory.createDriver();
            SmsGateway gateway = new SmsGateway(driver);

            String number = "09120478162";
            String message = "Test with Module";

            gateway.send(number, message);

            DbServices db = new DbServices();
            db.Connect();
            ConfigRepository Cr = db.getConfigRepository();
            SmsLogRepository Sr = db.getSmsLogRepository();

            MigrationRunner migrator = new MigrationRunner(db.getConnection());
            migrator.run();
            db.saveMessage(number, message);
            db.close();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

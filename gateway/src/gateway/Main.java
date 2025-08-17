package gateway;

import api.SmsDriver;
import gateway.model.Config;
import gateway.model.SmsLog;
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

            String number = "09120000000";
            String message = "Test with Module";

            gateway.send(number, message);

            DbServices db = new DbServices();
            db.Connect();

            ConfigRepository Cr = db.getConfigRepository();
            SmsLogRepository Sr = db.getSmsLogRepository();

            Cr.save(new Config(1, "Twilio"));
            Cr.save(new Config(2, "Nexmo"));

            System.out.println("Config List");
            Cr.findAll().forEach(c ->
                    System.out.println(c.GetID() + ": " + c.GetConfigDriver())
            );

            Config config = Cr.findByID(1);
            if (config != null)
            {
                System.out.println("findByID(1): " + config.GetConfigDriver());

                config.SetConfigDriver("Updated Driver");
                Cr.update(config);
            }

            Cr.delete(2);

            Cr.deleteAll();

            MigrationRunner migrator = new MigrationRunner(db.getConnection());
            migrator.run();

            SmsLog log = new SmsLog("System", number, driver.getClass().getSimpleName(), "Sent successfully");
            Sr.save(log);

            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

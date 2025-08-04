package gateway;

import api.SmsDriver;
import gateway.model.Config;
import gateway.migration.MigrationRunner;
import gateway.repository.ConfigRepository;
import gateway.repository.SmsLogRepository;
import gateway.repository.ConfigRepository;



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

            //create
            Cr.save(new Config(23,"Twilio"));
            Cr.save(new Config(313,"Nexmo"));

            //read
            System.out.println("Config List");
            Cr.findAll().forEach(c ->
                    System.out.println(c.GetID() +": "+ c.GetConfigDriver())
            );

            //read one
            Config config = Cr.findByID(1);
            if (config!=null)
            {
                System.out.println("findByID(1), " + config.GetConfigDriver());
            }

            //update
            if (config!=null)
            {
                config.SetConfigDriver("Updated Driver");
                Cr.update(config);
            }

            //delete
            Cr.delete(2);

            //deleteAll
            Cr.deleteAll();


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

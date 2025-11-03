package gateway;

import api.SmsDriver;
import gateway.database.Database;
import gateway.database.Message;
import gateway.migration.MigrationRunner;
import gateway.repository.ConfigRepository;
import gateway.repository.SmsLogRepository;
import gg.jte.output.StringOutput;
import gateway.template.*;
import gateway.template.TemplateEngineProvider;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class Main
{
    public static void main(String[] args)
    {
        List<Message> messages = Database.getMessages();


        Map<String, Object> model = new HashMap<>();
        model.put("message",messages);

        var output = new StringOutput();
        var engine = TemplateEngineProvider.getEngine();
        engine.render("index.jte",model , output);

        System.out.println(output);

//        try
//        {
//            SmsDriver driver = DriverFactory.createDriver();
//            SmsGateway gateway = new SmsGateway(driver);
//
//            String number = "09120478162";
//            String message = "Test with Module";
//
//            gateway.send(number, message);
//
//            DbServices db = new DbServices();
//            db.Connect();
//            ConfigRepository Cr = db.getConfigRepository();
//            SmsLogRepository Sr = db.getSmsLogRepository();
//
//            MigrationRunner migrator = new MigrationRunner(db.getConnection());
//            migrator.run();
//            db.saveMessage(number, message);
//            db.close();
//
//        }
//        catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }
}

package gateway;

import api.SmsDriver;
import gateway.database.Database;
import gateway.database.Message;
import gateway.migration.MigrationRunner;
import gateway.repository.ConfigRepository;
import gateway.repository.SmsLogRepository;
import gg.jte.output.StringOutput;
import gateway.template.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            // 1️⃣ اتصال به دیتابیس
            DbServices db = new DbServices();
            db.connect();

            // 2️⃣ اجرای مایگریشن‌ها
            MigrationRunner migrator = new MigrationRunner(db.getConnection());
            migrator.run();

            // 3️⃣ گرفتن دیتا برای JTE
            Database database = new Database(db.getConnection());
            List<Message> messages = database.getMessages();

            // 4️⃣ رندر HTML
            Map<String, Object> model = new HashMap<>();
            model.put("messages", messages);
            var output = new StringOutput();
            var engine = TemplateEngineProvider.getEngine();
            engine.render("index.jte", model, output);
            System.out.println(output);

            // 5️⃣ ارسال پیام نمونه
            SmsDriver driver = DriverFactory.createDriver();
            SmsGateway gateway = new SmsGateway(driver);
            String number = "09120478162";
            String message = "Test with Module";
            gateway.send(number, message);

            // 6️⃣ ذخیره در دیتابیس
            db.saveMessage(number, message);

            // 7️⃣ بستن ارتباط
            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

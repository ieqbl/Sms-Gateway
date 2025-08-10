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
            // ساخت درایور و گیت‌وی پیامک
            SmsDriver driver = DriverFactory.createDriver();
            SmsGateway gateway = new SmsGateway(driver);

            String number = "09120000000";
            String message = "Test with Module";

            // ارسال پیام
            gateway.send(number, message);

            // اتصال به پایگاه داده
            DbServices db = new DbServices();
            db.Connect();

            // گرفتن ریپازیتوری‌ها
            ConfigRepository Cr = db.getConfigRepository();
            SmsLogRepository Sr = db.getSmsLogRepository();

            // ایجاد کانفیگ‌ها
            Cr.save(new Config(23, "Twilio"));
            Cr.save(new Config(313, "Nexmo"));

            // خواندن همه کانفیگ‌ها
            System.out.println("Config List");
            Cr.findAll().forEach(c ->
                    System.out.println(c.GetID() + ": " + c.GetConfigDriver())
            );

            // خواندن یک کانفیگ خاص
            Config config = Cr.findByID(1);
            if (config != null)
            {
                System.out.println("findByID(1): " + config.GetConfigDriver());

                // بروزرسانی
                config.SetConfigDriver("Updated Driver");
                Cr.update(config);
            }

            // حذف یک کانفیگ خاص
            Cr.delete(2);

            // حذف همه کانفیگ‌ها
            Cr.deleteAll();

            // اجرای مایگریشن (ساخت جدول‌ها)
            MigrationRunner migrator = new MigrationRunner(db.getConnection());
            migrator.run();

            // ذخیره لاگ پیامک
            SmsLog log = new SmsLog("System", number, driver.getClass().getSimpleName(), "Sent successfully");
            Sr.save(log);

            // بستن ارتباط با پایگاه‌داده
            db.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}

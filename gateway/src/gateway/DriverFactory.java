package gateway;

import api.SmsDriver;

import java.util.ServiceLoader;
import java.util.Properties;
import java.io.InputStream;

public class DriverFactory
{
    public static SmsDriver createDriver()
    {
        String driverName;
        try (InputStream inputStream = DriverFactory.class.getResourceAsStream("/config.properties"))
        {
            Properties properties = new Properties();
            properties.load(inputStream);
            driverName = properties.getProperty("driver").toLowerCase();
        }
        catch (Exception e)
        {
            throw new RuntimeException("Error loading config.properties", e);
        }

        ServiceLoader<SmsDriver> loader = ServiceLoader.load(SmsDriver.class);
        for (SmsDriver driver : loader)
        {
            System.out.println(driver.getName());

            if (driver.getName().equalsIgnoreCase(driverName))
            {
                return driver;
            }
        }

        throw new RuntimeException("No matching driver found: " + driverName);
    }
}

package gateway;

import api.SmsDriver;

public class SmsGateway
{
    private final SmsDriver driver;

    public SmsGateway (SmsDriver driver)
    {
        this.driver = driver;
    }
    public void send(String phoneNumber, String message)
    {
        driver.send(phoneNumber, message);
    }
}

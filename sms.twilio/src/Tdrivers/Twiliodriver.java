package Tdrivers;

import api.SmsDriver;

public class Twiliodriver implements SmsDriver
{
    @Override
    public void send(String phoneNumber, String message)
    {
        System.out.println("Twilio "+ phoneNumber + " -> "+ message);
    }
    @Override
    public String getName()
    {
        return "Twilio";
    }

}

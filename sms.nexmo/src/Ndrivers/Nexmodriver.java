package Ndrivers;

import api.SmsDriver;

public class Nexmodriver implements SmsDriver
{
    @Override
    public void send(String phoneNumber, String message)
    {
        System.out.println("Nexmo "+phoneNumber+"  -> "+message);
    }
    @Override
    public String getName()
    {
        return "Nexmo";
    }
}

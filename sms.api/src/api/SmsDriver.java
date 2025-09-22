package api;

public interface SmsDriver
{
    void send(String phoneNumber, String message);
    String getName();
}

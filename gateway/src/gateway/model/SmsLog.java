package gateway.model;

import java.sql.Timestamp;

public class SmsLog
{
    private int id;
    private String fromAddress;
    private String toAddress;
    private String driver;
    private Timestamp updatedAt;
    private Timestamp createdAt;
    private int retry;
    private String driverResponse;

    public SmsLog(int id, String fromAddress, String toAddress, String driver, Timestamp updatedAt, Timestamp createdAt, int retry, String driverResponse)
    {
        this.id = id;
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.driver = driver;
        this.updatedAt = updatedAt;
        this.createdAt = createdAt;
        this.retry = retry;
        this.driverResponse = driverResponse;
    }

    public SmsLog(String fromAddress, String toAddress, String driver, String driverResponse)
    {
        this.fromAddress = fromAddress;
        this.toAddress = toAddress;
        this.driver = driver;
        this.driverResponse = driverResponse;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getFromAddress() { return fromAddress; }
    public void setFromAddress(String fromAddress) { this.fromAddress = fromAddress; }

    public String getToAddress() { return toAddress; }
    public void setToAddress(String toAddress) { this.toAddress = toAddress; }

    public String getDriver() { return driver; }
    public void setDriver(String driver) { this.driver = driver; }

    public Timestamp getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public int getRetry() { return retry; }
    public void setRetry(int retry) { this.retry = retry; }

    public String getDriverResponse() { return driverResponse; }
    public void setDriverResponse(String driverResponse) { this.driverResponse = driverResponse; }
}

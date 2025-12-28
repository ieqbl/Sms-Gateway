package gateway.database;

import java.time.LocalDateTime;
public class Message
{

    private long id;
    private String receiver;
    private String content;
    private String status;   // SENT | FAILED
    private String driver;
    private LocalDateTime createdAt;

    public Message(String receiver, String content, String status, String driver, LocalDateTime createdAt)
    {
        this.receiver = receiver;
        this.content = content;
        this.status = status;
        this.driver = driver;
        this.createdAt = createdAt;
    }

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getReceiver() { return receiver; }
    public String getContent() { return content; }
    public String getStatus() { return status; }
    public String getDriver() { return driver; }
    public LocalDateTime getCreatedAt() { return createdAt; }


}

package gateway.database;

public class Message
{
    public int id;
    public String phone;
    public String content;

    public Message(int id,String phone, String content)
    {
        this.id = id;
        this.phone = phone;
        this.content = content;
    }

    public int getId()
    {
        return id;
    }

    public String getPhone()
    {
        return phone;
    }
    public String getContent()
    {
        return content;
    }

    @Override
    public String toString()
    {
        return "Message:{id= " +id+", phone= "+phone+", content= "+content+"}";
    }
}

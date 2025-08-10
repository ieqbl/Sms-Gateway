package gateway.model;

public class Config
{
    private int ID;
    private String ConfigDriver;

    public Config(int ID, String ConfigDriver)
    {
        this.ID = ID;
        this.ConfigDriver = ConfigDriver;
    }
    public int GetID()
    {
        return ID;
    }
    public void SetID(int ID)
    {
        this.ID = ID;
    }
    public String GetConfigDriver()
    {
        return ConfigDriver;
    }
    public void SetConfigDriver(String ConfigDriver)
    {
        this.ConfigDriver = ConfigDriver;
    }
}

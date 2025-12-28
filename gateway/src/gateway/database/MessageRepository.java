package gateway.database;

import java.util.List;

public interface MessageRepository
{
    void save(Message message);

    List<Message> findAll(int page, int size);

}

package gateway.service;

import api.SmsDriver;
import gateway.database.Message;
import gateway.database.MessageRepository;
import gateway.dto.SendMessageRequest;
import java.time.LocalDateTime;


import java.util.List;

public class MessageService {

    private final MessageRepository messageRepository;
    private final SmsDriver driver;

    public MessageService(MessageRepository messageRepository, SmsDriver driver) {
        this.messageRepository = messageRepository;
        this.driver = driver;
    }

    public List<Message> listMessages(int page, int size) {
        return messageRepository.findAll(page, size);
    }

    public Message sendMessage(SendMessageRequest request) {

        boolean sentSuccessfully = false;

        try {
            driver.send(request.getReceiver(), request.getMessage());
            sentSuccessfully = true;
        } catch (Exception e) {
            // failure is recorded, not ignored
        }

        Message message = new Message(
                request.getReceiver(),
                request.getMessage(),
                sentSuccessfully ? "SENT" : "FAILED",
                driver.getName(),
                LocalDateTime.now()
        );


        messageRepository.save(message);
        return message;
    }
}

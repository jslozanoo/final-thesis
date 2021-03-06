package jslozano.thesis.service;

import jslozano.thesis.command.MessageCommand;
import jslozano.thesis.model.Message;
import jslozano.thesis.model.Type;

import java.util.Set;

public interface MessageService {
    Set<Message> getMessages(Long userId);
    Set<Message> getInboxMessages(Long userId, Type type);
    Message findMessageById(Long userId, Long userMessage);
    void deleteById(Long userId, Long messageId);
    Message saveMessage(Long userId, Message message);
    MessageCommand saveMessageCommand(Long userId, MessageCommand messageCommand);
}

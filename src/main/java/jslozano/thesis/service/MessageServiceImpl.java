package jslozano.thesis.service;

import jslozano.thesis.model.Message;
import jslozano.thesis.model.Type;
import jslozano.thesis.model.User;
import jslozano.thesis.repositories.MessageRepository;
import jslozano.thesis.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class MessageServiceImpl implements MessageService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    public MessageServiceImpl(UserRepository userRepository, MessageRepository messageRepository)  {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    @Override
    public Set<Message> getMessages(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User does not exist");
        }
        User user = userOptional.get();
        Set<Message> messages = new HashSet<>();
        user.getMessages().iterator().forEachRemaining(messages::add);

        return messages;
    }
    @Override
    public Set<Message> getInboxMessages(Long userId, Type type) {

        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User does not exist");
        }
        User user = userOptional.get();
        Set<Message> messages = new HashSet<>();
        user.getMessages().iterator().forEachRemaining(message -> {
            if(message.getType() == type){
                messages.add(message);
            }
        });
        return messages;
    }

    @Override
    public Message findMessageById(Long userId, Long userMessage) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not Found");
        }
        User user = userOptional.get();

        return user.getMessages().stream().filter(message -> Objects.
                        equals(message.getId(), userMessage)).
                findFirst().get();
    }

    @Override
    public void deleteById(Long userId, Long messageId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if(userOptional.isEmpty()){
            throw new RuntimeException("User not Found");
        }
        User user = userOptional.get();
        user.getMessages().remove(findMessageById(userId, messageId));


    }
}

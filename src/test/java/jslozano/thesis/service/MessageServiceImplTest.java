package jslozano.thesis.service;

import jslozano.thesis.converter.MessageCommandToMessage;
import jslozano.thesis.converter.MessageToMessageCommand;
import jslozano.thesis.repositories.MessageRepository;
import jslozano.thesis.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class MessageServiceImplTest {
    MessageServiceImpl messageService;

    @Mock
    UserRepository userRepository;
    @Mock
    MessageRepository messageRepository;
    @Mock
    MessageCommandToMessage messageCommandToMessage;
    @Mock
    MessageToMessageCommand messageToMessageCommand;

    @BeforeEach
    void setUp() {
        messageService = new MessageServiceImpl(userRepository, messageRepository,
                messageCommandToMessage, messageToMessageCommand);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMessages() {
        /*
        //given
        User user = new User(); //ensure, optional it's not null
        user.setId(1L);
        Optional<User> userOptional = Optional.of(user);

        Set<Message> messages = new HashSet<>();

        Message message1 = new Message();
        message1.setId(1L);
        messages.add(message1);

        Message message2 = new Message();
        message2.setId(1L);
        messages.add(message2);

        user.setMessages(messages);
        //when
        when(userRepository.findById(anyLong())).thenReturn(userOptional);

        Set<Message> messagesMethod = messageService.getMessages(user.getId());


        //then
        assertEquals(2, messagesMethod.size());
        verify(userRepository, times(1)).findById(anyLong());

         */


    }

    @Test
    void getInboxMessages() {
    }

    @Test
    void findMessageById() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void saveMessage() {
    }

    @Test
    void saveMessageCommand() {
    }
}
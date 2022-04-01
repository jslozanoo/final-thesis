package jslozano.thesis.converter;

import jslozano.thesis.command.MessageCommand;
import jslozano.thesis.model.Message;
import jslozano.thesis.model.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageToMessageCommandTest {
    MessageToMessageCommand converter;
    public static Long ID_VALUE = 2L;
    public static String SUBJECT_VALUE = "Task";
    public static String ATTACHMENT_VALUE = "image.jpg";
    public static String BODY_VALUE = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, " +
            "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua";
    public static String USER_SENT_OR_TO_SEND_VALUE = "jslozano";
    public static Long LABEL_ID = 1L;
    public static Type TYPE_VALUE = Type.INBOX;


    @BeforeEach
    void setUp() {
        converter = new MessageToMessageCommand(new LabelToLabelCommand());
    }
    @Test
    void testNullSource(){
        assertNull(converter.convert(null));
    }
    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new Message()));
    }
    @Test
    void testLabelNull(){
        //given
        Message message = new Message();
        message.setId(ID_VALUE);
        message.setSubject(SUBJECT_VALUE);
        message.setAttachment(ATTACHMENT_VALUE);
        message.setBody(BODY_VALUE);
        message.setUserSentOrToSend(USER_SENT_OR_TO_SEND_VALUE);
        message.setType(TYPE_VALUE);
        message.setLabels(null);

        // when
        MessageCommand messageCommand = converter.convert(message);

        //then
        assertNull(messageCommand.getLabels());
        assertEquals(ID_VALUE, messageCommand.getId());
        assertEquals(SUBJECT_VALUE, messageCommand.getSubject());
        assertEquals(ATTACHMENT_VALUE, messageCommand.getAttachment());
        assertEquals(BODY_VALUE, messageCommand.getBody());
        assertEquals(USER_SENT_OR_TO_SEND_VALUE, messageCommand.getUserSentOrToSend());
        assertEquals(TYPE_VALUE, messageCommand.getType());
    }

    @Test
    void convert() {
    }
}
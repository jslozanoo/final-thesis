package jslozano.thesis.converter;

import jslozano.thesis.command.LabelCommand;
import jslozano.thesis.command.MessageCommand;
import jslozano.thesis.model.Message;
import jslozano.thesis.model.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class MessageCommandToMessageTest {
    MessageCommandToMessage converter;
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
        converter = new MessageCommandToMessage(new LabelCommandToLabel());
    }

    @Test
    void testNullSource(){
        assertNull(converter.convert(null));
    }
    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new MessageCommand()));
    }
    @Test
    void testLabelNull(){
        //given
        MessageCommand messageCommand = new MessageCommand();
        messageCommand.setId(ID_VALUE);
        messageCommand.setSubject(SUBJECT_VALUE);
        messageCommand.setAttachment(ATTACHMENT_VALUE);
        messageCommand.setBody(BODY_VALUE);
        messageCommand.setUserSentOrToSend(USER_SENT_OR_TO_SEND_VALUE);
        messageCommand.setType(TYPE_VALUE);
        messageCommand.setLabels(null);

        // when
        Message message = converter.convert(messageCommand);

        //then
        assertNull(messageCommand.getLabels());
        assertEquals(ID_VALUE, message.getId());
        assertEquals(SUBJECT_VALUE, message.getSubject());
        assertEquals(ATTACHMENT_VALUE, message.getAttachment());
        assertEquals(BODY_VALUE, message.getBody());
        assertEquals(USER_SENT_OR_TO_SEND_VALUE, message.getUserSentOrToSend());
        assertEquals(TYPE_VALUE, message.getType());
    }
    @Test
    void convert() {
        //given
        MessageCommand messageCommand = new MessageCommand();
        messageCommand.setId(ID_VALUE);
        messageCommand.setSubject(SUBJECT_VALUE);
        messageCommand.setAttachment(ATTACHMENT_VALUE);
        messageCommand.setBody(BODY_VALUE);
        messageCommand.setUserSentOrToSend(USER_SENT_OR_TO_SEND_VALUE);
        messageCommand.setType(TYPE_VALUE);
        messageCommand.setLabels(new HashSet<LabelCommand>());

        // when
        Message message = converter.convert(messageCommand);

        //then

        assertEquals(ID_VALUE, message.getId());
        assertEquals(SUBJECT_VALUE, message.getSubject());
        assertEquals(ATTACHMENT_VALUE, message.getAttachment());
        assertEquals(BODY_VALUE, message.getBody());
        assertEquals(USER_SENT_OR_TO_SEND_VALUE, message.getUserSentOrToSend());
        assertEquals(TYPE_VALUE, message.getType());
        assertNotNull(messageCommand.getLabels());

    }
}
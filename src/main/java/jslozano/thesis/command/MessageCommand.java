package jslozano.thesis.command;

import jslozano.thesis.model.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Slf4j
public class MessageCommand {
    private Long id;
    private Long userId;
    private String subject;
    private String attachment;

    private String body;
    private String userSentOrToSend;
    private Type type;
    private Set<LabelCommand> labels;

    public static MessageCommand copyForInbox(MessageCommand messageCommand,
                                              String userSent){
        MessageCommand messageCommandCopy = new MessageCommand();
        messageCommandCopy.setSubject(messageCommand.getSubject());
        messageCommandCopy.setAttachment(messageCommand.getAttachment());
        messageCommandCopy.setBody(messageCommand.getBody());
        messageCommandCopy.setUserSentOrToSend(userSent);

        return messageCommand;
    }

}

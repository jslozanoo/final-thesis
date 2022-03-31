package jslozano.thesis.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@NoArgsConstructor
public class MessageCommand {
    private Long id;
    private String subject;
    private String attachment;

    private String body;
    private String userSentOrToSend;
}

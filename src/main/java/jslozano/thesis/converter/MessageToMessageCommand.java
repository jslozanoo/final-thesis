package jslozano.thesis.converter;

import jslozano.thesis.command.MessageCommand;
import jslozano.thesis.model.Message;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MessageToMessageCommand implements Converter<Message, MessageCommand> {
    private final LabelToLabelCommand labelToLabelCommand;

    public MessageToMessageCommand(LabelToLabelCommand labelToLabelCommand) {
        this.labelToLabelCommand = labelToLabelCommand;
    }

    @Nullable
    @Synchronized
    @Override
    public MessageCommand convert(Message source) {
        if(source == null){
            return null;
        }
        MessageCommand messageCommand = new MessageCommand();
        messageCommand.setId(source.getId());
        messageCommand.setSubject(source.getSubject());
        messageCommand.setBody(source.getBody());
        messageCommand.setAttachment(source.getAttachment());
        messageCommand.setUserSentOrToSend(source.getUserSentOrToSend());
        messageCommand.setType(source.getType());

        if(source.getLabels() != null && source.getLabels().size() > 0){
            source.getLabels().forEach(label -> messageCommand.getLabels()
                    .add(labelToLabelCommand.convert(label)));
        }

        return messageCommand;
    }
}

package jslozano.thesis.converter;

import jslozano.thesis.command.MessageCommand;
import jslozano.thesis.model.Message;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class MessageCommandToMessage implements Converter<MessageCommand, Message> {
    private final LabelCommandToLabel labelCommandToLabel;

    public MessageCommandToMessage(LabelCommandToLabel labelCommandToLabel) {
        this.labelCommandToLabel = labelCommandToLabel;
    }

    @Nullable
    @Synchronized
    @Override
    public Message convert(MessageCommand source) {
        if(source == null){
            return null;
        }
        Message message = new Message();
        message.setId(source.getId());
        message.setSubject(source.getSubject());
        message.setBody(source.getBody());
        message.setAttachment(source.getAttachment());
        message.setUserSentOrToSend(source.getUserSentOrToSend());
        message.setType(source.getType());

        if(source.getLabels() != null && source.getLabels().size() > 0){
            source.getLabels().forEach(label -> message.getLabels()
                    .add(labelCommandToLabel.convert(label)));
        }

        return message;
    }
}

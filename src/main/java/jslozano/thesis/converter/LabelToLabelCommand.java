package jslozano.thesis.converter;

import jslozano.thesis.command.LabelCommand;
import jslozano.thesis.model.Label;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LabelToLabelCommand implements Converter<Label, LabelCommand> {
    @Nullable
    @Synchronized
    @Override
    public LabelCommand convert(Label source) {
        if(source == null){
            return null;
        }
        LabelCommand labelCommand = new LabelCommand();

        labelCommand.setId(source.getId());
        labelCommand.setName(source.getName());

        return labelCommand;
    }
}

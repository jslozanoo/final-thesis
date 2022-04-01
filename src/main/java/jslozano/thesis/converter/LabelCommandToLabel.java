package jslozano.thesis.converter;

import jslozano.thesis.command.LabelCommand;
import jslozano.thesis.model.Label;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class LabelCommandToLabel implements Converter<LabelCommand, Label> {
    @Nullable
    @Synchronized
    @Override
    public Label convert(LabelCommand source) {
        if(source == null){
            return null;
        }
        Label label = new Label();

        label.setId(source.getId());
        label.setName(source.getName());

        return label;
    }
}

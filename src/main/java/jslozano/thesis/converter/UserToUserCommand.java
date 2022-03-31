package jslozano.thesis.converter;

import jslozano.thesis.command.UserCommand;
import jslozano.thesis.model.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserToUserCommand implements Converter<User, UserCommand> {
    @Synchronized
    @Nullable
    @Override
    public UserCommand convert(User source) {
        if(source == null) {
            return null;
        }
        UserCommand userCommand = new UserCommand();

        userCommand.setUserName(source.getUserName());
        userCommand.setPassword(source.getPassword());
        userCommand.setFirstName(source.getFirstName());
        userCommand.setLastName(source.getLastName());
        userCommand.setIdentificationNumber(source.getIdentificationNumber());
        userCommand.setIdentificationNumberString(Long.toString(source.getIdentificationNumber()));
        userCommand.setAddress(source.getAddress());
        userCommand.setZipCode(source.getZipCode());
        userCommand.setState(source.getState());
        userCommand.setCountry(source.getCountry());

        return userCommand;
    }
}

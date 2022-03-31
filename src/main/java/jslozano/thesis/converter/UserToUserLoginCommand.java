package jslozano.thesis.converter;

import jslozano.thesis.command.UserLoginCommand;
import jslozano.thesis.model.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserToUserLoginCommand implements Converter<User, UserLoginCommand> {
    @Synchronized
    @Nullable
    @Override
    public UserLoginCommand convert(User source) {
        if(source == null) {
            return null;
        }
        UserLoginCommand userLoginCommand = new UserLoginCommand();
        userLoginCommand.setId(source.getId());
        userLoginCommand.setUserName(source.getUserName());
        userLoginCommand.setPassword(source.getPassword());

        return userLoginCommand;
    }
}

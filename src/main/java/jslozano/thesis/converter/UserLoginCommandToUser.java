package jslozano.thesis.converter;

import jslozano.thesis.command.UserLoginCommand;
import jslozano.thesis.model.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserLoginCommandToUser implements Converter<UserLoginCommand, User> {
    @Synchronized
    @Nullable
    @Override
    public User convert(UserLoginCommand source) {
        if(source == null) {
            return null;
        }
        User user = new User();
        user.setId(source.getId());
        user.setUserName(source.getUserName());
        user.setPassword(source.getPassword());

        return user;
    }
}

package jslozano.thesis.converter;

import jslozano.thesis.command.UserCommand;
import jslozano.thesis.model.User;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class UserCommandToUser implements Converter<UserCommand, User> {
    @Synchronized
    @Nullable
    @Override
    public User convert(UserCommand source) {
        if(source == null) {
            return null;
        }

        User user = new User();

        // If something broke, i change the next line
        user.setId(source.getId());
        user.setUserName(source.getUserName());
        user.setPassword(source.getPassword());
        user.setFirstName(source.getFirstName());
        user.setLastName(source.getLastName());

        if (source.getIdentificationNumberString() == null){
            user.setIdentificationNumber(null);
        }
        else {
            user.setIdentificationNumber(Long.parseLong(source.getIdentificationNumberString()));
        }

        user.setAddress(source.getAddress());
        user.setZipCode(source.getZipCode());
        user.setState(source.getState());
        user.setCountry(source.getCountry());

        return user;
    }
}

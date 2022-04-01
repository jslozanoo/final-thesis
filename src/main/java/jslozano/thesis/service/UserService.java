package jslozano.thesis.service;

import jslozano.thesis.command.UserCommand;
import jslozano.thesis.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findUserByUsernameAndPassword(String user, String password);
    Optional<User> findByUserName(String userName);
    UserCommand saveUserCommand(UserCommand userCommand);
    User findById(Long id);
    UserCommand findCommandById(Long id);


}

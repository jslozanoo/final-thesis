package jslozano.thesis.service;

import jslozano.thesis.command.UserCommand;
import jslozano.thesis.converter.UserCommandToUser;
import jslozano.thesis.converter.UserToUserCommand;
import jslozano.thesis.model.User;
import jslozano.thesis.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserCommandToUser userCommandToUser;
    private final UserToUserCommand userToUserCommand;

    public UserServiceImpl(UserRepository userRepository, UserCommandToUser userCommandToUser, UserToUserCommand userToUserCommand) {
        this.userRepository = userRepository;
        this.userCommandToUser = userCommandToUser;
        this.userToUserCommand = userToUserCommand;
    }


    @Override
    public Optional<User> findUserByUsernameAndPassword(String username, String password) {
        Optional<User> user = userRepository.findByUserNameAndPassword(username, password);
        if(user.isEmpty()){
            throw new RuntimeException("User not Found");
        }

        return user;
    }

    @Override
    public Optional<User> findByUserName(String userName) {
        Optional<User> user = userRepository.findByUserName(userName);

        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return user;
    }

    @Override
    public User findById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isEmpty()){
            throw new RuntimeException("User Not Found");
        }
        return userOptional.get();
    }

    @Override
    public UserCommand findCommandById(Long id) {
        return userToUserCommand.convert(findById(id));
    }

    @Override
    public UserCommand saveUserCommand(UserCommand userCommand) {
        User userDetached = userCommandToUser.convert(userCommand);
        User userSaved = userRepository.save(userDetached);

        return userToUserCommand.convert(userSaved);
        /*
        userDetached it's now a POJO and can be persisted in the database. userCommand it's what I
        obtain after submitted the form of create user. userSaved has the id property
         */
    }
}

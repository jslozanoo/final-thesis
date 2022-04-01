package jslozano.thesis.service;

import jslozano.thesis.command.UserCommand;
import jslozano.thesis.converter.UserCommandToUser;
import jslozano.thesis.converter.UserToUserCommand;
import jslozano.thesis.model.User;
import jslozano.thesis.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    UserCommandToUser userCommandToUser;

    @Mock
    UserToUserCommand userToUserCommand;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository, userCommandToUser, userToUserCommand);
    }

    @Test
    void findUserByUsernameAndPassword() {
        //given
        User user = new User();
        user.setUserName("pepito");
        user.setPassword("qwerty");
        Optional<User> userOptional = Optional.of(user);

        //when
        Mockito.when(this.userRepository.findByUserNameAndPassword(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(userOptional);
        // Test if the service it's doing its job
        User userReturned = userService
                .findUserByUsernameAndPassword("pepito" ,"qwerty").get();

        //then
        assertNotNull(userReturned);
        verify(this.userRepository, times(1))
                .findByUserNameAndPassword(anyString(), anyString());
    }

    @Test
    void findByUserName() {
        //given
        User user = new User();
        user.setUserName("pepito");
        Optional<User> userOptional = Optional.of(user);

        //when
        when(this.userRepository.findByUserName(anyString())).thenReturn(userOptional);
        // Test if the service it's doing its job
        User userReturned = userService.findByUserName("pepito").get();

        //then
        assertNotNull(userReturned);
        verify(this.userRepository, times(1)).findByUserName(anyString());

    }

    @Test
    void findById() {
        //given
        User user = new User(); //ensure, optional it's not null
        user.setId(1L);
        Optional<User> userOptional = Optional.of(user);

        //when
        when(this.userRepository.findById(anyLong())).thenReturn(userOptional);
        // Calling the service, for passing the mock.
        User userReturned = userService.findById(1L);

        //then
        assertNotNull(userReturned);
        verify(this.userRepository, times(1)).findById(anyLong());
    }

    @Test
    void findCommandById() {
        //given
        User user = new User(); //ensure, optional it's not null
        user.setId(1L);
        Optional<User> userOptional = Optional.of(user);

        //when
        when(this.userRepository.findById(anyLong())).thenReturn(userOptional);

        //given
        UserCommand userCommand = new UserCommand();
        userCommand.setId(1L);

        when(userToUserCommand.convert(any())).thenReturn(userCommand);

        // kaboom

        UserCommand commandById = userService.findCommandById(1L);

        //then
        assertNotNull(commandById);
        verify(this.userRepository, times(1)).findById(anyLong());
        verify(userToUserCommand, times(1)).convert(any());
    }

    @Test
    void saveUserCommand() {
        // given
        UserCommand command = new UserCommand();
        command.setId(1L);

        User user = new User();
        user.setId(1L);

        when(userCommandToUser.convert(any())).thenReturn(user);

        User savedUser = new User();
        savedUser.setId(1L);

        when(this.userRepository.save(any())).thenReturn(savedUser);

        UserCommand detachedSavedUser = new UserCommand();
        detachedSavedUser.setId(1L);

        when(userToUserCommand.convert(any())).thenReturn(detachedSavedUser);

        //when

        UserCommand commandSaved = userService.saveUserCommand(command);

        //then
        assertEquals(1L, commandSaved.getId());
        verify(userToUserCommand, times(1)).convert(any());
        verify(userCommandToUser, times(1)).convert(any());
        verify(this.userRepository, times(1)).save(any());



    }
}
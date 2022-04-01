package jslozano.thesis.converter;

import jslozano.thesis.command.UserCommand;
import jslozano.thesis.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserToUserCommandTest {
    UserToUserCommand converter;

    public static Long ID_VALUE = 1L;
    public static String USER_NAME_VALUE = "jlozano";
    public static String PASSWORD = "qwerty";
    public static String FIRSTNAME = "juan";
    public static String LASTNAME = "pepito";
    public static Long IDENTIFICATION_NUMBER = 123456789L;
    public static String ADDRESS = "av 123 street 12";
    public static String ZIPCODE = "110321";
    public static String STATE = "new york";
    public static String COUNTRY = "united states";
    public static String IDENTIFICATION_NUMBER_STRING = "123456789";

    @BeforeEach
    void setUp() {
        converter = new UserToUserCommand();
    }

    @Test
    void testNullSource(){
        assertNull(converter.convert(null));
    }
    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new User()));
    }

    @Test
    void convert() {
        //given
        User user = new User();
        user.setId(ID_VALUE);
        user.setUserName(USER_NAME_VALUE);
        user.setPassword(PASSWORD);
        user.setFirstName(FIRSTNAME);
        user.setLastName(LASTNAME);
        user.setIdentificationNumber(IDENTIFICATION_NUMBER);
        user.setAddress(ADDRESS);
        user.setZipCode(ZIPCODE);
        user.setState(STATE);
        user.setCountry(COUNTRY);

        //when
        UserCommand userCommand = converter.convert(user);

        //then

        assertEquals(ID_VALUE, userCommand.getId());
        assertEquals(USER_NAME_VALUE, userCommand.getUserName());
        assertEquals(PASSWORD, userCommand.getPassword());
        assertEquals(FIRSTNAME, userCommand.getFirstName());
        assertEquals(LASTNAME, userCommand.getLastName());
        assertEquals(IDENTIFICATION_NUMBER, userCommand.getIdentificationNumber());
        assertEquals(ADDRESS, userCommand.getAddress());
        assertEquals(ZIPCODE, userCommand.getZipCode());
        assertEquals(STATE, userCommand.getState());
        assertEquals(COUNTRY, userCommand.getCountry());
        assertEquals(IDENTIFICATION_NUMBER_STRING, userCommand.getIdentificationNumberString());

    }
}
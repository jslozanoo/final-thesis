package jslozano.thesis.converter;

import jslozano.thesis.command.UserCommand;
import jslozano.thesis.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserCommandToUserTest {
    UserCommandToUser converter;

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
        converter = new UserCommandToUser();
    }

    @Test
    void testNullSource(){
        assertNull(converter.convert(null));
    }
    @Test
    void testEmptyObject(){
        assertNotNull(converter.convert(new UserCommand()));
    }

    @Test
    void convert() {
        //given
        UserCommand userCommand = new UserCommand();
        userCommand.setId(ID_VALUE);
        userCommand.setUserName(USER_NAME_VALUE);
        userCommand.setPassword(PASSWORD);
        userCommand.setFirstName(FIRSTNAME);
        userCommand.setLastName(LASTNAME);
        userCommand.setIdentificationNumber(IDENTIFICATION_NUMBER);
        userCommand.setAddress(ADDRESS);
        userCommand.setZipCode(ZIPCODE);
        userCommand.setState(STATE);
        userCommand.setCountry(COUNTRY);
        userCommand.setIdentificationNumberString(IDENTIFICATION_NUMBER_STRING);


        //when
        User user = converter.convert(userCommand);


        //then

        assertEquals(ID_VALUE, user.getId());
        assertEquals(USER_NAME_VALUE, user.getUserName());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(FIRSTNAME, user.getFirstName());
        assertEquals(LASTNAME, user.getLastName());
        assertEquals(IDENTIFICATION_NUMBER, user.getIdentificationNumber());
        assertEquals(ADDRESS, user.getAddress());
        assertEquals(ZIPCODE, user.getZipCode());
        assertEquals(STATE, user.getState());
        assertEquals(COUNTRY, user.getCountry());


    }
}
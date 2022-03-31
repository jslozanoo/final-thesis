package jslozano.thesis.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCommand {
    private Long id;

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private Long identificationNumber;
    private String address;
    private String zipCode; // First Digit may be 0
    private String state;
    private String country;
    private String identificationNumberString;
}

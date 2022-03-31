package jslozano.thesis.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginCommand {
    private Long id;

    @NotBlank(message = "Username it's mandatory")
    private String userName;
    @NotBlank(message = "Password it's mandatory")
    private String password;

}

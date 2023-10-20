package vader.lab.demo.payload;

import vader.lab.demo.validation.PasswordMatching;
import vader.lab.demo.validation.StrongPassword;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@PasswordMatching(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "Password and Confirm Password must be matched!"
)
public class SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @StrongPassword
    private String password;

    private String confirmPassword;

}

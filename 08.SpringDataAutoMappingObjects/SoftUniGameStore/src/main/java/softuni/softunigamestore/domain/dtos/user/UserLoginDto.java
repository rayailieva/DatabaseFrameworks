package softuni.softunigamestore.domain.dtos.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserLoginDto {

    private String email;
    private String password;

    public UserLoginDto(){
    }

    public UserLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @NotNull(message = "Email cannot be null.")
    @Pattern(regexp = "[0-9a-zA-z]+@[a-zA-z]+\\.[a-z]{2,4}", message = "Invalid Email.")
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotNull(message = "Password cannot be null.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?!.*[&%$]).{6,}$", message = "Password must contain at least 1 uppercase, 1 lowercase letter and 1 digit.")
    @Size(min = 6, message = "Password must be at least 6 symbols long.")
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

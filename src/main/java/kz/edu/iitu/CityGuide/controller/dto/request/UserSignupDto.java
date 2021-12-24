package kz.edu.iitu.CityGuide.controller.dto.request;

import kz.edu.iitu.CityGuide.controller.dto.BaseUserDto;
import kz.edu.iitu.CityGuide.feature.validation.user.CheckPassword;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
public class UserSignupDto extends BaseUserDto {
    @CheckPassword
    private final String password;

    public UserSignupDto(String email, String username, String password) {
        super(email, username);
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserSignupDto{" +
                "email='" + getEmail()+ '\'' +
                ", username='" + getUsername() + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}

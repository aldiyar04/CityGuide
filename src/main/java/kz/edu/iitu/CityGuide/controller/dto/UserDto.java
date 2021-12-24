package kz.edu.iitu.CityGuide.controller.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import kz.edu.iitu.CityGuide.repository.entity.User;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
public class UserDto extends BaseUserDto {
    @NotBlank(message = "User role cannot be blank")
    @Size(min = 4, max = 32, message = "User role must be 4-32 characters long")
    private final String role;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private final LocalDate createdOn;

    private UserDto(String email, String username, String role, LocalDate createdOn) {
        super(email, username);
        this.role = role;
        this.createdOn = createdOn;
    }

    public static UserDto build(User user) {
        return new UserDto(
                user.getEmail(),
                user.getUsername(),
                user.getRole(),
                user.getCreatedOn()
        );
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + getEmail() + '\'' +
                ", username=" + getUsername() +
                ", role='" + role + '\'' +
                ", createdOn=" + createdOn +
                '}';
    }
}

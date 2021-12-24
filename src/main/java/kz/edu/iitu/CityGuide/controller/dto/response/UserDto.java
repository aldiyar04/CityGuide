package kz.edu.iitu.CityGuide.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import kz.edu.iitu.CityGuide.controller.dto.BaseUserDto;
import kz.edu.iitu.CityGuide.feature.validation.user.CheckUserRole;
import kz.edu.iitu.CityGuide.repository.entity.User;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class UserDto extends BaseUserDto {
    @CheckUserRole
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

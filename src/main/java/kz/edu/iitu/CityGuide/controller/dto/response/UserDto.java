package kz.edu.iitu.CityGuide.controller.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import kz.edu.iitu.CityGuide.controller.dto.BaseUserDto;
import kz.edu.iitu.CityGuide.repository.entity.User;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends BaseUserDto {
    @NotBlank(message = "User role cannot be blank")
    @Size(min = 4, max = 32, message = "User role must be 4-32 characters long")
    private final String role;

    @JsonFormat(pattern = "dd.MM.yyyy")
    private final LocalDate createdOn;

    private final BigDecimal bigDecimal;

    private UserDto(String email, String username, String role, LocalDate createdOn, BigDecimal bigDecimal) {
        super(email, username);
        this.role = role;
        this.createdOn = createdOn;
        this.bigDecimal = bigDecimal;
    }

    public static UserDto build(User user) {
        return new UserDto(
                user.getEmail(),
                user.getUsername(),
                user.getRole(),
                user.getCreatedOn(),
                user.getBigDecimal()
        );
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "email='" + getEmail() + '\'' +
                ", username=" + getUsername() +
                ", role='" + role + '\'' +
                ", createdOn=" + createdOn +
                ", bigDecimal=" + bigDecimal +
                '}';
    }
}

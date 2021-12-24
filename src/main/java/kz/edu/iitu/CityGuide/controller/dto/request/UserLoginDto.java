package kz.edu.iitu.CityGuide.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
@ToString
public class UserLoginDto {
    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 32, message = "Username must be 4-32 characters long")
    private final String username;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, max = 64, message = "Password must be 8-64 characters long")
    private final String password;
}

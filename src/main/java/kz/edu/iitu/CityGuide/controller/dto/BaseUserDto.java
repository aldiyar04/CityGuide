package kz.edu.iitu.CityGuide.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public abstract class BaseUserDto {
    @NotBlank(message = "Email cannot be blank")
    @Size(max = 64, message = "Email cannot be longer than 64 characters")
    @Email(message = "Email must be valid")
    private final String email;

    @NotBlank(message = "Username cannot be blank")
    @Size(min = 4, max = 32, message = "Username must be 4-32 characters long")
    private final String username;
}

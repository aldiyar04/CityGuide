package kz.edu.iitu.CityGuide.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Size;

@AllArgsConstructor
@Getter
public class UserUpdateDto {
    @Size(max = 64, message = "Email must be at most 64 characters")
    private final String email;

    @Size(min = 4, max = 32, message = "Username must be 4-32 characters long")
    private final String username;
}

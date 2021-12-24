package kz.edu.iitu.CityGuide.controller.dto;

import kz.edu.iitu.CityGuide.feature.validation.user.CheckEmail;
import kz.edu.iitu.CityGuide.feature.validation.user.CheckUsername;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class BaseUserDto {
    @CheckEmail
    private final String email;

    @CheckUsername
    private final String username;
}

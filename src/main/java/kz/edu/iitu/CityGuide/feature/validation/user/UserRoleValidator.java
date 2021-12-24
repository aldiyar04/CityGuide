package kz.edu.iitu.CityGuide.feature.validation.user;

import kz.edu.iitu.CityGuide.repository.entity.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class UserRoleValidator implements ConstraintValidator<CheckUserRole, String> {
    @Override
    public boolean isValid(String role, ConstraintValidatorContext constraintValidatorContext) {
        return Objects.equals(role, User.ROLE_USER) || Objects.equals(role, User.ROLE_ADMIN);
    }
}

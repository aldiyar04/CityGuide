package kz.edu.iitu.CityGuide.feature.validation.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotBlank(message = "User role cannot be blank")
@Size(min = 4, max = 32, message = "User role must be 4-32 characters long")
@Constraint(validatedBy = {UserRoleValidator.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckUserRole {
    String message() default "User role is not acceptable";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

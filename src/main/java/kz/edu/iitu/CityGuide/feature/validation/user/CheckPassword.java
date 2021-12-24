package kz.edu.iitu.CityGuide.feature.validation.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotBlank(message = "Password cannot be blank")
@Size(min = 8, max = 64, message = "Password must be 8-64 characters long")
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPassword {
    String message() default "Password is not acceptable";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

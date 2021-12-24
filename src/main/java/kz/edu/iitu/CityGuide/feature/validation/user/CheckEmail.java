package kz.edu.iitu.CityGuide.feature.validation.user;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotBlank(message = "Email cannot be blank")
@Size(max = 64, message = "Email must be at most 64 characters")
@Email(message = "Email must be valid")
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckEmail {
    String message() default "Email is not acceptable";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
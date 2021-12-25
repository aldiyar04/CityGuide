package kz.edu.iitu.CityGuide.feature.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotNull;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotNull(message = "ID cannot be null")
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckId {
    String message() default "ID is not acceptable";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

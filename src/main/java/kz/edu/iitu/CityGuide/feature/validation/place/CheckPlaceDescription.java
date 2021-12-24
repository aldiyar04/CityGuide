package kz.edu.iitu.CityGuide.feature.validation.place;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Size(min = 64, max = 4096, message = "Place description must be 64-4096 characters long")
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckPlaceDescription {
    String message() default "Place description is not acceptable";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

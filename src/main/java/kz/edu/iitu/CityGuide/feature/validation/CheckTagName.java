package kz.edu.iitu.CityGuide.feature.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@NotBlank(message = "Tag name cannot be blank")
@Size(min = 2, max = 64, message = "Tag name must be 2-64 characters long")
@Constraint(validatedBy = {})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckTagName {
    String message() default "Tag name is not acceptable";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

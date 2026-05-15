package br.com.joaomonteiro.restaurantBlue.validation;

import jakarta.validation.Constraint;
import org.springframework.validation.annotation.Validated;

import java.lang.annotation.*;

@Constraint(validatedBy = CpfValidationValidator.class)
@Documented
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CpfValidation {

    String message() default "CPF inválido";

    Class<?>[] groups() default {};

    Class<? extends jakarta.validation.Payload>[] payload() default {};

}

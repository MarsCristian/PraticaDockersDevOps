package br.ufscar.dc.dsw.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UniquedTelefoneValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface UniquedTelefone {
    String message() default "Telefone is already registered";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

package br.com.zup.proposta.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Documented
@Constraint(validatedBy = {})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@CPF
@CNPJ
public @interface CPFouCNPJ {

    String message() default "Insira um CPF ou CNPJ válido.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}

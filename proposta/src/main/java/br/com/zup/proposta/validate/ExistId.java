package br.com.zup.proposta.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {ExistIdValidador.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistId {

String message() default "Este dado não existe no banco de dados."; 
	
	Class<?>[] groups() default { };
	
	Class<? extends Payload>[] payload() default { };
	
	String fieldName();
	
	Class<?> domainClass();
}

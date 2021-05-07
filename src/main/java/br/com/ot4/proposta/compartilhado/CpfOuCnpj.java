package br.com.ot4.proposta.compartilhado;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@ConstraintComposition(CompositionType.OR)

@Constraint(validatedBy = { })
@CPF @CNPJ
public @interface CpfOuCnpj {
	String message() default "";
	
	Class<?>[] groups() default{};
	
	Class<? extends Payload>[] payload() default {};
}
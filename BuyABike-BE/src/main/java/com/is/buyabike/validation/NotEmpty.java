package com.is.buyabike.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmpty {
	String message() default "String may not be empty";
	
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}

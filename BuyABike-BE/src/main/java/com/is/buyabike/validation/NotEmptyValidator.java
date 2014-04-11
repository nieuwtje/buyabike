package com.is.buyabike.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public class NotEmptyValidator implements ConstraintValidator<NotEmpty, String> {
	public void initialize(NotEmpty notempty) {
	}

	public boolean isValid(String str, ConstraintValidatorContext cvc) {
		return str != null && !str.equals("");
	}
}

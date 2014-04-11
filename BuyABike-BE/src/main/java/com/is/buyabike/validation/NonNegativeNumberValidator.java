package com.is.buyabike.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.stereotype.Component;

@Component
public abstract class NonNegativeNumberValidator implements ConstraintValidator<NonNegativeNumber, Double> {
	public void initialize(NonNegativeNumber notempty) {
	}

	public boolean isValid(double value, ConstraintValidatorContext cvc) {
		return value >= 0;
	}
}

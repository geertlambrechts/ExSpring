package be.abis.exercise.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import be.abis.exercise.annotation.VerifLogin;
import be.abis.exercise.model.Person;

public class LoginValidator implements ConstraintValidator<VerifLogin, Person> {

	@Override
	public boolean isValid(Person p, ConstraintValidatorContext context) {
		
		return true;
	}
}

package be.abis.exercise.annotation;

import java.lang.annotation.Retention;

import javax.validation.Constraint;
import javax.validation.Payload;

import be.abis.exercise.validator.LoginValidator;

import java.lang.annotation.*;

@Target(ElementType.TYPE)	
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LoginValidator.class)

public @interface VerifLogin {
	String message() default "Combination emain address - password not found";
	
	Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

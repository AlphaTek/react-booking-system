package com.stationbelleville.StationBelleville.Validators;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.stationbelleville.StationBelleville.Domain.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
		return User.class.equals(aClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		User user = (User) object;

		if (user.getPassword().length() < 7) {
			errors.rejectValue("password", "Length", "Password must be at least 7 characters");
		}

		if (!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("confirmPassword", "Match", "Passwords must match");
		}

	}
}

package com.winnovate.didpatients.validation;

import java.util.List;

import com.winnovate.didpatients.model.LoginRequest;

public class UserNameValidator implements GenericValidator<LoginRequest>{

	@Override
	public List<Edits> validate(LoginRequest t) {
		return null;
	}

}

package com.winnovate.didpatients.validation;

import java.util.List;

public interface GenericValidator<T> {

	public List<Edits> validate(T t);
}

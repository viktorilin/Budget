package com.valsoft.dto.annotation;

import com.valsoft.dao.IUserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by sanya on 07.07.2017.
 */
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

	@Autowired
	private IUserDAO userDAO;

	@Override
	public void initialize(UniqueUsername uniqueUsername) {

	}

	@Override
	@Transactional(readOnly = true)
	public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
		return !userDAO.usernameExists(username);
	}
}

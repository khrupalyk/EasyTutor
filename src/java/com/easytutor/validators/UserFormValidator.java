package com.easytutor.validators;

import com.easytutor.dao.UserDAO;
import com.easytutor.models.RegisteredUser;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by root on 24.07.15.
 */
public class UserFormValidator implements Validator {

    private UserDAO userDAO;

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisteredUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        RegisteredUser user = (RegisteredUser) o;

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "confirm.user.confirmPassword");

        if (user.getPassword().trim().isEmpty() || user.getConfirmPassword().trim().isEmpty()) {

            if (user.getPassword().trim().isEmpty())
                errors.rejectValue("password", "empty.user.confirmPassword");

            if (user.getConfirmPassword().trim().isEmpty())
                errors.rejectValue("confirmPassword", "empty.user.confirmPassword");


        } else if (!user.getPassword().equals(user.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "user.confirmPassword");
        }

        if (user.getUsername().trim().isEmpty()) {
            errors.rejectValue("username", "empty.user.username");
        } else if (userDAO.isUserExistWithSuchName(user.getUsername())) {
            errors.rejectValue("username", "user.username");
        }

    }


    public UserDAO getUserDAO() {
        return userDAO;
    }

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }
}

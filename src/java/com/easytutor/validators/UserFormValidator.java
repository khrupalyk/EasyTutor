package com.easytutor.validators;

import com.easytutor.models.RegisteredUser;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by root on 24.07.15.
 */
public class UserFormValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return RegisteredUser.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        RegisteredUser user = (RegisteredUser)o;

//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "confirm.user.confirmPassword");

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            System.out.println("ERROR");
            errors.rejectValue("confirmPassword", "Ok.user.confirmPassword");
        }else {
            System.out.println("ERRORwwww");

        }


    }
}

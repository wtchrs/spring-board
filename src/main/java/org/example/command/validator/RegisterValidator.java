package org.example.command.validator;

import org.example.command.RegisterCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class RegisterValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return RegisterCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RegisterCommand regCommand = (RegisterCommand) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "id", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nickname", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordConfirm", "required");
        if (!regCommand.isPasswordEqualToConfirm()) {
            errors.rejectValue("passwordConfirm", "notMatchingToPassword");
        }
    }
}

package org.example.command.validator;

import org.example.command.CreateBoardCommand;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CreateBoardValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return CreateBoardCommand.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required");
    }
}

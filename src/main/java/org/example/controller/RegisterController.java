package org.example.controller;

import org.example.command.RegisterCommand;
import org.example.command.validator.RegisterValidator;
import org.example.exception.DuplicateUserIdException;
import org.example.exception.WrongIdPasswordException;
import org.example.service.UserRegisterService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserRegisterService registerService;

    public RegisterController(UserRegisterService regSvc) {
        registerService = regSvc;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new RegisterValidator());
    }

    @GetMapping
    public String agree() {
        return "register/agree";
    }

    @PostMapping("/form")
    public String form(@ModelAttribute("registerCommand") RegisterCommand command,
                       @RequestParam(value = "agree", defaultValue = "false") boolean agree) {
        if (!agree) {
            return "register/agree";
        }
        return "register/form";
    }

    @GetMapping("/form")
    public String formGet() {
        return "register/agree";
    }

    @PostMapping("/submit")
    public String submit(@ModelAttribute("registerCommand") @Valid RegisterCommand command, Errors errors) {
        if (errors.hasErrors()) {
            return "register/form";
        }
        try {
            registerService.register(command);
            return "register/submit";
        } catch (DuplicateUserIdException e) {
            errors.rejectValue("id", "duplicate");
            return "register/form";
        } catch (WrongIdPasswordException e) {
            errors.reject("notMatchingIdPassword");
            return "register/form";
        }
    }
}

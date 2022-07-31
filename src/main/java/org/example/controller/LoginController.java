package org.example.controller;

import org.example.command.LoginCommand;
import org.example.command.validator.LoginValidator;
import org.example.exception.WrongIdPasswordException;
import org.example.service.AuthInfo;
import org.example.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class LoginController {

    private final AuthService authService;

    public LoginController(AuthService authService) {
        this.authService = authService;
    }

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new LoginValidator());
    }

    @GetMapping("/login")
    public String form(@ModelAttribute("login") LoginCommand login, HttpSession session) {
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        if (authInfo != null) {
            return "redirect:/main";
        }

        return "login/form";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("login") @Valid LoginCommand login, Errors errors, HttpServletRequest request) {
        if (errors.hasErrors()) {
            return "login/form";
        }

        HttpSession session = request.getSession();

        // TODO: Implement keeping logged in

        try {
            AuthInfo authInfo = authService.authenticate(login.getId(), login.getPassword());
            session.setAttribute("authInfo", authInfo);
            return "redirect:/main";
        } catch (WrongIdPasswordException e) {
            errors.reject("notMatchingIdPassword");
            return "login/form";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/main";
    }
}

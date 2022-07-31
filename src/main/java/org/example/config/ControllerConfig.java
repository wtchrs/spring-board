package org.example.config;

import org.example.controller.LoginController;
import org.example.controller.RegisterController;
import org.example.service.AuthService;
import org.example.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    private AuthService authService;
    private UserRegisterService userRegisterService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setUserRegisterService(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @Bean
    public RegisterController registerController() {
        return new RegisterController(userRegisterService);
    }

    @Bean
    public LoginController loginController() {
        return new LoginController(authService);
    }
}

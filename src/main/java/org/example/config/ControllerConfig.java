package org.example.config;

import org.example.controller.BoardController;
import org.example.controller.LoginController;
import org.example.controller.RegisterController;
import org.example.service.AuthService;
import org.example.service.BoardService;
import org.example.service.PostService;
import org.example.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ControllerConfig {

    private AuthService authService;
    private UserRegisterService userRegisterService;
    private BoardService boardService;
    private PostService postService;

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @Autowired
    public void setUserRegisterService(UserRegisterService userRegisterService) {
        this.userRegisterService = userRegisterService;
    }

    @Autowired
    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @Autowired
    public void setPostService(PostService postService) {
        this.postService = postService;
    }

    @Bean
    public RegisterController registerController() {
        return new RegisterController(userRegisterService);
    }

    @Bean
    public LoginController loginController() {
        return new LoginController(authService);
    }

    @Bean
    public BoardController boardController() {
        return new BoardController(boardService, postService);
    }
}

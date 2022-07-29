package org.example.config;

import org.example.dao.BoardDao;
import org.example.dao.PostDao;
import org.example.dao.UserDao;
import org.example.service.PasswordChangeService;
import org.example.service.UserRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    private UserDao userDao;
    private BoardDao boardDao;
    private PostDao postDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setBoardDao(BoardDao boardDao) {
        this.boardDao = boardDao;
    }

    @Autowired
    public void setPostDao(PostDao postDao) {
        this.postDao = postDao;
    }

    @Bean
    public UserRegisterService userRegisterService() {
        return new UserRegisterService(userDao);
    }

    @Bean
    public PasswordChangeService passwordChangeService() {
        return new PasswordChangeService(userDao);
    }
}

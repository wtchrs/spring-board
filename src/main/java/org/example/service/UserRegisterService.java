package org.example.service;

import org.example.command.RegisterCommand;
import org.example.dao.UserDao;
import org.example.exception.DuplicateUserIdException;
import org.example.exception.WrongIdPasswordException;
import org.example.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

public class UserRegisterService {

    private final UserDao userDao;

    public UserRegisterService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public Long register(RegisterCommand registerCommand) {
        User user = userDao.selectById(registerCommand.getId());

        if (user != null) throw new DuplicateUserIdException();
        if (!registerCommand.isPasswordEqualToConfirm()) throw new WrongIdPasswordException();

        User newUser = new User(registerCommand.getId(), registerCommand.getPassword(), registerCommand.getNickname(),
                                LocalDateTime.now());
        userDao.insert(newUser);
        return newUser.getNo();
    }
}

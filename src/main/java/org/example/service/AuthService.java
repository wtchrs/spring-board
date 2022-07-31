package org.example.service;

import org.example.dao.UserDao;
import org.example.exception.WrongIdPasswordException;
import org.example.model.User;

public class AuthService {

    private final UserDao userDao;

    public AuthService(UserDao userDao) {
        this.userDao = userDao;
    }

    public AuthInfo authenticate(String id, String password) {
        User user = userDao.selectById(id);

        if (user == null || !user.matchPassword(password)) {
            throw new WrongIdPasswordException();
        }

        return new AuthInfo(user.getNo(), user.getId(), user.getNickname());
    }

}

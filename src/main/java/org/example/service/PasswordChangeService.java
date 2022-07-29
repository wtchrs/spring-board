package org.example.service;

import org.example.dao.UserDao;
import org.example.exception.UserNotFoundException;
import org.example.exception.WrongIdPasswordException;
import org.example.model.User;
import org.springframework.transaction.annotation.Transactional;

public class PasswordChangeService {

    private final UserDao userDao;

    public PasswordChangeService(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public void passwordChange(String id, String oldPassword, String newPassword) {
        User user = userDao.selectById(id);

        if (user == null) throw new UserNotFoundException();

        user.changePassword(oldPassword, newPassword);
        userDao.update(user);
    }
}

package org.example.dao;

import org.example.model.User;

import java.util.List;

public interface UserDao {
    User selectByNo(Long no);

    User selectById(String id);

    List<User> selectAll();

    int count();

    void insert(User user);

    void update(User user);
}

package org.example.service;

import org.example.dao.UserDao;
import org.example.model.Post;
import org.example.model.User;

public class PostService {

    private final UserDao userDao;

    public PostService(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getAuthor(Post post) {
        return userDao.selectByNo(post.getAuthorNo());
    }
}

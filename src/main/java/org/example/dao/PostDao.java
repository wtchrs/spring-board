package org.example.dao;

import org.example.model.Post;

import java.util.List;

public interface PostDao {
    List<Post> selectByBoardNo(Long boardNo);

    List<Post> selectByAuthorNo(Long authorNo);

    List<Post> selectByTitleAndContent(String contains);

    void insert(Post post);

    void update(Post post);

    void delete(Post post);

    void increaseViews(Long no);
}

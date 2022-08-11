package org.example.dao;

import org.example.model.Board;

import java.util.List;

public interface BoardDao {
    Board selectByNo(Long no);

    Board selectByTitle(String title);

    List<Board> selectAll();

    void insert(Board board);

    void update(Board board);
}

package org.example.service;

import org.example.dao.BoardDao;
import org.example.dao.PostDao;
import org.example.exception.BoardNotFoundException;
import org.example.exception.DuplicateBoardTitleException;
import org.example.model.Board;
import org.example.model.Post;

import java.time.LocalDateTime;
import java.util.List;

public class BoardService {

    private final BoardDao boardDao;
    private final PostDao postDao;

    public BoardService(BoardDao boardDao, PostDao postDao) {
        this.boardDao = boardDao;
        this.postDao = postDao;
    }

    public List<Board> getBoards() {
        return boardDao.selectAll();
    }

    public Long createBoard(String title) {
        Board board = boardDao.selectByTitle(title);
        if (board != null) {
            throw new DuplicateBoardTitleException();
        }
        Board newBoard = new Board(title, LocalDateTime.now());
        boardDao.insert(newBoard);
        return newBoard.getNo();
    }

    public void changeTitle(Long no, String newTitle) {
        Board target = boardDao.selectByNo(no);
        if (target == null) {
            throw new BoardNotFoundException();
        }
        Board board = boardDao.selectByTitle(newTitle);
        if (board != null) {
            throw new DuplicateBoardTitleException();
        }
        target.setTitle(newTitle);
        boardDao.update(target);
    }

    public List<Post> getPostsByBoardTitle(String title) {
        Board board = boardDao.selectByTitle(title);
        return postDao.selectByBoardNo(board.getNo());
    }

    // TODO: Implement methods related to board services.
}

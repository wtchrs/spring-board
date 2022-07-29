package org.example.model;

import java.time.LocalDateTime;

public class Post {
    private Long no;
    private String board;
    private String title;
    private String content;
    private final String author;
    private final LocalDateTime date;
    private Long views;

    public Post(String board, String title, String content, String author, LocalDateTime date) {
        this.board = board;
        this.title = title;
        this.content = content;
        this.author = author;
        this.date = date;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getBoard() {
        return board;
    }

    public void setBoard(String board) {
        this.board = board;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Long getViews() {
        return views;
    }

    public void setViews(Long views) {
        this.views = views;
    }

    public void increaseViews() {
        views++;
    }
}

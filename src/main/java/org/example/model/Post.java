package org.example.model;

import java.time.LocalDateTime;

public class Post {
    private Long no;
    private Long boardNo;
    private final Long authorNo;
    private String title;
    private String content;
    private final LocalDateTime date;
    private Long views;

    public Post(Long boardNo, Long authorNo, String title, String content, LocalDateTime date) {
        this.boardNo = boardNo;
        this.title = title;
        this.content = content;
        this.authorNo = authorNo;
        this.date = date;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public Long getBoardNo() {
        return boardNo;
    }

    public void setBoardNo(Long boardNo) {
        this.boardNo = boardNo;
    }

    public Long getAuthorNo() {
        return authorNo;
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

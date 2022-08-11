package org.example.model;

import java.time.LocalDateTime;

public class Board {

    private Long no;
    private String title;
    private final LocalDateTime openDate;

    public Board(String title, LocalDateTime openDate) {
        this.title = title;
        this.openDate = openDate;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getOpenDate() {
        return openDate;
    }
}

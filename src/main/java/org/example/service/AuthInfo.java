package org.example.service;

public class AuthInfo {

    private final Long no;
    private final String id;
    private final String nickname;

    public AuthInfo(Long no, String id, String nickname) {
        this.no = no;
        this.id = id;
        this.nickname = nickname;
    }

    public Long getNo() {
        return no;
    }

    public String getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }
}

package org.example.model;


import org.example.exception.WrongIdPasswordException;

import java.time.LocalDateTime;

public class User {

    private Long no;
    private final String id;
    private String password;
    private String nickname;
    private final LocalDateTime registerDateTime;

    public User(String id, String password, String nickname, LocalDateTime registerDateTime) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.registerDateTime = registerDateTime;
    }

    public Long getNo() {
        return no;
    }

    public void setNo(Long no) {
        this.no = no;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void changePassword(String currentPassword, String newPassword) {
        if (!this.password.equals(currentPassword)) {
            throw new WrongIdPasswordException();
        }
        this.password = newPassword;
    }

    public boolean matchPassword(String password) {
        return this.password.equals(password);
    }
}

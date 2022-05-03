package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {

    private Long userId;
    private String login;
    private String password;
    private List<Chatroom> allRooms;
    private List<Chatroom> userRooms;

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getUserId().equals(user.getUserId()) && getLogin().equals(user.getLogin()) && getPassword().equals(user.getPassword()) && Objects.equals(getAllRooms(), user.getAllRooms()) && Objects.equals(getUserRooms(), user.getUserRooms());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getLogin(), getPassword(), getAllRooms(), getUserRooms());
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Chatroom> getAllRooms() {
        return allRooms;
    }

    public void setAllRooms(List<Chatroom> allRooms) {
        this.allRooms = allRooms;
    }

    public List<Chatroom> getUserRooms() {
        return userRooms;
    }

    public void setUserRooms(List<Chatroom> userRooms) {
        this.userRooms = userRooms;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", allRooms=" + allRooms +
                ", userRooms=" + userRooms +
                '}';
    }
}

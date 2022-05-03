package edu.school21.models;

import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String Password;
    private boolean authenticationStatus;


    public User(Long id, String login, String password, boolean authenticationStatus) {
        this.id = id;
        this.login = login;
        Password = password;
        this.authenticationStatus = authenticationStatus;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean isAuthenticationStatus() {
        return authenticationStatus;
    }

    public void setAuthenticationStatus(boolean authenticationStatus) {
        this.authenticationStatus = authenticationStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return isAuthenticationStatus() == user.isAuthenticationStatus() && Objects.equals(getId(), user.getId()) && Objects.equals(getLogin(), user.getLogin()) && Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), isAuthenticationStatus());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", Password='" + Password + '\'' +
                ", authenticationStatus=" + authenticationStatus +
                '}';
    }
}

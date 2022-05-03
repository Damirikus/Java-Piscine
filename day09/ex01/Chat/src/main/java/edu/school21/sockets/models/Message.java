package edu.school21.sockets.models;

import java.time.LocalDateTime;
import java.util.Objects;

public class Message {

    private Long id;
    private User sender;
    private String message;
    private LocalDateTime date;

    public Message(Long id, User sender, String message, LocalDateTime date) {
        this.id = id;
        this.sender = sender;
        this.message = message;
        this.date = date;
    }

    public Message(User sender, String message, LocalDateTime date) {
        this.sender = sender;
        this.message = message;
        this.date = date;
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", sender=" + sender +
                ", message='" + message + '\'' +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(getId(), message1.getId()) && Objects.equals(getSender(), message1.getSender()) && Objects.equals(getMessage(), message1.getMessage()) && Objects.equals(getDate(), message1.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getSender(), getMessage(), getDate());
    }
}

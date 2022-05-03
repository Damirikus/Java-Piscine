package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message {

    private Long messageId;
    private User author;
    private Chatroom room;
    private String text;
    private LocalDateTime date;

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Chatroom getRoom() {
        return room;
    }

    public void setRoom(Chatroom room) {
        this.room = room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(getMessageId(), message.getMessageId()) && Objects.equals(getAuthor(), message.getAuthor()) && Objects.equals(getRoom(), message.getRoom()) && Objects.equals(getText(), message.getText()) && Objects.equals(getDate(), message.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessageId(), getAuthor(), getRoom(), getText(), getDate());
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ",\n author=" + author +
                ",\n room=" + room +
                ",\n text='" + text + '\'' +
                ",\n date=" + date +
                '}';
    }
}

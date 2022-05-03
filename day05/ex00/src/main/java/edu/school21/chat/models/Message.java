package edu.school21.chat.models;

import java.util.Date;
import java.util.Objects;

public class Message {

    private Long messageId;
    private User author;
    private Chatroom room;
    private String text;
    private Date date;

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public Long getMessageId() {
        return messageId;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return getMessageId().equals(message.getMessageId()) && getAuthor().equals(message.getAuthor()) && getRoom().equals(message.getRoom()) && getText().equals(message.getText()) && getDate().equals(message.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMessageId(), getAuthor(), getRoom(), getText(), getDate());
    }


    @Override
    public String toString() {
        return "Message{" +
                "messageId=" + messageId +
                ", author=" + author +
                ", room=" + room +
                ", text='" + text + '\'' +
                ", date=" + date +
                '}';
    }
}

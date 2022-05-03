package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class Chatroom {

    private Long chatroomId;
    private String name;
    private User owner;
    private List<Message> messages;

    public Long getChatroomId() {
        return chatroomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chatroom chatroom = (Chatroom) o;
        return getChatroomId().equals(chatroom.getChatroomId()) && getName().equals(chatroom.getName()) && getOwner().equals(chatroom.getOwner()) && Objects.equals(getMessages(), chatroom.getMessages());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getChatroomId(), getName(), getOwner(), getMessages());
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "chatroomId=" + chatroomId +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", messages=" + messages +
                '}';
    }
}

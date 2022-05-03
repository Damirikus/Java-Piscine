package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

public class Program {

    public static void main(String[] args) {

        MessagesRepositories messagesRepositories = new MessagesRepositoryJdbcImpl();
        Optional<Message> messageOptional = messagesRepositories.findById(3L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("LLLLLLLLLLL");
            message.setDate(null);
            messagesRepositories.update(message);
        }
    }
}

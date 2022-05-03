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
        UsersRepositories usersRepositories = new UsersRepositoriesJdbcImpl();
        ChatroomRepositories chatroomRepositories = new ChatroomRepositoriesJdbcImpl();

        Optional<Message> messageOptional = messagesRepositories.findById(2L);
        User user = usersRepositories.findById(1L).get();
        Chatroom chatroom = chatroomRepositories.findById(1L).get();

        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setAuthor(user);
            message.setRoom(chatroom);
            message.setText("LLLLLLLLLLL");
            message.setDate(LocalDateTime.now());
            messagesRepositories.update(message);
        }
    }
}

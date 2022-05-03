package edu.school21.chat.app;

import edu.school21.chat.models.Chatroom;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {

    public static void main(String[] args) {

        MessagesRepositories repositoryJdbc = new MessagesRepositoryJdbcImpl();
        UsersRepositories usersRepositories = new UsersRepositoriesJdbcImpl();
        ChatroomRepositories chatroomRepositories = new ChatroomRepositoriesJdbcImpl();

        User author = usersRepositories.findById(1L).get();
        Chatroom room = chatroomRepositories.findById(2L).get();

//        User author = new User(30L, "user", "user", new ArrayList(), new ArrayList());
//        Chatroom room = new Chatroom(8L, "room", author, new ArrayList());

        Message message = new Message(null, author, room, "Hello all!", LocalDateTime.now());
        repositoryJdbc.save(message);

        System.out.println(message.getMessageId());



    }
}

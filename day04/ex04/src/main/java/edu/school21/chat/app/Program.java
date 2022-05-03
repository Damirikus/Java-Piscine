package edu.school21.chat.app;


import edu.school21.chat.models.User;
import edu.school21.chat.repositories.*;


import java.util.Collections;
import java.util.List;


public class Program {

    public static void main(String[] args) {

        UsersRepositories usersRepositories = new UsersRepositoriesJdbcImpl();

        List<User> users = usersRepositories.findAll(2, 10);
        users.forEach(System.out::println);
    }
}

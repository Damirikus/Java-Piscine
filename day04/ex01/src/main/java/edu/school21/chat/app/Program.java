package edu.school21.chat.app;

import edu.school21.chat.models.Message;
import edu.school21.chat.repositories.MessagesRepositoryJdbcImpl;

import java.util.Scanner;

public class Program {

    public static void main(String[] args) {

        MessagesRepositoryJdbcImpl repositoryJdbc = new MessagesRepositoryJdbcImpl();
        Scanner scanner = new Scanner(System.in);
        while (true){

            Long id = scanner.nextLong();
            Message message = repositoryJdbc.findById(id).get();
            System.out.println(message);

            if (!scanner.hasNext()) break;

        }
//


    }
}

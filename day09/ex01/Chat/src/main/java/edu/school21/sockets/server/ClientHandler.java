package edu.school21.sockets.server;

import edu.school21.sockets.models.Message;
import edu.school21.sockets.models.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientHandler implements Runnable {
    private Server server;
    private PrintWriter outMessage;
    private Scanner inMessage;
    private User user;

    private Socket clientSocket = null;

    private static int clients_count = 0;

    public ClientHandler(Socket socket, Server server) {
        try {
            this.server = server;
            this.clientSocket = socket;
            this.outMessage = new PrintWriter(socket.getOutputStream());
            this.inMessage = new Scanner(socket.getInputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            outMessage.println("Hello from server!");
            outMessage.flush();
            while (true){
                if (inMessage.hasNext()) {
                    String command = inMessage.nextLine();
                    if (command.equals("signUp")){
                        signUpR();
                    }
                    if (command.equals("signIn")){
                        user = signInR();
                        if (user == null){
                            outMessage.println("Bad credentials!");
                            outMessage.flush();
                        } else {
                            break;
                        }
                    }
                }
            }
            server.clients.add(this);
            clients_count++;
            outMessage.println("Start messaging!");
            server.sendMessageToAllClients("New participant come into the chat!");
            server.sendMessageToAllClients("Count of clients = " + clients_count);

            while (true) {
                try {
                    String clientMessage = inMessage.nextLine();
                    Message message = new Message(user, clientMessage, LocalDateTime.now());
                    server.getMessagesRepository().save(message);
                    System.out.println(clientMessage);
                    server.sendMessageToAllClients(user.getUsername() + ": " + clientMessage);
                } catch (Exception e){
                    break;
                }
            }
        }
        finally {
            this.close();
        }
    }

    private void signUpR(){
        outMessage.println("Enter username:\n");
        outMessage.flush();
        String name = inMessage.nextLine();
        outMessage.println("Enter password:\n");
        outMessage.flush();
        String pass = inMessage.nextLine();
        outMessage.println("Successful!\n");
        outMessage.flush();
        server.getUsersService().signUp(name, pass);

    }

    private User signInR(){
        outMessage.println("Enter username:\n");
        outMessage.flush();
        String name = inMessage.nextLine();
        outMessage.println("Enter password:\n");
        outMessage.flush();
        String pass = inMessage.nextLine();
        User us = null;
        try {
            us = server.getUsersService().signIn(name, pass);

        } catch (Exception e){
            outMessage.println("Not found user by username!\n");
            outMessage.flush();
        }
        return  us;
    }

    public void sendMsg(String msg) {
        try {
            outMessage.println(msg);
            outMessage.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void close() {
        server.removeClient(this);
        clients_count--;
        server.sendMessageToAllClients("Count of clients = " + clients_count);
    }
}
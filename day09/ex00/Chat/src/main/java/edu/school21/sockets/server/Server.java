package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private final int port;
    private UsersService usersService;

    public Server(int port) {
        this.port = port;
        usersService = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class)
                .getBean(UsersServiceImpl.class);
    }

    public void run(){
        try {

            ServerSocket serverSocket = new ServerSocket(port);
            Socket client = serverSocket.accept();

            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            BufferedWriter writer= new BufferedWriter( new OutputStreamWriter(client.getOutputStream()));
            writer.write("Hello from Server!\n");
            writer.flush();

            String command = reader.readLine();
            writer.write("Enter username:\n");
            writer.flush();
            String name = reader.readLine();
            writer.write("Enter password:\n");
            writer.flush();
            String pass = reader.readLine();
            writer.write("Successful!\n");
            writer.flush();
            usersService.signUp(name, pass);


            reader.close();
            writer.close();
            client.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

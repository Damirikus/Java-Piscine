package edu.school21.sockets.server;

import edu.school21.sockets.config.SocketsApplicationConfig;
import edu.school21.sockets.repositories.MessagesRepositoryImpl;
import edu.school21.sockets.services.UsersService;
import edu.school21.sockets.services.UsersServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    static int PORT;
    ArrayList<ClientHandler> clients = new ArrayList<>();
    private UsersService usersService;
    private MessagesRepositoryImpl messagesRepository;

    public Server(int port) {
        PORT = port;
        ApplicationContext context = new AnnotationConfigApplicationContext(SocketsApplicationConfig.class);
        this.usersService = context.getBean(UsersServiceImpl.class);
        this.messagesRepository = context.getBean(MessagesRepositoryImpl.class);
        run();
    }

    public void run(){
        Socket clientSocket = null;
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("Server is start!");
            while (true) {
                clientSocket = serverSocket.accept();
                ClientHandler client = new ClientHandler(clientSocket, this);
                new Thread(client).start();
            }
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            try {
                clientSocket.close();
                System.out.println("Server is stop!");
                serverSocket.close();
            }
            catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public ArrayList<ClientHandler> getClients() {
        return clients;
    }

    public void setClients(ArrayList<ClientHandler> clients) {
        this.clients = clients;
    }

    public UsersService getUsersService() {
        return usersService;
    }

    public void setUsersService(UsersService usersService) {
        this.usersService = usersService;
    }

    public MessagesRepositoryImpl getMessagesRepository() {
        return messagesRepository;
    }

    public void setMessagesRepository(MessagesRepositoryImpl messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    public void sendMessageToAllClients(String msg) {
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

}

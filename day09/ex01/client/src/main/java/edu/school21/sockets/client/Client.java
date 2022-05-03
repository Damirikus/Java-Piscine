package edu.school21.sockets.client;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_HOST = "localhost";
    private static int SERVER_PORT;
    private Socket clientSocket;
    private Scanner inMessage;
    private PrintWriter outMessage;

    // конструктор
    public Client(int port) {
        SERVER_PORT = port;
        try {
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            inMessage = new Scanner(clientSocket.getInputStream());
            outMessage = new PrintWriter(clientSocket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        listen();
        sendMessage();
    }

    public void listen(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        if (inMessage.hasNext()) {
                            String inMes = inMessage.nextLine();
                            System.out.println(inMes);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }).start();
    }

    public void sendMessage(){
        Scanner scanner = new Scanner(System.in);

        while (true){
            if (scanner.hasNext()){
                String str = scanner.nextLine();
                if (str.equals("Exit")){
                    System.out.println("You have left the chat.");
                    System.exit(0);
                }
                outMessage.println(str);
                outMessage.flush();
            }
        }
    }
}
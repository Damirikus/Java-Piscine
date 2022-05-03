package edu.school21.sockets.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public void run(int port){
        Socket clientSocket = null;
        try {
            clientSocket = new Socket("127.0.0.1", 8081);
            Scanner scanner = new Scanner(System.in);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter writer= new BufferedWriter( new OutputStreamWriter(clientSocket.getOutputStream()));

            System.out.println(reader.readLine());

            String command = scanner.nextLine();
            writer.write(command+ "\r");
            writer.flush();

            System.out.println(reader.readLine());
            String name = scanner.nextLine();
            writer.write(name + "\r");
            writer.flush();

            System.out.println(reader.readLine());
            String pass = scanner.nextLine();
            writer.write(pass+ "\r");
            writer.flush();

            System.out.println(reader.readLine());

            reader.close();
            writer.close();
            clientSocket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

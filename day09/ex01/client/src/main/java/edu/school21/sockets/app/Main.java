package edu.school21.sockets.app;

import edu.school21.sockets.client.Client;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        if (args.length != 1){
            System.out.println("Enter the port!");
            return;
        }
        String[] arg = args[0].split("=");
        int port = Integer.parseInt(arg[1]);
        new Client(port);
    }
}

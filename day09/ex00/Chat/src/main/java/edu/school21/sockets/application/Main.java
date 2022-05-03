package edu.school21.sockets.application;

import edu.school21.sockets.server.Server;


public class Main {
    public static void main(String[] args) {

        if (args.length != 1){
            System.out.println("Enter the port!");
            return;
        }
        String[] arg = args[0].split("=");
        int port = Integer.parseInt(arg[1]);

        Server server = new Server(port);
        server.run();

    }
}

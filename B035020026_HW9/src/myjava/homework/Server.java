package myjava.homework;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    //private final int timeout = 15000;

    private static int CheckInt( String s ) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return -1;
        }
        return Integer.valueOf(s);
    }

    public static void main(String args[]) throws IOException {

        int a, b, port;
        // Check input
        a = CheckInt(args[0]);
        b = CheckInt(args[1]);
        port = CheckInt(args[2]);
        if (a < 0 || b < 0 || port < 0) {
            System.out.println("wrong input.");
            return;
        }

        // Create server Listening Socket
        ServerSocket server = new ServerSocket(port);
        Socket client = null;
        System.out.println("Server Start Success.");
        System.out.printf("Port:%d\n", port);
        System.out.printf("Resource:< A : %d, B : %d >\n", a, b);
        System.out.println("Listening Now");

        int nums = 0;
        while (true) { // Run until kill process
            // Wait for connection
            client = server.accept();

            // Create Thread
            System.out.printf("[Client_%d] : ", ++nums);
            new Thread(new ServerThread(client, a, b)).start();
        }
        //server.close();
    }
}

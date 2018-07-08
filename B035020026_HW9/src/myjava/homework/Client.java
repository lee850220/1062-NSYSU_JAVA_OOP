package myjava.homework;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Client {
    private final int timeout = 10000;
    private String address; // Server IP
    private int port;

    Client( int a, int b, String address, int port ) throws IOException {
        this.address = address;
        this.port = port;

        // Create socket
        System.out.printf("Resource requirement : < A : %d, B : %d >\n", a, b);
        System.out.printf("Connecting... (timeout=%d)\n\n", timeout);
        Socket socket = new Socket(address, port);
        socket.setSoTimeout(timeout);

        // Read Stream from keyboard
        //BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));

        // Send Stream to Socket
        PrintStream out = new PrintStream(socket.getOutputStream());

        // Receive Stream from Socket
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String rev = null;
        while (true) {
            // Send msg to Socket
            out.println(String.valueOf(a) + " " + String.valueOf(b));

            // Wait for receive msg from Socket
            try {
                rev = in.readLine();
                if (rev != null) {
                    System.out.println(rev);
                    break;
                }
            } catch (SocketTimeoutException e) {
                System.out.println("[Error] : Time out, no response.");
                break;
            }
        }
    }

    private static int CheckInt( String s ) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return -1;
        }
        return Integer.valueOf(s);
    }

    public static void main(String args[]) {
        int a, b, port;
        // Check input
        a = CheckInt(args[0]);
        b = CheckInt(args[1]);
        port = CheckInt(args[3]);
        if (a < 0 || b < 0 || port < 0) {
            System.out.println("wrong input.");
            return;
        }
        try {
            Client c = new Client(a, b, args[2], port);
        } catch (IOException e) {
            System.out.println("Socket connection failed.\n" + e);
        }
    }
}


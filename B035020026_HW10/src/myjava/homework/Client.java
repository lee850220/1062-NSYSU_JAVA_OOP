package myjava.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.SocketTimeoutException;

public class Client {
    private final static int Connect_timeout = 10000;
    private static String address;
    private static final int PORT = 53026;
    static Socket socket = null;

    private static int CheckInt( String s ) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return -1;
        }
        return Integer.valueOf(s);
    }

    private static String RandomName() {
        StringBuilder name = new StringBuilder();
        // Name Length
        int length = (int) (Math.random() * (8 - 3 + 1) + 3); // Length= 3 ~ 8

        // First Letter
        name.append((char) (Math.random() * (90 - 65 + 1) + 65));

        // Follow Letter
        for (int i = 0; i < length - 1; i++) {
            name.append((char) (Math.random() * (122 - 97 + 1) + 97));
        }
        return name.toString();
    }

    public static void main( String args[] ) throws IOException { // input "threads" "address"

        int threads;
        // Check input
        if (args.length < 2) {
            System.out.println("[Error] : too few arguments.");
            return;
        }
        threads = CheckInt(args[0]);
        address = args[1];

        // Create socket
        System.out.printf("Connecting... (timeout=%d)\n\n", Connect_timeout);
        try {
            socket = new Socket(address, PORT);
            socket.setSoTimeout(Connect_timeout);
        } catch (IOException e) {
            System.out.println("[Error] : " + e);
            return;
        }

        // Receive Stream from Socket
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        String rev;

        // Wait for receive Welcome msg from Server
        try {
            rev = in.readLine();
            if (rev != null) {
                System.out.println(rev);
            }
        } catch (SocketTimeoutException e) {
            System.out.println("[Error] : Time out, no response.");
            return;
        }


        // Create Customers
        for (int i = 0; i < threads; i++) {
            new Thread(new Customer(RandomName())).start();
        }


        while (true) {

        }
    }
}

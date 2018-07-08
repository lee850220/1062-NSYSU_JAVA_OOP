package myjava.homework;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class Server {
    private static final int PORT = 53026;
    static QueueMachine qm = new QueueMachine();
    static Socket client;

    private static int CheckInt( String s ) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return -1;
        }
        return Integer.valueOf(s);
    }

    public static void main(String args[]) throws IOException { // input "threads"
        int threads;
        ArrayList<Thread> t;
        // Check input
        if (args.length < 1) {
            System.out.println("[Error] : too few arguments.");
            return;
        }
        threads = CheckInt(args[0]);
        if (threads < 0) {
            System.out.println("[Error] : wrong input.");
            return;
        }

        // Create server Listening Socket
        try {
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server Start Success.");
            System.out.printf("Port:%d\n", PORT);
            System.out.println("Listening Now\n");

            while (true) { // Run until kill process
                // Wait for connection
                client = server.accept();
                PrintStream out = new PrintStream(client.getOutputStream());
                out.println("Connection Established.");

                // Create QueueMachine
                Thread QM = new Thread(new QMThread());
                QM.setPriority(1);


                // Create Clerks
                t = new ArrayList<Thread>();
                char c = 'A';
                for (int i = 0; i < threads; i++) {
                    t.add(new Thread(new Clerk(String.valueOf((char)(c + i))))); // Create Clerks
                    t.get(i).setPriority(10);
                    t.get(i).start();
                }
                QM.start();

                while(true) {

                }
            }

        } catch (IOException e) {
            System.out.println(e + "\n");
        }
    }
}

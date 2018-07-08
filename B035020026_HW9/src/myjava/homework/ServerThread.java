package myjava.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class ServerThread implements Runnable{
    private Socket socket = null;
    private int A, B;

    ServerThread( Socket client, int a, int b ) {
        socket = client;
        A = a; B = b;
    }

    public void run() {
        try {
            // Send Stream to Socket
            PrintStream out = new PrintStream(socket.getOutputStream());

            // Receive Stream from Socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(true) {
                // Read msg from Socket
                String rev = in.readLine();
                if (rev == null || rev == "") break;

                int delim = rev.indexOf(" ");
                int req_a = Integer.valueOf(rev.substring(0, delim));
                int req_b = Integer.valueOf(rev.substring(delim + 1));
                //System.out.println("Get data:" + rev);

                // Process requirement
                if (req_a > this.A || req_b > this.B) {
                    System.out.println("Resource insufficient! Error...");
                    req_a = req_b = 0;
                    out.println(("[Error] : Resource quantity insufficient")); // Send msg to Socket
                } else {
                    System.out.printf("Take Resource -> A : %d, B : %d\n", req_a, req_b);
                    System.out.printf("                   Resource : A : %d, B : %d\n", this.A - req_a, this.B - req_b);
                    out.println(("Service finish")); // Send msg to Socket
                }
                System.out.printf("Replenishment -> A : %d, B : %d\n", req_a, req_b);
                System.out.printf("                   Resource : A : %d, B : %d\n", this.A, this.B);
            }

            // Close connection
            out.close();
            socket.close();

        } catch (IOException e) {

        }
    }
}

package myjava.homework;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;

public class Customer implements Runnable {
    private static final String[] tranType = {"deposit", "withdraw"};
    private static final Socket socket = Client.socket;
    private final String name;
    private static int nums = 0;

    private static PrintStream out;

    static {
        try {
            out = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Customer( String name ) {
        this.name = name;
    }

    public synchronized static void process(String name) {
        // Generate 5 transactions
        for (int i = 0; i < 5; i++) {
            int amount = (int) (Math.random() * (300 - 100 + 1) + 100); // transaction amount = Random @ 100~300
            String type = tranType[(int) (Math.random() * 2)]; // random Trantype

            // Send msg to Socket
            nums = nums + 1;
            out.println(nums + " " + name + " " + amount + " " + type);
            try {
                Thread.sleep((int) (Math.random() * (300 - 100 + 1) + 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void run() {
        process(name);
    }

}

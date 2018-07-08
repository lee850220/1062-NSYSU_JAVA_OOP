package myjava.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.Scanner;

public class Client extends ATM {

    private final static int Connect_timeout = 7000;
    private static String address;
    private static int port;
    private static Socket client;
    private static BufferedReader in;
    private static PrintStream out;

    private static int CheckInt( String s ) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return -1;
        }
        return Integer.valueOf(s);
    }

    private static String CheckPWD( String s) {
        if (s.length() != 6) return "len";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return "num";
        }
        return s;
    }

    public static void main(String[] args) {
        new Thread(new Client()::run).start();
    }

    @Override
    // forever running
    public void run() {
        Scanner scan = new Scanner(System.in);

        while (true) {
            boolean exit = false;
            int opt;
            String tmp;
            String acc, pwd;
            String msg, res;

            // input address
            System.out.println("Input your address");
            address = scan.nextLine();

            // input port
            while (true) {
                System.out.println("Input your port");
                tmp = scan.nextLine();
                port = CheckInt(tmp);
                if (port != -1) break;
                System.out.println("wrong input\n\n");
            }

            // create connection
            if (processConnection() == -1) continue;

            while (exit == false) {
                // get selection
                System.out.println("(1).Sign In\n(2).Create New Account\n(3).Exit\n-----------------------------------");
                tmp = scan.nextLine();
                opt = CheckInt(tmp);

                // process
                switch (opt) {
                    case 1: // Sign in
                    case 2: // Create account
                        System.out.println("Input your account");
                        acc = scan.nextLine();
                        System.out.println("Input your password");
                        while (true) {
                            pwd = CheckPWD(scan.nextLine());
                            if (pwd.equals("num")) {
                                System.out.println("password must integer, try again");
                            } else if (pwd.equals("len")) {
                                System.out.println("password must consist of 6 digits, try again");
                            } else break;
                        }
                        msg = opt + " " + acc + " " + pwd;
                        sendData(msg);
                        res = getStreams();
                        System.out.println(res);

                        // process ATM
                        if (res.equals("signed in")) {
                            while (true) {
                                System.out.println("(1).Deposit Cash\n(2).Get Cash\n(3).Balance\n(4).Withdraw\n-----------------------------------");
                                tmp = scan.nextLine();
                                opt = CheckInt(tmp);
                                if (opt >= 1 && opt <= 4) break;
                                System.out.println("wrong input\n");
                            }
                            sendData(String.valueOf(opt));

                            int amount;
                            switch (opt) {
                                case 1:
                                    while (true) {
                                        System.out.println("Input the amount of money you want to deposit");
                                        tmp = scan.nextLine();
                                        amount = CheckInt(tmp);
                                        if (amount != -1) break;
                                        System.out.println("wrong input\n");
                                    }
                                    sendData(String.valueOf(amount));
                                    System.out.println(getStreams());
                                    break;
                                case 2:
                                    while (true) {
                                        System.out.println("Input the amount of money you want to get");
                                        tmp = scan.nextLine();
                                        amount = CheckInt(tmp);
                                        if (amount != -1) break;
                                        System.out.println("wrong input\n");
                                    }
                                    sendData(String.valueOf(amount));
                                    System.out.println(getStreams());
                                    break;
                                case 3:
                                    System.out.println(getStreams());
                                    break;
                                default:
                            }
                        }
                        break;
                    case 3: // Exit
                        System.out.println("\n");
                        exit = true;
                        sendData("exit");
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        closeConnection();
                        break;
                    default:
                        System.out.println("wrong input\n");
                }
            }
        }
    }

    @Override
    // close connection
    public void closeConnection() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("\n");
        }
    }

    @Override
    // create connection
    public int processConnection() {
        try {
            System.out.println("connection..");
            client = new Socket(address, port);
            client.setSoTimeout(Connect_timeout);
            System.out.println("Connection Established.");
            out = new PrintStream(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            return 0;
        } catch (IOException e) {
            System.out.println("[Error] : " + e);
            return -1;
        }
    }

    @Override
    // send msg to server
    public void sendData( String message ) {
        out.println(message);
    }

    @Override
    // get msg from server
    public String getStreams() {
        String s = null;
        try {
            s = in.readLine();
            if (s != null) {
                return s;
            }
        } catch (SocketTimeoutException e) {
            System.out.println("[Error] : Time out, no response.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

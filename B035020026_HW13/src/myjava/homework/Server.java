package myjava.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Server extends ATM {

    private static int port;
    private static Socket client;
    private static ServerSocket server;
    private static BufferedReader in;
    private static PrintStream out;

    private static int CheckInt( String s ) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') return -1;
        }
        return Integer.valueOf(s);
    }

    public static void main(String[] args) {
        new Thread(new Server()::run).start();
        //while (true);
    }

    @Override
    public void run() {
        Scanner scan = new Scanner(System.in);
        String tmp, acc, pwd;
        int opt;

        // input port
        while (true) {
            System.out.println("Input your port");
            tmp = scan.nextLine();
            port = CheckInt(tmp);
            if (port != -1) break;
            System.out.println("wrong input\n\n");
        }

        // create socket
        try {
            server = new ServerSocket(port);
            System.out.println("Server Start Success.");
            System.out.printf("Port:%d\n", port);
            System.out.println("Listening Now\n");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        // process
        while (true) {
            // get connection
            System.out.println("Waiting for connection...");
            if (processConnection() == -1) continue;

            while (true) {
                // get account profile from client
                tmp = getStreams();
                ArrayList<String> data = new ArrayList<String>();
                while (true) {
                    int delim = tmp.indexOf(" ");
                    if (delim == -1) {
                        data.add(tmp.substring(0));
                        break;
                    }
                    data.add(tmp.substring(0, delim));
                    tmp = tmp.substring(delim + 1);
                }
                //System.out.println(tmp);
                if (data.get(0).equals("exit")) {
                    System.out.println("Client Exit. Reset Connection...\n");
                    break;
                }

                if (data.size() == 3) {
                    opt = CheckInt(data.get(0));
                    acc = data.get(1);
                    pwd = data.get(2);
                } else {
                    System.out.println("[Error] : Wrong data format. Reset connection...");
                    closeConnection();
                    return;
                }

                // process with database
                try {
                    // connect to MySQL server
                    Class.forName("com.mysql.jdbc.Driver");
                    String url = "jdbc:mysql://localhost:3306/java_hw13?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&useSSL=false";
                    String user = "root";
                    String pass = "root";
                    Connection conDB = DriverManager.getConnection(url, user, pass);
                    PreparedStatement ps;
                    ResultSet rs;
                    System.out.println("與資料庫連線成功");

                    // process
                    switch (opt) {
                        case 1: // Sign in
                            ps = conDB.prepareStatement("SELECT id FROM java_hw13.user WHERE id = ? AND password = ?");
                            ps.setString(1, acc);
                            ps.setString(2, pwd);
                            rs = ps.executeQuery();
                            try {
                                rs.next();
                                rs.getString(1);
                            } catch (SQLException e) {
                                // Fail to sign in
                                System.out.println("登入失敗: Wrong username or password");
                                sendData("Wrong username or password");
                                break;
                            }
                            // Signed in
                            System.out.printf("登入成功: account=%s\tpassword=%s\n", acc, pwd);
                            sendData("signed in");

                            // ATM service
                            int amount = -1;
                            int balance = -1;
                            opt = CheckInt(getStreams());
                            switch (opt) {
                                case 1:
                                    amount = CheckInt(getStreams());
                                    ps = conDB.prepareStatement("SELECT balance FROM java_hw13.money WHERE user = ?");
                                    ps.setString(1, acc);
                                    rs = ps.executeQuery();
                                    try {
                                        rs.next();
                                        balance = rs.getInt(1);
                                    } catch (SQLException e) {
                                        System.out.println("[Deposit Cash]: " + e);
                                        sendData(String.valueOf(balance));
                                        break;
                                    }
                                    ps.close();

                                    balance += amount;
                                    ps = conDB.prepareStatement("UPDATE java_hw13.money SET balance = ? WHERE user = ?");
                                    ps.setInt(1, balance);
                                    ps.setString(2, acc);
                                    ps.executeUpdate();
                                    System.out.printf("[Deposit Cash]: Process Succeed (OriBalance=%d, Amount=%d, PresBalance=%d)\n", balance-amount, amount, balance);
                                    sendData(String.format("Deposit $%d, $%d is in your account", amount, balance));
                                    ps.close();
                                    break;
                                case 2:
                                    amount = CheckInt(getStreams());
                                    ps = conDB.prepareStatement("SELECT balance FROM java_hw13.money WHERE user = ?");
                                    ps.setString(1, acc);
                                    rs = ps.executeQuery();
                                    try {
                                        rs.next();
                                        balance = rs.getInt(1);
                                    } catch (SQLException e) {
                                        System.out.println("[Get Cash]: " + e);
                                        sendData(String.valueOf(balance));
                                        break;
                                    }
                                    ps.close();

                                    if (balance < amount) {
                                        System.out.printf("[Get Cash]: Process Failed (Balance=%d, Amount=%d)\n", balance, amount);
                                        sendData("Get cash failed, no enough money.");
                                    } else {
                                        balance -= amount;
                                        ps = conDB.prepareStatement("UPDATE java_hw13.money SET balance = ? WHERE user = ?");
                                        ps.setInt(1, balance);
                                        ps.setString(2, acc);
                                        ps.executeUpdate();
                                        System.out.printf("[Get Cash]: Process Succeed (OriBalance=%d, Amount=%d, PresBalance=%d)\n", balance+amount, amount, balance);
                                        sendData(String.format("Get $%d, $%d is in your account", amount, balance));
                                        ps.close();
                                    }
                                    break;
                                case 3: // get Balance
                                    ps = conDB.prepareStatement("SELECT balance FROM java_hw13.money WHERE user = ?");
                                    ps.setString(1, acc);
                                    rs = ps.executeQuery();
                                    try {
                                        rs.next();
                                        balance = rs.getInt(1);
                                    } catch (SQLException e) {
                                        System.out.println("[Balance]: " + e);
                                        sendData(String.valueOf(balance));
                                        break;
                                    }
                                    System.out.println("[Balance]: Process Succeed");
                                    sendData(String.format("Balance: $%d is in your account", balance));
                                    break;
                                case 4:
                                    System.out.println("[Withdraw]: Process Succeed");
                                default:
                            }
                            break;
                        case 2: // Create Account
                            // Check duplicate
                            ps = conDB.prepareStatement("SELECT id FROM java_hw13.user WHERE id = ?");
                            ps.setString(1, acc);
                            rs = ps.executeQuery();
                            try {
                                rs.next();
                                rs.getString(1);
                            } catch (SQLException e) {
                                // Create account
                                //System.out.println("[Error] : " + e);
                                ps = conDB.prepareStatement("INSERT INTO user VALUES (?, ?)");
                                ps.setString(1, acc);
                                ps.setString(2, String.valueOf(pwd));
                                ps.executeUpdate();
                                System.out.printf("新增成功: account=%s\tpassword=%s\n", acc, pwd);
                                sendData("account established successfully");
                                ps.close();
                                ps = conDB.prepareStatement("INSERT INTO money VALUES (?, ?)");
                                ps.setString(1, acc);
                                ps.setInt(2, 0);
                                ps.executeUpdate();
                                break;
                            }
                            // Account existed
                            System.out.println("新增失敗: account already existed\n");
                            sendData("account already existed");
                            ps.close();
                            break;
                        default:
                    }

                } catch (SQLException | ClassNotFoundException e) {
                    System.out.println("[Error] : " + e);
                }
            }

            // close connection
            closeConnection();
        }
    }

    @Override
    public void closeConnection() {
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    // get connection
    public int processConnection() {
        try {
            // Wait for connection
            client = server.accept();
            out = new PrintStream(client.getOutputStream());
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            System.out.println("Connection Established.");
            return 0;
        } catch (IOException e) {
            System.out.print("[Error 001] : ");
            e.printStackTrace();
            return -1;
        }
    }

    @Override
    public void sendData( String message ) {
        out.println(message);
    }

    @Override
    public String getStreams() {
        String s = "";
        while (true) {
            try {
                while (true) {
                    s = in.readLine();
                    if (s == null || s.equals("")) {
                        Thread.sleep(1000);
                    } else {
                        return s;
                    }
                }
            } catch (SocketTimeoutException e) {
                System.out.println("[Error] : Time out, no response.");
                break;
            } catch (IOException e) {
                // do nothing
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

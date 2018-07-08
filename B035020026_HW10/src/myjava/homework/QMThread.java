package myjava.homework;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

public class QMThread implements Runnable{
    private static final int Wait_timeout = 200;
    private static final Socket socket = Server.client;
    private static QueueMachine qm = Server.qm;

    public void run() {
        synchronized (this) {
            try {
                // Send Stream to Socket
                PrintStream out = new PrintStream(socket.getOutputStream());

                // Receive Stream from Socket
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

                while (true) {
                    // Read msg from Socket
                    String rev = in.readLine();
                    //System.out.println(rev);
                    if (rev == null || rev.equals("")) break;

                    // Parse Data (name, balance, amount, tranType)
                    ArrayList<String> data = new ArrayList<String>();
                    while (true) {
                        int delim = rev.indexOf(" ");
                        if (delim == -1) {
                            data.add(rev.substring(0));
                            break;
                        }
                        data.add(rev.substring(0, delim));
                        rev = rev.substring(delim + 1);
                    }

                    if (data.size() < 4) {
                        if (data.get(0).equals("Finished")) {
                            out.println("Finished");
                            socket.close();
                            return;
                        } else {
                            System.out.println("[Error] : Data loss, transaction failed.");
                        }
                        return;
                    }

                    // Store Data
                    int num = Integer.valueOf(data.get(0));
                    String name = data.get(1);
                    int amount = Integer.valueOf(data.get(2));
                    String tranType = data.get(3);

                    // Check Database
                    if (BankDB.find(name) == -1) {
                        BankDB.createAcc(name);
                    }
                    int balance = BankDB.getBalance(name);

                    // Process
                    qm.add(new Transaction(num, new Account(name, balance), amount, tranType));
                    System.out.printf("Transaction[run: %d, account = Account[name: %s, balance = %d], amount = %d, tranType = %s]\n",
                            num, name, balance, amount, tranType);
                    wait(Wait_timeout);
                }

            } catch (IOException e) {
                System.out.println("[Caution] : " + e + "\n");
            } catch (InterruptedException e) {
                System.out.println("[Caution002] : " + e + "\n");
            }
        }
    }
}

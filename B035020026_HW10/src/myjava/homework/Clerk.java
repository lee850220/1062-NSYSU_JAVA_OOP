package myjava.homework;

import java.net.Socket;

import static java.lang.Thread.sleep;

class Clerk implements Runnable {
    private static final Socket socket = Server.client;
    private static final QueueMachine qm = Server.qm;
    private static final int Wait_timeout = 1000;
    private static final int Set_timeout = 300;
    private String name;

    Clerk(String name) {
        this.name = name;
    }

    public synchronized static void process(String name) {
        while (true) { // run forever...

            // Check queue
            try {
                // Send Stream to Socket
                //PrintStream out = new PrintStream(socket.getOutputStream());

                // Process
                Transaction transaction;
                // Waiting until data received
                while(true) {
                    if (qm.isEmpty()) {
                        sleep(Wait_timeout);
                    } else {
                        transaction = qm.get();
                        break;
                    }
                }

                String c_name = transaction.getAccount().getName();

                System.out.printf("Clerk %s ���o��� Transaction[run: %d, account = Account[name: %s, balance = %d], amount = %d, tranType = %s]\n", name,
                        transaction.getNum(), c_name , transaction.getAccount().getBalance(), transaction.getAmount(), transaction.getTranType());

                if (transaction.getTranType().equals("deposit")) {

                    try {
                        sleep(Set_timeout);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    BankDB.setBalance(c_name, BankDB.getBalance(c_name) + transaction.getAmount());
                    System.out.printf("Clerk %s ���� %d ������B�z�A%s �b��l�B: %d\n",
                            name, transaction.getNum(), c_name, BankDB.getBalance(c_name));
                    //out.printf("Clerk %s ���� %d ������B�z�A%s �b��l�B: %d",
                    //name, transaction.getNum(), transaction.getAccount().getName(), transaction.getAccount().getBalance());

                } else if (transaction.getTranType().equals("withdraw")) {

                    if (BankDB.getBalance(c_name) < transaction.getAmount()) {
                        System.out.printf("Clerk %s ���� %d ������B�z�A%s �b��l�B: %d (������ѡG�b��l�B�����A������B=%d)\n",
                                name, transaction.getNum(), c_name, BankDB.getBalance(c_name), transaction.getAmount());
                        //out.printf("Clerk %s ���� %d ������B�z�A%s �b��l�B: %d (������ѡG�b��l�B�����A������B=%d)",
                        //name, transaction.getNum(), transaction.getAccount().getName(), transaction.getAccount().getBalance(), transaction.getAmount());
                    } else {
                        try {
                            sleep(Set_timeout);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        BankDB.setBalance(c_name, BankDB.getBalance(c_name) - transaction.getAmount());
                        System.out.printf("Clerk %s ���� %d ������B�z�A%s �b��l�B: %d\n",
                                name, transaction.getNum(), c_name, BankDB.getBalance(c_name));
                        //out.printf("Clerk %s ���� %d ������B�z�A%s �b��l�B: %d",
                        //name, transaction.getNum(), transaction.getAccount().getName(), transaction.getAccount().getBalance());
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void run() {
        process(name);
    }
}

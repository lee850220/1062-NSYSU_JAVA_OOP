package myjava.homework;

import java.util.ArrayList;

public class BankDB {
    private static final int initBalance = 5000;
    private static ArrayList<Account> db = new ArrayList<Account>();

    public static int find(String name) {
        int i;
        for (i = 0; i < db.size(); i++) {
            if (db.get(i).getName().equals(name)) break;
        }
        if (i == db.size()) return -1;
        else return i;
    }

    public static void createAcc(String name) {
        db.add(new Account(name, initBalance));
    }

    public static void setBalance(String name, int balance) {
        db.get(find(name)).setBalance(balance);
    }

    public static int getBalance(String name) {
        return db.get(find(name)).getBalance();
    }
}

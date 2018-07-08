package myjava.homework;

public class Transaction {
    private int num;
    private int amount;
    private String tranType;
    private Account account;

    public Transaction(int num, Account account, int amount, String tranType) {
        this.num = num;
        this.account = account;
        this.amount = amount;
        this.tranType = tranType;
    }

    public String getTranType() {
        return tranType;
    }

    public Account getAccount() {
        return account;
    }

    public int getAmount() {
        return amount;
    }

    public int getNum() {
        return num;
    }
}

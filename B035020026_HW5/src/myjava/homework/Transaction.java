package myjava.homework;// Abstract superclass Transaction represents an ATM transaction

public abstract class Transaction {

	private int accountNumber;
	private BankDatabase bankDatabase;

	Transaction(int acc_num, BankDatabase bankBD) {
	    accountNumber = acc_num;
	    bankDatabase = bankBD;
    }

	public int getAccountNumber() {
        return accountNumber;
    }

    public BankDatabase getAccount() {return bankDatabase;}

    public abstract void execute();
	
}

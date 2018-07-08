package myjava.homework;// Represents a balance inquiry ATM transaction

public class BalanceInquiry extends Transaction {

    BalanceInquiry(int acc_num, BankDatabase bankDB) {
        super(acc_num, bankDB);
    }

    public void execute() {
        Screen theScreen = new Screen();
        theScreen.displayMessageLine("Balance Information");
        theScreen.displayMessageLine("Total Balance : " + getAccount().getTotalBalance(getAccountNumber()));
    }
	
}

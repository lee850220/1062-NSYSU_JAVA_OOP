package myjava.homework;// Represents the bank account information database

public class BankDatabase {
	
	private Account[] accounts; // array of Accounts
	
    // no-argument BankDatabase constructor initializes accounts
	public BankDatabase () {
		accounts = new Account[4];  // just 4 accounts for testing
		accounts[0] = new Account(111, 222, 5000,0,'A');
		accounts[1] = new Account(222, 333, 4000,0,'B');
		accounts[2] = new Account(333, 444, 3000,0,'C');
		accounts[3] = new Account(444, 555, 2000,0,'D');
	}

	private int findAccount(int acc) {
		int i;
		for (i = 0; i < accounts.length; i++) {
			if (acc == accounts[i].getAccountNumber()) break;
		}
		if (i != accounts.length) return i;
		else return -1;
	}
	
	public boolean authenticateUser(int acc, int pin) {
		int i;
		for (i = 0; i < accounts.length; i++) {
		    if (acc == accounts[i].getAccountNumber() && accounts[i].validatePIN(pin)) break;
        }
        if (i == accounts.length) return false;
		else return true;
	}

	public int getTotalBalance(int acc) {
        return accounts[findAccount(acc)].getTotalBalance();
	}

	public char getCreditLevel(int acc) {
        return accounts[findAccount(acc)].getCreditLevel();
	}

	public int getDebt(int acc) {
        return accounts[findAccount(acc)].getDebt();
	}

	public void setTotalBalance(int acc, int TB) {
        accounts[findAccount(acc)].setTotalBalance(TB);
	}

	public void setDebt(int acc, int dbt) {
        accounts[findAccount(acc)].setDebt(dbt);
	}

}

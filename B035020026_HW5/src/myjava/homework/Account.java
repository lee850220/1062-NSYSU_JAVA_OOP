package myjava.homework;//Represents a bank account

public class Account {
	
	private int accountNumber;
	private int pin;
	private int totalBalance;
	private int debt;
	private char creditLevel;

	public Account(int acc_num, int pin_num, int TB, int dbt, char CL) {
        accountNumber = acc_num;
        pin = pin_num;
        totalBalance = TB;
        debt = dbt;
        creditLevel = CL;
    }

    public boolean validatePIN(int pin_num) {
        if (pin_num == pin) return true;
        else return false;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public int getTotalBalance() {
        return totalBalance;
    }

    public char getCreditLevel() {
        return creditLevel;
    }

    public int getDebt() {
        return debt;
    }

    public void setTotalBalance(int TB) {
        totalBalance = TB;
    }

    public void setDebt(int dbt) {
        debt = dbt;
    }
}

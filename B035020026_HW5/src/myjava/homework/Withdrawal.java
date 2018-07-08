package myjava.homework;// Represents a withdrawal ATM transaction

public class Withdrawal extends Transaction {

	Withdrawal(int acc_num, BankDatabase bankDB) {
        super(acc_num, bankDB);
    }

    public void execute() {
	    int input, balance;
        Screen theScreen = new Screen();
        Keypad theKeypad = new Keypad();

        /* Check input is non-number */
        while (true) {
            theScreen.displayMessage("How much do you want to withdraw : ");
            input = theKeypad.getInput();
            if (input == -1) {
                theScreen.displayMessageLine("wrong input!\n");
                continue;
            } else break;
        }

        balance = getAccount().getTotalBalance(getAccountNumber());
        if (balance < input) {
            theScreen.displayMessageLine("Transaction Error, Balance not enough.");
        } else {
            theScreen.displayMessageLine("Transaction Success.");
            getAccount().setTotalBalance(getAccountNumber(), balance - input);
        }
    }

}

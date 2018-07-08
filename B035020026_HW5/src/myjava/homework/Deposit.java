package myjava.homework;// Represents a deposit ATM transaction

public class Deposit extends Transaction {

    Deposit(int acc_num, BankDatabase bankDB) {
        super(acc_num, bankDB);
    }

    public void execute() {
        int input;
        Screen theScreen = new Screen();
        Keypad theKeypad = new Keypad();

        /* Check input is non-number */
        while (true) {
            theScreen.displayMessage("How much do you want to deposit : ");
            input = theKeypad.getInput();
            if (input == -1) {
                theScreen.displayMessageLine("wrong input!\n");
                continue;
            } else break;
        }
        theScreen.displayMessageLine("Transaction Success.");
        getAccount().setTotalBalance(getAccountNumber(), getAccount().getTotalBalance(getAccountNumber()) + input);
    }

}

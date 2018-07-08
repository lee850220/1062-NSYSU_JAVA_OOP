package myjava.homework;// Represents a withdrawal ATM transaction

public class Loan extends Transaction {

	Loan(int acc_num, BankDatabase bankDB) {
        super(acc_num, bankDB);
    }

    public enum Credit {
	    A(11000), B(9000), C(7000), D(5000);
	    private int num;

        Credit(int value) {this.num = value;}
        public int value() {return this.num;}
    }

    public void execute() {
        int input,
            account_num = getAccountNumber(),
            credit = Credit.values()[getAccount().getCreditLevel(account_num) - 'A'].value(),
            debt = getAccount().getDebt(account_num);
        Screen theScreen = new Screen();
        Keypad theKeypad = new Keypad();


        if (credit == debt) theScreen.displayMessageLine("Sorry, You cannot loan any money now.");
        else {
            /* Check input is non-number */
            while (true) {
                theScreen.displayMessageLine("Your debt : " + debt);
                theScreen.displayMessage("Your loan limit is " + credit + ", How much do you want to loan : ");
                input = theKeypad.getInput();
                if (input == -1) {
                    theScreen.displayMessageLine("wrong input!\n");
                    continue;
                } else break;
            }

            if (debt + input > credit) {
                theScreen.displayMessageLine("Transaction Error, You have not much Loan Limit.");
            } else {
                theScreen.displayMessageLine("Transaction Success.");
                getAccount().setDebt(account_num, debt + input);
            }

        }
    }
}

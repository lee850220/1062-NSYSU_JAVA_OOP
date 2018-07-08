package myjava.homework;


public class ATM {

	private boolean userAuthenticated;
	private final String main_menu = new String (
	        "Main_menu :\n" +
                    "1. View my balance\n" +
                    "2. Withdraw\n" +
                    "3. Deposit\n" +
                    "4. Loan\n" +
                    "5. Exit\n"
    );
	public BankDatabase bankDatabase;

	public void run() {
        bankDatabase = new BankDatabase();
	    Screen theScreen = new Screen();
	    Keypad theKeypad = new Keypad();
        while (true) {
            int account, pin;

            /* input Account & Pin */
            while (true) {
                theScreen.displayMessageLine("Welcome !");
                theScreen.displayMessage("Please enter your account number : ");
                account = theKeypad.getInput();

                theScreen.displayMessage("Please enter your pin : ");
                pin = theKeypad.getInput();
                if (pin != -1 && account != -1) break;
                theScreen.displayMessageLine("AccountNumber or Pin Error\n"); //check input is non-number
            }

            /* check Account or Pin is valid */
            if (!bankDatabase.authenticateUser(account, pin)) {
                theScreen.displayMessageLine("AccountNumber or Pin Error\n");
                continue;
            }

            EXIT:
            while (true) {
                /* main menu */
                int choice;
                while (true) {
                    theScreen.displayMessage("\n" + main_menu);
                    theScreen.displayMessage("Enter a choice : ");
                    choice = theKeypad.getInput();
                    if (choice >= 0 && choice <= 5) break;
                    theScreen.displayMessageLine("wrong input!"); //check input is valid choice
                }

                /* execute option */
                Transaction transaction;
                switch (choice) {
                    case 1:
                        // View Balance
                        transaction = new BalanceInquiry(account, bankDatabase);
                        break;
                    case 2:
                        // Withdraw
                        transaction = new Withdrawal(account, bankDatabase);
                        break;
                    case 3:
                        // Deposit
                        transaction = new Deposit(account, bankDatabase);
                        break;
                    case 4:
                        // Loan
                        transaction = new Loan(account, bankDatabase);
                        break;
                    case 5:
                        // EXIT
                        theScreen.displayMessageLine("");
                        break EXIT;
                    default:
                        // NEVER BE HERE
                        break EXIT;
                }
                transaction.execute();
            }
        }
    }
	
}

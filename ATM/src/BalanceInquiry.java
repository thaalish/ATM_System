// BalanceInquiry.java
// This class handles the balance inquiry transaction, allowing users to check their available and total balances.

public class BalanceInquiry extends Transaction {
    private Keypad keypad;

    private static final int BACK_TO_MENU = 0;

    public BalanceInquiry(int accountNumber, Screen screen, BankDatabase bankDatabase, Keypad keypad) {
        super(accountNumber, screen, bankDatabase);
        this.keypad = keypad;
    }

    @Override
    public void execute() {
        // Retrieve available and total balances from the bank database
        double availableBalance = bankDatabase.getAvailableBalance(accountNumber);
        double totalBalance = bankDatabase.getTotalBalance(accountNumber);
        boolean backToMenu = false;

        // Clear the screen before displaying information
        screen.clearScreen();
        while (!backToMenu) {
            screen.displayMessageLine("\nBalance Information:");
            screen.displayMessage(" - Available balance: ");
            screen.displayMessageLine(String.valueOf(availableBalance));
            screen.displayMessage(" - Total balance: ");
            screen.displayMessageLine(String.valueOf(totalBalance));

            screen.displayMessageLine("\nMenu options:");
            screen.displayMessageLine("0 - Cancel transaction");
            screen.displayMessage("Choose an option: ");

            int selection = keypad.getIntegerInput();

            screen.clearScreen();
            // Check if the user wants to go back to the main menu
            if (selection == BACK_TO_MENU) {
                backToMenu = true;
                return;
            } else {
                // Handle invalid menu selection
                screen.displayMessageLine("Invalid selection. Please try again.");
                screen.displayMessageLine("================================================================");
            }
        }
    }
}

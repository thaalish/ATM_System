// ATM.java
// Represents an automated teller machine

public class ATM {
    private boolean userAuthenticated;
    private int currentAccountNumber;
    private Screen screen;
    private Keypad keypad;
    private CashDispenser cashDispenser;
    private DepositSlot depositSlot;
    private BankDatabase bankDatabase;

    // Menu options
    public static final int BALANCE_INQUIRY = 1;
    public static final int WITHDRAWAL = 2;
    public static final int DEPOSIT = 3;
    public static final int EXIT = 4;

    // Constructor
    public ATM() {
        userAuthenticated = false;
        currentAccountNumber = 0;
        screen = new Screen();
        keypad = new Keypad();
        cashDispenser = new CashDispenser();
        depositSlot = new DepositSlot();
        bankDatabase = BankDatabase.getInstance();
    }

    // Initialization
    public void run() {
        while (true) {
            while (!userAuthenticated) {
                screen.displayMessageLine("\nWelcome!");
                authenticateUser();
            }

            performTransactions();

            // Reset before next ATM session
            userAuthenticated = false;
            currentAccountNumber = 0;
            screen.displayMessageLine("\nThank you! Goodbye!");
        }
    }

    private void authenticateUser() {
        screen.displayMessage("\nPlease enter your account number: ");
        int accountNumber = keypad.getIntegerInput();

        screen.displayMessage("\nEnter your PIN: ");
        int pin = keypad.getIntegerInput();

        screen.clearScreen();
        userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);

        if (userAuthenticated) {
            currentAccountNumber = accountNumber;
        } else {
            screen.displayMessageLine("Invalid account number or PIN. Please try again.");
            screen.displayMessageLine("================================================");
        }
    }

    private void performTransactions() {
        Transaction currentTransaction = null;
        boolean userExited = false;

        while (!userExited) {
            int mainMenuSelection = displayMainMenu();

            if (mainMenuSelection == EXIT) {
                screen.clearScreen();
                screen.displayMessageLine("\nExiting the system...");
                userExited = true;
            } else {
                try {
                    currentTransaction = TransactionFactory.createTransaction(mainMenuSelection, currentAccountNumber,
                            screen, bankDatabase, keypad, cashDispenser, depositSlot);
                    currentTransaction.execute();
                } catch (IllegalArgumentException e) {
                    screen.displayMessageLine("\nYou did not enter a valid selection. Try again.");
                }
            }
        }
    }

    private int displayMainMenu() {
        screen.displayMessageLine("\nMain menu:");
        screen.displayMessageLine("1 - View my balance");
        screen.displayMessageLine("2 - Withdraw cash");
        screen.displayMessageLine("3 - Deposit funds");
        screen.displayMessageLine("4 - Exit\n");

        return keypad.getIntegerInput();
    }
}

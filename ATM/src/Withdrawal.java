// Withdrawal.java
// This class represents a withdrawal transaction in an ATM, allowing users to withdraw funds from their account.

public class Withdrawal extends Transaction {
    private Keypad keypad;
    private CashDispenser cashDispenser;

    private static final int BACK_TO_MENU = 0;
    private static final int CANCEL = 0;
    private static final int[] PRESET_AMOUNTS = { 20, 40, 60, 100, 200 };

    public Withdrawal(int accountNumber, Screen screen, BankDatabase bankDatabase, Keypad keypad,
            CashDispenser cashDispenser) {
        super(accountNumber, screen, bankDatabase);
        this.keypad = keypad;
        this.cashDispenser = cashDispenser;
    }

    @Override
    public void execute() {
        boolean cashDispensed = false;
        double availableBalance;

        // Clear the screen before displaying messages
        screen.clearScreen();

        Account account = getAccount();
        if (account == null) {
            handleErrors("Account not found during withdrawal.");
            return;
        }

        while (!cashDispensed) {
            // Display menu of withdrawal amounts and get user selection
            int selection = displayMenuOfAmounts();

            if (selection == CANCEL) {
                screen.displayMessageLine("Canceling transaction...");
                return;
            }

            double amount = selection == (PRESET_AMOUNTS.length + 1) ? getCustomAmount() : PRESET_AMOUNTS[selection - 1];
            if (amount % 20 != 0) {
                screen.displayMessageLine("Amount must be in multiples of $20.");
                continue;
            }

            availableBalance = account.getAvailableBalance();

            screen.clearScreen();
            if (amount <= availableBalance) {
                if (cashDispenser.isSufficientCashAvailable(amount)) {
                    bankDatabase.debitAccount(accountNumber, amount);
                    cashDispenser.dispenseCash(amount);
                    cashDispensed = true;
                    screen.displayMessageLine("Your cash has been dispensed. Please take your cash.");
                    screen.displayMessageLine("================================================================");
                } else {
                    handleErrors("Insufficient cash available in the dispenser.");
                    screen.displayMessageLine("================================================================");
                }
            } else {
                handleErrors("Insufficient funds in your account.");
                screen.displayMessageLine("================================================================");
            }
        }
    }

    // Method to display menu of preset withdrawal amounts and get user selection
    private int displayMenuOfAmounts() {
        screen.displayMessageLine("\nWithdrawal options:");
        for (int i = 0; i < PRESET_AMOUNTS.length; i++) {
            screen.displayMessageLine((i + 1) + " - $" + PRESET_AMOUNTS[i]);
        }
        screen.displayMessageLine((PRESET_AMOUNTS.length + 1) + " - Other Amount");
        screen.displayMessageLine("0 - Cancel transaction");
        screen.displayMessage("Choose a withdrawal option: ");
        int input = keypad.getIntegerInput();

        if (input >= 0 && input <= PRESET_AMOUNTS.length + 1) {
            return input;
        } else {
            screen.displayMessageLine("Invalid selection. Please try again.");
            return displayMenuOfAmounts();
        }
    }

    private int getCustomAmount() {
        screen.displayMessage("\nEnter amount to withdraw (multiples of $20 only): ");
        return keypad.getWithdrawalInput();
    }
}

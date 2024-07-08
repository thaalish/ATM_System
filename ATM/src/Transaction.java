// Transaction.java
// This abstract class represents a transaction in an ATM system, providing common attributes and methods for transactions.

public abstract class Transaction {
    protected int accountNumber;
    protected Screen screen;
    protected BankDatabase bankDatabase; 

    public Transaction(int accountNumber, Screen atmScreen, BankDatabase bankDatabase) {
        this.accountNumber = accountNumber;
        this.screen = atmScreen;
        this.bankDatabase = bankDatabase;
    }

    // Abstract method to be implemented by subclasses for executing specific
    // transaction logic
    public abstract void execute();

    // Method to get the account associated with the transaction
    protected Account getAccount() {
        Account account = bankDatabase.getAccount(accountNumber);
        if (account == null) {
            screen.displayMessageLine("Account not found.");
        }
        return account;
    }

    // Method to handle errors during transaction execution
    protected void handleErrors(String errorMessage) {
        // Log error for security purposes
        System.err.println("Error: " + errorMessage);
        screen.displayMessageLine(errorMessage);
    }
}

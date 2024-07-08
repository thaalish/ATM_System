// BankDatabase.java
// This class simulates a bank database using a singleton pattern to manage accounts and their transactions securely.

import java.util.HashMap;
import java.util.Map;

public class BankDatabase {
    private static BankDatabase singletonInstance;
    private Map<Integer, Account> accounts;

    private BankDatabase() {
        accounts = new HashMap<>();

        // Adding some test accounts to the database with initial balances
        // Key will be the account number
        accounts.put(12345, new Account(12345, 54321, 1000.0, 1200.0));
        accounts.put(98765, new Account(98765, 56789, 200.0, 200.0));
    }

    // Method to get the singleton instance of the BankDatabase
    public static synchronized BankDatabase getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new BankDatabase();
        }
        return singletonInstance;
    }

    public boolean authenticateUser(int accountNumber, int pin) {
        Account account = accounts.get(accountNumber);
        return account != null && account.validatePIN(pin);
    }

    public synchronized double getAvailableBalance(int accountNumber) {
        Account account = accounts.get(accountNumber);
        return account != null ? account.getAvailableBalance() : 0;
    }

    public synchronized double getTotalBalance(int accountNumber) {
        Account account = accounts.get(accountNumber);
        return account != null ? account.getTotalBalance() : 0;
    }

    public synchronized void creditAccount(int accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.credit(amount);
        }
    }

    public synchronized void debitAccount(int accountNumber, double amount) {
        Account account = accounts.get(accountNumber);
        if (account != null) {
            account.debit(amount);
        }
    }

    // Provide controlled access to account object securely
    protected synchronized Account getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }
}

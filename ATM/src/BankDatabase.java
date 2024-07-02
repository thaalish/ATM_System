// BankDatabase.java
// Represents the bank account information database

public class BankDatabase {
    private static BankDatabase singletonInstance;
    private Account[] accounts;

    public BankDatabase() {
        accounts = new Account[2]; // 2 accounts for testing
        accounts[0] = new Account(12345, 54321, 1000.0, 1200.0);
        accounts[1] = new Account(98765, 56789, 200.0, 200.0);
    }

    public static synchronized BankDatabase getInstance() {
        if (singletonInstance == null) {
            singletonInstance = new BankDatabase();
        }
        return singletonInstance;
    }

    public boolean authenticateUser(int userAccountNumber, int userPIN) {
        Account userAccount = getAccount(userAccountNumber);
        return userAccount != null && userAccount.validatePIN(userPIN);
    }

    public double getAvailableBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getAvailableBalance();
    }

    public double getTotalBalance(int userAccountNumber) {
        return getAccount(userAccountNumber).getTotalBalance();
    }

    public void credit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).credit(amount);
    }

    public void debit(int userAccountNumber, double amount) {
        getAccount(userAccountNumber).debit(amount);
    }

    private Account getAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }

        return null;
    }
}

// Account.java
// This class represents a bank account with basic functionalities such as balance inquiry and funds transfer.

public class Account {
    private int accountNumber;
    private int pin;
    private double availableBalance;
    private double totalBalance;

    // Constructor to initialize an account with provided details
    public Account(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        this.accountNumber = theAccountNumber;
        this.pin = thePIN;
        this.availableBalance = theAvailableBalance;
        this.totalBalance = theTotalBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public boolean validatePIN(int userPIN) {
        return pin == userPIN;
    }

    public void credit(double amount) {
        totalBalance += amount;
    }

    public void debit(double amount) {
        availableBalance -= amount;
        totalBalance -= amount;
    }
}

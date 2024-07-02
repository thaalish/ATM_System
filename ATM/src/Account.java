// Account.java
// Represents a bank account

public class Account {
    private int accountNumber;
    private int pin;
    private double availableBalance;
    private double totalBalance;

    public Account(int theAccountNumber, int thePIN, double theAvailableBalance, double theTotalBalance) {
        this.accountNumber = theAccountNumber;
        this.pin = thePIN;
        this.availableBalance = theAvailableBalance;
        this.totalBalance = theTotalBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePIN(int userPIN) {
        return pin == userPIN;
    }

    public double getAvailableBalance() {
        return availableBalance;
    }

    public double getTotalBalance() {
        return totalBalance;
    }

    public void credit(double amount) {
        totalBalance += amount;
    }

    public void debit(double amount) {
        availableBalance -= amount;
        totalBalance -= amount;
    }
}

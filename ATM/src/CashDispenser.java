// CashDispenser.java
// This class simulates a cash dispenser used in ATMs, managing the dispensing of cash and maintaining the count of available bills.

public class CashDispenser {
    // Initial number of $20 bills in the dispenser
    private static final int INITIAL_COUNT = 500;

    // Number of $20 bills remaining in the dispenser
    private int count;

    public CashDispenser() {
        count = INITIAL_COUNT;
    }

    public void dispenseCash(double amount) {
        int billsRequired = (int) (amount / 20); // Number of $20 bills required
        count -= billsRequired;
    }

    public boolean isSufficientCashAvailable(double amount) {
        int billsRequired = (int) (amount / 20); // Number of $20 bills required
        return count >= billsRequired;
    }

    public int getCount() {
        return count;
    }

    public void refill(int billCount) {
        count += billCount;
    }
}

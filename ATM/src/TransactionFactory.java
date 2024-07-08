// TransactionFactory.java
// This abstract factory class creates different types of transactions based on the specified type.

public abstract class TransactionFactory {
    public static Transaction createTransaction(int type, int accountNumber, Screen screen, BankDatabase bankDatabase,
            Keypad keypad, CashDispenser cashDispenser, DepositSlot depositSlot) {
        switch (type) {
            case ATM.BALANCE_INQUIRY:
                return new BalanceInquiry(accountNumber, screen, bankDatabase, keypad);
            case ATM.WITHDRAWAL:
                return new Withdrawal(accountNumber, screen, bankDatabase, keypad, cashDispenser);
            case ATM.DEPOSIT:
                return new Deposit(accountNumber, screen, bankDatabase, keypad, depositSlot);
            default:
                throw new IllegalArgumentException("Invalid transaction type");
        }
    }
}

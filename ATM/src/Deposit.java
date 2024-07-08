// Deposit.java
// This class represents a deposit transaction, allowing users to deposit funds into their account using an ATM.

public class Deposit extends Transaction {
  private Keypad keypad;
  private DepositSlot depositSlot;

  public Deposit(int accountNumber, Screen screen, BankDatabase bankDatabase, Keypad keypad, DepositSlot depositSlot) {
    super(accountNumber, screen, bankDatabase);
    this.keypad = keypad;
    this.depositSlot = depositSlot;
  }

  @Override
  public void execute() {
    // Clear the screen before displaying messages
    screen.clearScreen();

    // Prompt the user to enter a deposit amount
    screen.displayMessage("\nPlease enter a deposit amount in DOLLARS (or 0 to cancel): ");
    double amount = keypad.getDepositInput();

    if (amount <= 0) {
      screen.displayMessageLine("Canceling transaction...");
    } else {
      screen.displayMessageLine("\nPlease insert a deposit envelope containing $" + amount);
      boolean envelopeReceived = depositSlot.isEnvelopeReceived();

      
      screen.clearScreen();
      if (envelopeReceived) {
        bankDatabase.creditAccount(accountNumber, amount);
        screen.displayMessageLine(
            "Your envelope has been received.\nNOTE: The money deposited will not be available until we verify the amount of any enclosed cash and your checks clear.");
        screen.displayMessageLine("================================================================");
      } else {
        handleErrors("Deposit envelope not received.");
        screen.displayMessageLine("================================================================");
      }
    }
  }
}
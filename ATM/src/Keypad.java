// Keypad.java
// This class represents a keypad used in ATM software to receive user input for various transactions.

import java.util.Scanner;

public class Keypad {
    private Scanner scanner;

    public Keypad() {
        scanner = new Scanner(System.in);
    }

    public int getIntegerInput() {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    // Method to get input for withdrawal (whole dollars only, in multiples of $20)
    public int getWithdrawalInput() {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input < 0 || input % 20 != 0) {
                    throw new NumberFormatException("Amount must be a positive whole number in multiples of $20.");
                }
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive whole number in multiples of $20.");
            }
        }
    }

    // Method to get input for deposit (allows cents)
    public double getDepositInput() {
        while (true) {
            try {
                double input = Double.parseDouble(scanner.nextLine());
                if (input < 0) {
                    throw new NumberFormatException("Amount must be positive.");
                }
                return input;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a positive amount.");
            }
        }
    }
}

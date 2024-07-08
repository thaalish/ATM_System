// Screen.java
// This class represents the screen used in ATM software to display messages and manage screen operations.

public class Screen {
    // Method to display a message without a new line
    public void displayMessage(String message) {
        System.out.print(message);
    }

    // Method to display a message with a new line
    public void displayMessageLine(String message) {
        System.out.println(message);
    }

    // Method to clear the screen
    public void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

package src;

import java.util.Scanner;

public class AdminLogin {
    private Scanner scanner;

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_CYAN = "\u001B[36m";

    public AdminLogin() {
        scanner = new Scanner(System.in);
        displayLoginPrompt();
    }

    private void displayLoginPrompt() {
        System.out.println("");
        System.out.println("");
        System.out.println(ANSI_CYAN + "====== Admin Login ======" + ANSI_RESET);
        System.out.println(ANSI_YELLOW + "     (Type \"Q\" to exit)" + ANSI_RESET);
        System.out.println("");
        System.out.print("Username : ");
        String username = scanner.nextLine();
        if (username.equalsIgnoreCase("Q")) {
            returnToMainMenu();
            return;
        }

        System.out.print("Password : ");
        String password = scanner.nextLine();
        if (password.equalsIgnoreCase("Q")) {
            returnToMainMenu();
            return;
        }

        if (username.equalsIgnoreCase("admin123") && password.equalsIgnoreCase("123")) {
            System.out.println(ANSI_GREEN + "Login Successful ! " + ANSI_RESET);
            // Call to Admin functionality
            new Admin();
        } else {
            System.out.println(ANSI_RED + "Login Failed ! " + ANSI_RESET);
            showOptions();
        }
    }

    private void showOptions() {
        System.out.println("\nOptions:");
        System.out.println("1. Try Again");
        System.out.println("2. Back to Main Menu");
        System.out.println("Q. Quit");
        System.out.print("Enter your choice (1-2 or Q): ");

        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("Q")) {
            returnToMainMenu();
            return;
        }

        try {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    displayLoginPrompt();
                    break;
                case 2:
                    returnToMainMenu();
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
                    showOptions();
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println(ANSI_RED + "Invalid input. Please enter a number or 'Q'." + ANSI_RESET);
            showOptions();
        }
    }

    private void returnToMainMenu() {
        System.out.println(ANSI_YELLOW + "\nReturning to main menu..." + ANSI_RESET);
        new Main();
    }
}

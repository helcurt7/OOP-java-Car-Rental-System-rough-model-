package src.Contoller;

import java.util.Scanner;
import src.Main;

public class UpdateProfile {
    private String ID;
    private Scanner scanner;

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public UpdateProfile(String ID) {
        this.ID = ID;
        this.scanner = new Scanner(System.in);
        displayOptions();
    }

    private void displayOptions() {
        boolean running = true;

        while (running) {
            System.out.println(ANSI_YELLOW + "\n===== Updating Profile =====" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "1. Change Password" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "2. Update Email" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "3. Update Phone Number" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "4. Back to Main Menu" + ANSI_RESET);
            System.out.print(ANSI_YELLOW + "\nEnter your choice (1-4): " + ANSI_RESET);

            int choice = getValidChoice();

            switch (choice) {
                case 1:
                    changePassword();
                    break;
                case 2:
                    updateEmail();
                    break;
                case 3:
                    updatePhone();
                    break;
                case 4:
                    returnToMainMenu();
                    running = false;
                    break;
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
            }
        }
    }

    private int getValidChoice() {
        int choice = 0;
        boolean validInput = false;

        while (!validInput) {
            try {
                choice = Integer.parseInt(scanner.nextLine());
                validInput = true;
            } catch (NumberFormatException e) {
                System.out.print(ANSI_YELLOW + "Please enter a number (1-4): " + ANSI_RESET);
            }
        }
        return choice;
    }

    private void changePassword() {
        System.out.println(ANSI_YELLOW + "\nChanging password for customer ID: " + ID + ANSI_RESET);
        // Replace with actual implementation
        new ChangePassword(ID);
    }

    private void updateEmail() {
        System.out.println(ANSI_YELLOW + "\nUpdating email for customer ID: " + ID + ANSI_RESET);
        // Replace with actual implementation
        new ChangeEmail(ID);
    }

    private void updatePhone() {
        System.out.println(ANSI_YELLOW + "\nUpdating phone for customer ID: " + ID + ANSI_RESET);
        // Replace with actual implementation
        new ChangePhone(ID);
    }

    private void returnToMainMenu() {
        System.out.println(ANSI_YELLOW + "\nReturning to main menu..." + ANSI_RESET);
        // Replace with actual implementation
        new FinalChoice();
        new Main();
    }
}

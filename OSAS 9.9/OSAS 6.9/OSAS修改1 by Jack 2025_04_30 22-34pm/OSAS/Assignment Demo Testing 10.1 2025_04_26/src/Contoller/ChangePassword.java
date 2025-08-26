package src.Contoller;

import java.util.Scanner;
import src.CustomerOperations;
import src.CustomerPage;

public class ChangePassword {
    private String ID;

    public ChangePassword(String ID) {
        this.ID = ID;
        start();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("========== Change Password ==========(Type \"Q\" to exit)");

        String password;
        boolean validPassword = false;

        // ANSI escape codes
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";

        do {
            System.out.print("\nEnter new password (Q to exit): ");
            password = scanner.nextLine();

            if (password.equalsIgnoreCase("Q")) {
                System.out.println("\nCancelling password change...");
                returnToCustomerPage();
                return;
            }

            if (password.isEmpty()) {
                System.out.println(ANSI_RED + "Password cannot be empty! Please try again." + ANSI_RESET);
            } else {
                validPassword = true;
            }
        } while (!validPassword);

        CustomerOperations co = new CustomerOperations();
        boolean updated = co.changePassword(ID, password);

        if (updated) {
            System.out.println(ANSI_GREEN + "Your password has been updated successfully" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Failed to Update Password!" + ANSI_RESET);
            System.out.println(ANSI_RED + "Please try again!" + ANSI_RESET);
        }

        // Returning to customer menu
        System.out.print("\nPress Enter to return to Customer Page...");
        scanner.nextLine();
        returnToCustomerPage();
    }

    private void returnToCustomerPage() {
        System.out.println("Returning to Customer Page...");
        new CustomerPage(ID);
    }
}

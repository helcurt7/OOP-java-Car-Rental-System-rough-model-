package src.Contoller;

import java.util.Scanner;
import src.CustomerOperations;
import src.CustomerPage;

public class ChangePhone {
    private String ID;

    public ChangePhone(String ID) {
        this.ID = ID;
        displayMenu();
    }

    public void displayMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // ANSI escape codes
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";

        while (running) {
            System.out.println("\n===== Update Phone Number =====");
            System.out.println("1. Update phone number");
            System.out.println("2. Back to Customer Page");
            System.out.print("Enter your choice (1-2): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    updatePhoneNumber(scanner);
                    break;
                case "2":
                    running = false;
                    returnToCustomerPage();
                default:
                    System.out.println(ANSI_RED + "Invalid choice. Please try again." + ANSI_RESET);
            }
        }
    }

    private void updatePhoneNumber(Scanner scanner) {
        String phoneNumber;
        boolean validInput = false;

        while (!validInput) {
            System.out.print("Enter new phone number: ");
            phoneNumber = scanner.nextLine();

            // Check if the input contains only digits
            if (phoneNumber.matches("\\d+")) {
                validInput = true;

                CustomerOperations co = new CustomerOperations();
                boolean updated = co.updatePhoneNumber(ID, phoneNumber);

                if (updated) {
                    System.out.println(ANSI_GREEN + "Phone Number Updated Successfully" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + "Failed to Update Phone Number" + ANSI_RESET);
                }
            } else {
                System.out.println(ANSI_RED + "Invalid input! Phone number must contain only digits. Please try again."
                        + ANSI_RESET);
            }
        }
    }

    private void returnToCustomerPage() {
        System.out.println("Returing to Customer Page...");
        new CustomerPage(ID);
    }
}

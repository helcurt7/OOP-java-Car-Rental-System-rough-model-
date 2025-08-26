package src.Contoller;

import java.util.Scanner;
import src.Admin;
import src.AdminOperations;

public class DeleteCustomer {
    private AdminOperations adminOps;

    // ANSI escape codes for colored output
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    public DeleteCustomer() {
        adminOps = new AdminOperations();
        deleteCustomerProcess();
    }

    public void deleteCustomerProcess() {
        Scanner scanner = new Scanner(System.in);
        boolean continueRunning = true;

        while (continueRunning) {
            System.out.println(ANSI_YELLOW + "\n========== DELETE CUSTOMER ============" + ANSI_RESET);
            System.out.print("Enter Customer ID (or type 'back' to return): ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("back")) {
                continueRunning = false;
                System.out.println(ANSI_YELLOW + "Returning to admin menu..." + ANSI_RESET);
                new Admin();
            } else if (!input.isEmpty()) {
                boolean found = adminOps.removeCustomer(input);

                if (found) {
                    System.out.println(ANSI_GREEN + "Customer deleted successfully" + ANSI_RESET);
                } else {
                    System.out.println(ANSI_RED + "User not found" + ANSI_RESET);
                }
            }
        }
    }
}

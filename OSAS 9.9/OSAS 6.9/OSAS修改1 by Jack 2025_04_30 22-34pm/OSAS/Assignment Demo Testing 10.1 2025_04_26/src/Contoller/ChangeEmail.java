package src.Contoller;

import java.util.Scanner;
import src.CustomerOperations;
import src.CustomerPage;

public class ChangeEmail {
    private String ID;

    public ChangeEmail(String ID) {
        this.ID = ID;
        processEmailChange();
    }

    private void processEmailChange() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n===== Update Email =====");

        String email;
        boolean validEmail = false;

        // ANSI escape codes
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_RED = "\u001B[31m";
        final String ANSI_GREEN = "\u001B[32m";

        do {
            System.out.print("Enter new email (must include @ and mail.com) (or 'back' to return): ");
            email = scanner.nextLine().trim();

            if (email.equalsIgnoreCase("back")) {
                System.out.println("\n\tReturning to Customer Portal...");
                new CustomerPage(ID);
                return;
            }

            if (email.isEmpty()) {
                System.out.println(ANSI_RED + "Email cannot be empty!" + ANSI_RESET);
                continue;
            }

            if (!email.contains("@") || !email.contains("mail.com")) {
                System.out.println(ANSI_RED + "\nEmail must contain @ and mail.com!" + ANSI_RESET);
                System.out.println("For example: username@example.mail.com");
                continue;
            }

            validEmail = true;

        } while (!validEmail);

        // Update email
        CustomerOperations co = new CustomerOperations();
        boolean updated = co.updateEmail(ID, email);

        if (updated) {
            System.out.println(ANSI_GREEN + "Email Updated Successfully" + ANSI_RESET);
        } else {
            System.out.println(ANSI_RED + "Email Not Updated. Please try again." + ANSI_RESET);
        }

        // Return to customer page after updating
        new CustomerPage(ID);
    }
}

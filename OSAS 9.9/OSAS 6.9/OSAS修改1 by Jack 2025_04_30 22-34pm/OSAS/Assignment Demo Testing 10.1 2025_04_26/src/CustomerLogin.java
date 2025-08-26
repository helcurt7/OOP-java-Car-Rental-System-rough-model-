package src;

import java.util.Scanner;

public class CustomerLogin {

    // ANSI color codes for console output
    private static final String RESET = "\u001B[0m";
    private static final String RED = "\u001B[31m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String CYAN = "\u001B[36m";

    public CustomerLogin() {
        System.out.println("");
        System.out.println(YELLOW + "====== Customer Login ======" + RESET);
        System.out.println(CYAN + "  ( Type \"Q\" to exit )" + RESET);
        System.out.println("");

        processLogin();
    }

    private void processLogin() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Customer ID : ");
        String customerId = scanner.nextLine();
        if (customerId.equalsIgnoreCase("Q")) {
            returnToMainMenu();
            return;
        }

        System.out.print("Enter Password : ");
        String password = scanner.nextLine();
        if (password.equalsIgnoreCase("Q")) {
            returnToMainMenu();
            return;
        }

        CustomerOperations operations = new CustomerOperations();
        boolean login = operations.credentialsFound(customerId, password);

        if (login) {
            System.out.println(GREEN + "Login Successful ! " + RESET);
            new CustomerPage(customerId);
        } else {
            System.out.println(RED + "Login Failed ! Try Again ! " + RESET);
            showOptions(scanner);
        }
    }

    private void showOptions(Scanner scanner) {
        System.out.println("");
        System.out.println(YELLOW + "\n Options : " + RESET);
        System.out.println(BLUE + "1. Try Again" + RESET);
        System.out.println(BLUE + "2. Back to Main Menu" + RESET);
        System.out.println(BLUE + "Q. Quit" + RESET);
        System.out.println("");
        System.out.print("Enter your choice (1-2 or Q): ");

        String input = scanner.nextLine();
        if (input.equalsIgnoreCase("Q")) {
            returnToMainMenu();
            return;
        }

        int choice = 0;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("");
            System.out.println(RED + "Invalid input. Please enter a number or 'Q'." + RESET);
            showOptions(scanner);
            return;
        }

        switch (choice) {
            case 1:
                processLogin();
                break;
            case 2:
                new Main();
                break;
            default:
                System.out.println("");
                System.out.println(RED + "Invalid choice. Please try again." + RESET);
                showOptions(scanner);
                break;
        }
    }

    private void returnToMainMenu() {
        System.out.println("");
        System.out.println(CYAN + "\nReturning to main menu..." + RESET);
        new Main();
    }
}
